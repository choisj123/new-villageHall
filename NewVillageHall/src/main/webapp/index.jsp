<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix = "c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <title>마을회관</title>
    <meta content="" name="description" />
    <meta content="" name="keywords" />

    <!-- Favicons -->
    <link href="${contextPath}/resources/images/logo.png" rel="icon" />

    <!-- fontawesome -->
    <script
      src="https://kit.fontawesome.com/2f1bf0eac7.js"
      crossorigin="anonymous"
    ></script>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

    <!-- 정훈씨꺼 지도 api key -->
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=196b8c346b121bd08b2a475bfa001dd0"
    ></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=196b8c346b121bd08b2a475bfa001dd0&libraries=LIBRARY"
    ></script>

    <!-- Google Fonts 
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Montserrat:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Raleway:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
  -->
    <!-- 글꼴 -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400;500;600;700&display=swap"
      rel="stylesheet"
    />

    <!-- Vendor CSS Files -->
    <link
      href="resources/vendor/bootstrap/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="resources/vendor/bootstrap-icons/bootstrap-icons.css"
      rel="stylesheet"
    />
    <link href="resources/vendor/aos/aos.css" rel="stylesheet" />
    <link
      href="resources/vendor/glightbox/css/glightbox.min.css"
      rel="stylesheet"
    />
    <link
      href="resources/vendor/swiper/swiper-bundle.min.css"
      rel="stylesheet"
    />

    <!-- Template Main CSS File -->
    <link href="resources/css/main.css" rel="stylesheet" />

    <!-- =======================================================
  * Template Name: Impact
  * Updated: Mar 10 2023 with Bootstrap v5.2.3
  * Template URL: https://bootstrapmade.com/impact-bootstrap-business-website-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
  </head>

  <body>
    <!-- header include -->
    <jsp:include page="/WEB-INF/views/common/header.jsp" />

    <!-- ======= Hero Section ======= -->
    <section id="hero" class="hero">
      <div class="container position-relative">
        <div class="row gy-5" data-aos="fade-in">
        
          <div
            class="col-lg-6 order-2 order-lg-1 d-flex flex-column justify-content-center text-center text-lg-start"
          >
            <a href="index.html" class="logo d-flex align-items-center">
              <!-- Uncomment the line below if you also wish to use an image logo -->
              <img
                src="${contextPath}/resources/images/logo.png"
                id="home-logo"
              />
            </a>
            <p>메롱</p>
          </div>
            
          <div class="col-lg-6 order-1 order-lg-2 justify-content-end">
            <div class="d-flex justify-content-center justify-content-lg-start">
              <a href="${contextPath}/user/login" class="btn-get-started"
                >시작하기</a
              >
              <a
                href="${contextPath}/user/termsOfUse"
                class="glightbox btn-watch-video d-flex align-items-center"
                ><span>이웃주민 되기 </span><i class="fa-solid fa-user-plus"></i
              ></a>
            </div>
            
          </div>
          
        </div>
      </div>
    </section>
    <!-- End Hero Section -->

    <main id="main">
      <!-- ======= About Us Section ======= -->
      <!-- 지도 부분 -->
      <section id="about" class="map">
        <div class="container" data-aos="fade-up">
          <div class="section-header">
            <h2>Map</h2>
            <p>위치별 게시글 확인</p>
          </div>
          <form
            onchange="showMarkersByCategory(this.value)"
            id="categorySelect"
          >
            <input
              type="radio"
              name="category"
              id="all"
              value="전체"
              checked
              style="display: none"
            /><label for="all">#전체</label>
            <input
              type="radio"
              name="category"
              id="issue"
              value="이슈"
              style="display: none"
            /><label for="issue">#이슈</label>
            <input
              type="radio"
              name="category"
              id="eat"
              value="맛집"
              style="display: none"
            /><label for="eat">#맛집</label>
            <input
              type="radio"
              name="category"
              id="hobby"
              value="취미"
              style="display: none"
            /><label for="hobby">#취미</label>
            <input
              type="radio"
              name="category"
              id="friend"
              value="친목"
              style="display: none"
            /><label for="friend">#친목</label>
            <input
              type="radio"
              name="category"
              id="recommend"
              value="추천"
              style="display: none"
            /><label for="recommend">#추천</label>
            <input
              type="radio"
              name="category"
              id="etc"
              value="기타"
              style="display: none"
            /><label for="etc">#기타</label>

            <br />
          </form>
          <div class="map-wrap">
            <div id="map" style="width: auto; height: 500px; margin-left: 15px">
              <div class="spin-container">
                <i class="fas fa-spinner fa-10x fa-spin"></i>
              </div>
              <div class="map-nav">
                <div class="map-header"></div>
                <ul id="placesList"></ul>
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- End About Us Section -->

      <!-- ======= Frequently Asked Questions Section ======= -->
      <section id="faq" class="faq">
        <div class="container" data-aos="fade-up">
          <div class="row gy-4">
            <div class="col-lg-4">
              <div class="content px-xl-5">
                <h3>Frequently Asked <strong>Questions</strong></h3>
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                  do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                  Duis aute irure dolor in reprehenderit
                </p>
              </div>
            </div>

            <div class="col-lg-8">
              <div
                class="accordion accordion-flush"
                id="faqlist"
                data-aos="fade-up"
                data-aos-delay="100"
              >
                <div class="accordion-item">
                  <h3 class="accordion-header">
                    <button
                      class="accordion-button collapsed"
                      type="button"
                      data-bs-toggle="collapse"
                      data-bs-target="#faq-content-1"
                    >
                      <span class="num">1.</span>
                      Non consectetur a erat nam at lectus urna duis?
                    </button>
                  </h3>
                  <div
                    id="faq-content-1"
                    class="accordion-collapse collapse"
                    data-bs-parent="#faqlist"
                  >
                    <div class="accordion-body">
                      Feugiat pretium nibh ipsum consequat. Tempus iaculis urna
                      id volutpat lacus laoreet non curabitur gravida. Venenatis
                      lectus magna fringilla urna porttitor rhoncus dolor purus
                      non.
                    </div>
                  </div>
                </div>
                <!-- # Faq item-->

                <div class="accordion-item">
                  <h3 class="accordion-header">
                    <button
                      class="accordion-button collapsed"
                      type="button"
                      data-bs-toggle="collapse"
                      data-bs-target="#faq-content-2"
                    >
                      <span class="num">2.</span>
                      Feugiat scelerisque varius morbi enim nunc faucibus a
                      pellentesque?
                    </button>
                  </h3>
                  <div
                    id="faq-content-2"
                    class="accordion-collapse collapse"
                    data-bs-parent="#faqlist"
                  >
                    <div class="accordion-body">
                      Dolor sit amet consectetur adipiscing elit pellentesque
                      habitant morbi. Id interdum velit laoreet id donec
                      ultrices. Fringilla phasellus faucibus scelerisque
                      eleifend donec pretium. Est pellentesque elit ullamcorper
                      dignissim. Mauris ultrices eros in cursus turpis massa
                      tincidunt dui.
                    </div>
                  </div>
                </div>
                <!-- # Faq item-->

                <div class="accordion-item">
                  <h3 class="accordion-header">
                    <button
                      class="accordion-button collapsed"
                      type="button"
                      data-bs-toggle="collapse"
                      data-bs-target="#faq-content-3"
                    >
                      <span class="num">3.</span>
                      Dolor sit amet consectetur adipiscing elit pellentesque
                      habitant morbi?
                    </button>
                  </h3>
                  <div
                    id="faq-content-3"
                    class="accordion-collapse collapse"
                    data-bs-parent="#faqlist"
                  >
                    <div class="accordion-body">
                      Eleifend mi in nulla posuere sollicitudin aliquam ultrices
                      sagittis orci. Faucibus pulvinar elementum integer enim.
                      Sem nulla pharetra diam sit amet nisl suscipit. Rutrum
                      tellus pellentesque eu tincidunt. Lectus urna duis
                      convallis convallis tellus. Urna molestie at elementum eu
                      facilisis sed odio morbi quis
                    </div>
                  </div>
                </div>
                <!-- # Faq item-->

                <div class="accordion-item">
                  <h3 class="accordion-header">
                    <button
                      class="accordion-button collapsed"
                      type="button"
                      data-bs-toggle="collapse"
                      data-bs-target="#faq-content-4"
                    >
                      <span class="num">4.</span>
                      Ac odio tempor orci dapibus. Aliquam eleifend mi in nulla?
                    </button>
                  </h3>
                  <div
                    id="faq-content-4"
                    class="accordion-collapse collapse"
                    data-bs-parent="#faqlist"
                  >
                    <div class="accordion-body">
                      Dolor sit amet consectetur adipiscing elit pellentesque
                      habitant morbi. Id interdum velit laoreet id donec
                      ultrices. Fringilla phasellus faucibus scelerisque
                      eleifend donec pretium. Est pellentesque elit ullamcorper
                      dignissim. Mauris ultrices eros in cursus turpis massa
                      tincidunt dui.
                    </div>
                  </div>
                </div>
                <!-- # Faq item-->

                <div class="accordion-item">
                  <h3 class="accordion-header">
                    <button
                      class="accordion-button collapsed"
                      type="button"
                      data-bs-toggle="collapse"
                      data-bs-target="#faq-content-5"
                    >
                      <span class="num">5.</span>
                      Tempus quam pellentesque nec nam aliquam sem et tortor
                      consequat?
                    </button>
                  </h3>
                  <div
                    id="faq-content-5"
                    class="accordion-collapse collapse"
                    data-bs-parent="#faqlist"
                  >
                    <div class="accordion-body">
                      Molestie a iaculis at erat pellentesque adipiscing
                      commodo. Dignissim suspendisse in est ante in. Nunc vel
                      risus commodo viverra maecenas accumsan. Sit amet nisl
                      suscipit adipiscing bibendum est. Purus gravida quis
                      blandit turpis cursus in
                    </div>
                  </div>
                </div>
                <!-- # Faq item-->
              </div>
            </div>
          </div>
        </div>
      </section>
      <!-- End Frequently Asked Questions Section -->

      <!-- ======= Recent Blog Posts Section ======= -->
      <section id="recent-posts" class="recent-posts sections-bg">
        <div class="container" data-aos="fade-up">
          <div class="section-header">
            <h2>Recent Blog Posts</h2>
            <p>
              Consequatur libero assumenda est voluptatem est quidem illum et
              officia imilique qui vel architecto accusamus fugit aut qui
              distinctio
            </p>
          </div>

          <div class="row gy-4">
            <div class="col-xl-4 col-md-6">
              <article>
                <div class="post-img">
                  <img
                    src="assets/img/blog/blog-1.jpg"
                    alt=""
                    class="img-fluid"
                  />
                </div>

                <p class="post-category">Politics</p>

                <h2 class="title">
                  <a href="blog-details.html"
                    >Dolorum optio tempore voluptas dignissimos</a
                  >
                </h2>

                <div class="d-flex align-items-center">
                  <img
                    src="assets/img/blog/blog-author.jpg"
                    alt=""
                    class="img-fluid post-author-img flex-shrink-0"
                  />
                  <div class="post-meta">
                    <p class="post-author">Maria Doe</p>
                    <p class="post-date">
                      <time datetime="2022-01-01">Jan 1, 2022</time>
                    </p>
                  </div>
                </div>
              </article>
            </div>
            <!-- End post list item -->

            <div class="col-xl-4 col-md-6">
              <article>
                <div class="post-img">
                  <img
                    src="assets/img/blog/blog-2.jpg"
                    alt=""
                    class="img-fluid"
                  />
                </div>

                <p class="post-category">Sports</p>

                <h2 class="title">
                  <a href="blog-details.html"
                    >Nisi magni odit consequatur autem nulla dolorem</a
                  >
                </h2>

                <div class="d-flex align-items-center">
                  <img
                    src="assets/img/blog/blog-author-2.jpg"
                    alt=""
                    class="img-fluid post-author-img flex-shrink-0"
                  />
                  <div class="post-meta">
                    <p class="post-author">Allisa Mayer</p>
                    <p class="post-date">
                      <time datetime="2022-01-01">Jun 5, 2022</time>
                    </p>
                  </div>
                </div>
              </article>
            </div>
            <!-- End post list item -->

            <div class="col-xl-4 col-md-6">
              <article>
                <div class="post-img">
                  <img
                    src="assets/img/blog/blog-3.jpg"
                    alt=""
                    class="img-fluid"
                  />
                </div>

                <p class="post-category">Entertainment</p>

                <h2 class="title">
                  <a href="blog-details.html"
                    >Possimus soluta ut id suscipit ea ut in quo quia et
                    soluta</a
                  >
                </h2>

                <div class="d-flex align-items-center">
                  <img
                    src="assets/img/blog/blog-author-3.jpg"
                    alt=""
                    class="img-fluid post-author-img flex-shrink-0"
                  />
                  <div class="post-meta">
                    <p class="post-author">Mark Dower</p>
                    <p class="post-date">
                      <time datetime="2022-01-01">Jun 22, 2022</time>
                    </p>
                  </div>
                </div>
              </article>
            </div>
            <!-- End post list item -->
          </div>
          <!-- End recent posts list -->
        </div>
      </section>
      <!-- End Recent Blog Posts Section -->

      <!-- ======= Contact Section ======= -->
      <section id="contact" class="contact">
        <div class="container" data-aos="fade-up">
          <div class="section-header">
            <h2>Contact</h2>
            <p>
              Nulla dolorum nulla nesciunt rerum facere sed ut inventore quam
              porro nihil id ratione ea sunt quis dolorem dolore earum
            </p>
          </div>

          <div class="row gx-lg-0 gy-4">
            <div class="col-lg-4">
              <div
                class="info-container d-flex flex-column align-items-center justify-content-center"
              >
                <div class="info-item d-flex">
                  <i class="bi bi-geo-alt flex-shrink-0"></i>
                  <div>
                    <h4>Location:</h4>
                    <p>A108 Adam Street, New York, NY 535022</p>
                  </div>
                </div>
                <!-- End Info Item -->

                <div class="info-item d-flex">
                  <i class="bi bi-envelope flex-shrink-0"></i>
                  <div>
                    <h4>Email:</h4>
                    <p>info@example.com</p>
                  </div>
                </div>
                <!-- End Info Item -->

                <div class="info-item d-flex">
                  <i class="bi bi-phone flex-shrink-0"></i>
                  <div>
                    <h4>Call:</h4>
                    <p>+1 5589 55488 55</p>
                  </div>
                </div>
                <!-- End Info Item -->

                <div class="info-item d-flex">
                  <i class="bi bi-clock flex-shrink-0"></i>
                  <div>
                    <h4>Open Hours:</h4>
                    <p>Mon-Sat: 11AM - 23PM</p>
                  </div>
                </div>
                <!-- End Info Item -->
              </div>
            </div>

            <div class="col-lg-8">
              <form
                action="forms/contact.php"
                method="post"
                role="form"
                class="php-email-form"
              >
                <div class="row">
                  <div class="col-md-6 form-group">
                    <input
                      type="text"
                      name="name"
                      class="form-control"
                      id="name"
                      placeholder="Your Name"
                      required
                    />
                  </div>
                  <div class="col-md-6 form-group mt-3 mt-md-0">
                    <input
                      type="email"
                      class="form-control"
                      name="email"
                      id="email"
                      placeholder="Your Email"
                      required
                    />
                  </div>
                </div>
                <div class="form-group mt-3">
                  <input
                    type="text"
                    class="form-control"
                    name="subject"
                    id="subject"
                    placeholder="Subject"
                    required
                  />
                </div>
                <div class="form-group mt-3">
                  <textarea
                    class="form-control"
                    name="message"
                    rows="7"
                    placeholder="Message"
                    required
                  ></textarea>
                </div>
                <div class="my-3">
                  <div class="loading">Loading</div>
                  <div class="error-message"></div>
                  <div class="sent-message">
                    Your message has been sent. Thank you!
                  </div>
                </div>
                <div class="text-center">
                  <button type="submit">Send Message</button>
                </div>
              </form>
            </div>
            <!-- End Contact Form -->
          </div>
        </div>
      </section>
      <!-- End Contact Section -->
    </main>
    <!-- End #main -->

    <!-- footer include -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <a
      href="#"
      class="scroll-top d-flex align-items-center justify-content-center"
      ><i class="bi bi-arrow-up-short"></i
    ></a>

    <div id="preloader"></div>

    <!-- Vendor JS Files -->
    <script src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${contextPath}/resources/vendor/aos/aos.js"></script>
    <script src="${contextPath}/resources/vendor/glightbox/js/glightbox.min.js"></script>
    <script src="${contextPath}/resources/vendor/purecounter/purecounter_vanilla.js"></script>
    <script src="${contextPath}/resources/vendor/swiper/swiper-bundle.min.js"></script>
    <script src="${contextPath}/resources/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="${contextPath}/resources/vendor/php-email-form/validate.js"></script>

    <!-- jQuery 라이브러리 추가 -->
    <script
      src="https://code.jquery.com/jquery-3.6.0.js"
      integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
      crossorigin="anonymous"
    ></script>

    <!-- Template Main JS File -->
    <script src="${contextPath}/resources/js/main.js"></script>
    <script src="${contextPath}/resources/js/mapAPI.js"></script>
  </body>
</html>
