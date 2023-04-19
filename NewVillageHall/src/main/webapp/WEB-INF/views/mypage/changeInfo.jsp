<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>

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
    	.tab > a:nth-of-type(1) {
			background-color: #55710f;
  			color: white;
  			text-decoration: none;
 			border-radius: 10px;
		}
		
  		@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@400;500&display=swap');
  		* {
    		font-family: 'IBM Plex Sans KR', sans-serif;
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

          <!-- 내 정보 -->
          <div id="myInfo" class="tabContent">
             <!-- 
             enctype : form 태그가 데이터를 서버로 제출할 때 
                       데이터의 인코딩 형식을 지정하는 속성

             1) application/x-www-form-urlencoded
                 - 모든 문자를 서버로 제출하기 전에 인코딩 (모든 데이터가 문자)
                   (form태그 기본값)

             2) multipart/form-data : 제출할 때 인코딩을 하지 않음
                 -> 모든 데이터가 원본 형태를 유지(파일이 파일상태로 서버로 제출)
                 (주의) multipart/form-data 로 설정 시 method는 무조건 POST
         -->
          
           <form action="profile" method="POST" name="myPage-form" 
            	enctype="multipart/form-data" onsubmit="return profileValidate()">
				<div class="profile-image-area">
					<c:if test="${empty loginUser.profileImg}">
                            <img src="${contextPath}/resources/images/profile.png" id="profile-image">
                    </c:if>

					<c:if test="${!empty loginUser.profileImg}">
                            <img src="${contextPath}${loginUser.profileImg}" id="profile-image">
                    </c:if>

                    <!-- 프로필 이미지 삭제 버튼 -->
                    <span id="delete-image">x</span>
                </div>
                
				<div class="profile-btn-area">
	                <label for="input-image">이미지 선택</label>
	                <input type="file" name="profileImage" id="input-image" accept="image/*">
	                <!-- accept="image/*" : 이미지 파일 확장자만 선택 허용 -->
	                <!-- accept="video/*" : 동영상 파일 확장자만 선택 허용 -->
	                <!-- accept=".pdf" : pdf파일만 선택 허용 -->
	
	                <button type="submit">변경하기</button>
                </div>
                    <!-- 삭제버튼(x)이 눌러짐을 기록하는 숨겨진 input 태그 -->
                    <!-- 0 : 안눌러짐   /   1: 눌러짐 -->
                <input type="hidden" name="delete" id="delete" value="0">
           </form>

                <form action="changeInfo" method="POST" name="myPage-form" onsubmit="return infoValidate()">
	                <div class="myPage-row">
	                    <label>이메일</label>
	                    <span>${loginUser.userEmail}</span>
	                </div>
	                <div class="myPage-row">
	                    <label>닉네임</label>
	                    <input type="text" name="newNickname" id="newNickname" value="${loginUser.userNickname}" maxlength="10" autofocus></input>
	                    <span id ="nicknameMessage"></span>
	                </div>
	                 <div class="myPage-row">
	                    <label>전화번호</label>
	                    <input type="tel" name="newTel" id="newTel" value="${loginUser.userTel}" maxlength="11"></input>
	               		<span id ="telMessage"></span>
	                </div>
	                
	               <button type="submit" id="info-update-btn">변경</button>
	            </form>
	            
	     

         </div>
       </section>
     </section>
   </main>

    <!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script src="${contextPath}/resources/js/myPage-myInfo.js"></script>

  </body>
</html>
