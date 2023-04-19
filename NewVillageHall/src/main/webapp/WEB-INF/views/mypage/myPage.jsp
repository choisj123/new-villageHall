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
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage.css" />

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
      @import url("https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@400;500&display=swap");
      * {
        font-family: "IBM Plex Sans KR", sans-serif;
      }
    </style>
  </head>
  <body>
    <main>
      <jsp:include page="/WEB-INF/views/common/header.jsp" />

      <section class="body">

        <section class="right-body">
          <h2 class="mypageH2"> 마이페이지</h2>
          <div class="myPage-box">
            <div>내 정보 수정</div>
            <form action="${contextPath}/mypage/myPage" method="post" >
              <div id="box-body">
                비밀번호
                <input type="password" name="inputPw" id="inputPw" autofocus />
                <button type="submit" id="pwBtn" onclick="return pwNull()">
                  확인
                </button>
                <c:if test="${!empty result}">
                  <script>
                    alert("비밀번호가 일치하지 않습니다.");
                    history.back();
                  </script>
                </c:if>
              </div>
            </form>
          </div>
        </section>
      </section>
    </main>

    <!-- footer include -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />



    <!-- <script src="resources/js/myPage.js"></script> -->
  </body>
</html>
