<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix = "c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>마을회관</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/main.css" />
    <link
      rel="stylesheet"
      href="${contextPath}/resources/css/myPage-myInfo.css"
    />
    <!-- fontawesome -->
    <script
      src="https://kit.fontawesome.com/2f1bf0eac7.js"
      crossorigin="anonymous"
    ></script>

    <!-- jQuery 라이브러리 추가 -->
    <script
      src="https://code.jquery.com/jquery-3.6.0.js"
      integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
      crossorigin="anonymous"
    ></script>
    
    <style>
      .tab > a:nth-of-type(2) {
        background-color: #55710f;
        color: white;
        text-decoration: none;
        border-radius: 15px;
      }

      @import url("https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@400;500&display=swap");
      * {
        font-family: "IBM Plex Sans KR", sans-serif;
      }
    </style>
  </head>
  <body>
    <main>
      <!-- header -->
      <jsp:include page="/WEB-INF/views/common/header.jsp" />

      <!-- 바디 부분 시작 -->
      <section class="body">

        <!-- 메인 콘텐츠 -->
        <section class="right-body">
          <h2>마이페이지</h2>

          <!-- 탭 메뉴 -->
          <jsp:include page="/WEB-INF/views/mypage/myInfoTab.jsp" />

          <!-- 내 정보 수정 -->
          <div id="changePw" class="tabContent">
            <form
              action="changePw"
              method="POST"
              name="myPage-form"
              onsubmit="return changePwValidate()"
            >
              <div class="myPage-row">
                <label>새 비밀번호</label>
                <input type="password" name="newPw" maxlength="30" />
              </div>

              <div class="myPage-row">
                <label>새 비밀번호 확인</label>
                <input type="password" name="newPwConfirm" maxlength="30" />
              </div>

              <button id="info-update-btn">변경하기</button>
            </form>
          </div>
        </section>
      </section>
    </main>

    <!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script src="${contextPath}/resources/js/myPage-changePw.js"></script>

  </body>
</html>
