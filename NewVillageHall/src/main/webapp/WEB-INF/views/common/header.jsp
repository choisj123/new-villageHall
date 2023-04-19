<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	  <!-- ======= Header ======= -->
  <section id="topbar" class="topbar d-flex align-items-center">
    <div class="container d-flex justify-content-center justify-content-md-between">
      <div class="contact-info d-flex align-items-center">
        <i class="bi bi-envelope d-flex align-items-center"><a href="mailto:contact@example.com">contact@villagehall.com</a></i>
      </div>
      <div class="social-links d-none d-md-flex align-items-center">
          <a
            href="${contextPath}/user/login"
            id="login"
            class="fa-solid fa-lock"
            >&nbsp;로그인</a
          >
                  <a href="${contextPath}/user/termsOfUse" id="signup">회원가입</a>
      </div>
    </div>
  </section><!-- End Top Bar -->

  <header id="header" class="header d-flex align-items-center">

    <div class="container-fluid container-xl d-flex align-items-center justify-content-between">
		<div></div>
      <nav id="navbar" class="navbar">
        <ul>
          <li><a href="#hero">홈</a></li>
          <li><a href="#services">전체</a></li>
          <li><a href="#portfolio">인기</a></li>
          <li><a href="#team">공지사항</a></li>
          <li><a href="blog.html">FAQ</a></li>
          <li><a href="#about">마을회관</a></li>
        </ul>
      </nav><!-- .navbar -->

      <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
      <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

    </div>
  </header><!-- End Header -->
  <!-- End Header -->

	
</body>
</html>