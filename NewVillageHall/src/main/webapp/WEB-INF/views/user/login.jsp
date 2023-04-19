<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인창</title>

   <!-- <link rel="stylesheet" href="${contextPath}/resources/css/login.css">-->
     <link rel="stylesheet" href="${contextPath}/resources/css/main.css">
     <link rel="stylesheet" href="${contextPath}/resources/css/login.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>

	<style>
		header > section:nth-child(1){
			boarder: none;
		}
	
	</style>

	
</head>
<body>
    <main>
       
        <!--헤더 영역 시작-->
        <header>
            <!--로고 영역(클릭하면 메인페이지로 넘어감)-->
           
                <a href="${contextPath}">
                    <img src="${contextPath}/resources/images/logo.png" id="logo">
                </a>

        </header>

      
            <section class="login-content">
           
            
            	<%-- if - else --%>
            	<c:choose>  
            		<%-- choose 내부에는 jsp 주석만 사용 --%>
            		
            		<%-- 로그인이 되어있지 않은 경우 --%>
       				<c:when test="${ empty sessionScope.loginUser}"> 
            		
		                <form action="login" method="Post" name="login-form" onsubmit="return loginValidate()">
		                   
		                    <!-- 아이디(이메일)/비밀번호/로그인버튼 영역 -->
		                    <fieldset id="id-pw-area">
		                    
		                    <div class="villageHall">
                				마을회관
            				</div>
		        
		                        <section>
		                            <input type="text" id="input-area" name="userEmail" placeholder="아이디(이메일)" value="${cookie.saveId.value}"><br>
                                                                                                 <%-- 현재 페이지 쿠키 중 "saveId"의 내용을 출력--%>                   
		                            <input type="password" id="input-area" name="userPw" placeholder="비밀번호">
		                        </section>
		        
		                        <section>
		                            <!-- button의 type 기본값은 submit -->
		                            <button class="loginBtn">로그인</button>
		                        </section>
		                        
		             <span>
                        <label>
                             <input type="checkbox" name="saveId" ${chk}  id="saveId"><span class="saveId">아이디 저장</span>
                        </label>
                    </span>
        
        
        
                    <span class="signUp-pwFind-btn">
      
                        <a href="${contextPath}/user/termsOfUse" class="a">회원가입</a> 
                        <span>|</span>
                        <a href="${contextPath}/user/pwFindEmail" class="a">비밀번호 찾기</a>

                    </span><br>
		                    		
		                     		<a href="javascript:void(0)" id="kakao-btn" onclick="kakaoLogin()">
        								 <img src="${contextPath}/resources/images/kakao_login_medium_wide.png">
    								</a>
    								
		                    </fieldset>
		                    
		                 
                            <%-- 쿠키에 saveId가 있는 경우--%>
                            <c:if test="${ !empty cookie.saveId.value}">

                                <%-- chk 변수 생성(page scope)--%>
                                <c:set var="chk" value="checked"/>

                            </c:if>
                                

    								
    								
    
       						 <!-- 카카오 스크립트 -->
						<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
						
						<!--***********************카카오로 시작하기 script *************************  -->




	<script>
	
	Kakao.init('f05c8b2913faad9659a18a205defef9c'); //발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단

	var userEmail;
	var userNickname;
	var userUserKey;
		
	//카카오로그인
	function kakaoLogin() {
		
		
		   
		Kakao.Auth.login({
		    success: function (response) {
			    Kakao.API.request({
		  	     url: '/v2/user/me',
		  	     success: function (response) {
		            //console.log(response)
		           
		           
		            userEmail = JSON.stringify(response.kakao_account.email);
		            userNickname = JSON.stringify(response.properties.nickname);
		            kakaoUserKey = response.id;
		            
		            console.log("userEmail",userEmail);
		            console.log("userNickname", userNickname);
		            console.log("kakaoUserKey", kakaoUserKey);
		            
		           	process(userEmail, userNickname, kakaoUserKey);
			            
		
			          
		          		
		         //접속된 회원의 토큰값 출력됨
		        //alert(JSON.stringify(authObj));
		        
		      },
				fail: function(error) {
					
		    		console.log(error);
		      }
		      
		    });
			
		}
	});
			
	function process(userEmail, userNickname, userUserKey){
			
		$.ajax({
	           url:"kakaoLogin",
	           data:{"userEmail": userEmail, "userNickname":userNickname, "kakaoUserKey":kakaoUserKey },
	           type:"post",
	           //dataType:"JSON",
	           success:function(userNo){
	           //성공적으로 하고나면 이동할 url

	              
	              if(userNo != 0) {
	            	  location.href='${contextPath}';
	              } else {
	            	  location.href='${contextPath}/user/termsOfUse';
	              }
	              
	     
	              
	            },                            
	                   
	            error: function(error,status) {
	                		
	                console.log(error, status);
	            }
	            
	     	});
			
			
		}
		
			
			
	}
	
	</script>
	
						
		                    
		                </form>
            		
            		</c:when>
            
            	</c:choose>
            	  
            </section>
           
            
            <!-- footer include -->
   			 <jsp:include page="/WEB-INF/views/common/footer.jsp" />
  

    </main>
    

    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   
    <!-- login.js 연결 -->
    <script src="${contextPath}/resources/js/login.js"></script>

</body>
</html>