<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마을회관</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/findPw.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>

</head>
<body>
    <main>	
    
        <header>
            <!--로고 영역(클릭하면 메인페이지로 넘어감)-->
           
                <a href="${contextPath}">
                    <img src="${contextPath}/resources/images/logo.png" id="logo">
                </a>
           

        </header>
     	
    <form action="pwFindEmail"
       		 method="POST"
       		 name="pwFind-form"
       		 onsubmit="return pwFindValidate()">
    
        
<section class="main">
  <fieldset id="pwFind-field"> 
  <div class="pwFind-content">

    <div class="pwText">
      비밀번호 찾기
   </div>

     <label for="pwFind" class="pwFind">
       <span>•</span>이메일(아이디)로 인증
    </label>
        
   <div class="pwFind-input-area">
     <input
       type="email"
       id="userEmail"
       name="userEmail"
       class="input-text"
       placeholder="아이디(이메일)"
       maxlength="30"
       autocomplete="off"
       required
     />

     <!-- autocomplete="off" : 자동완성 미사용 -->
     <!-- required : 필수 작성 input 태그 -->

     <!-- 자바스크립로 코드 추가 예정 -->
     <button type="button" id="sendBtn" onclick="check()">인증번호</button>

     <div class="pwFind-input-area">
       <!-- cNumber -->
       <input
         type="text"
         id="cNumber"
         class="input-text"
         placeholder="인증번호 입력"
         maxlength="6"
         autocomplete="off"
       />
     <button type="button" id="cBtn">인증하기</button>
     </div>

     <!-- 5:00 타이머 / 인증되었습니다(녹색) / 인증 시간이 만료되었습니다.(빨간색) -->
     <span class="pwFind-message" id="cMessage"></span>
   
   </div>
   
<div id="display" style="display:none;">

   <label for="pwFind" class="pwFind">
    <span>•</span>비밀번호 변경
    </label>
    
   

  <div class="pwFind-input-area">
   
       <input
         type="password"
         id="newPw"
         name="newPw"
         class="input-text"
         placeholder=" 새 비밀번호"   
         maxlength="30">
         
   </div>
   

   <div class="pwFind-input-area">
       <input
         type="password"
         id="newPwConfirm"
         name="newPwConfirm"
         class="input-text"
         placeholder="새 비밀번호 확인"
         maxlength="30">
   </div>

   <span class="pwFind-message" id="pwMessage">
   영어, 숫자, 특수문자 6~30글자 사이로 작성해주세요.</span>
   
   <br>
   
   <button id="info-update-btn" >변경 하기</button>
   
</div>

   
 </form>

</div>

</fieldset> 

</section>    


   	 	<!-- 푸터 -->
    	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
   	
    </main>

	



    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   
	 <!--FindPw.js 연결 -->
    <script src="${contextPath}/resources/js/findPw.js"></script>

</body>
</html>