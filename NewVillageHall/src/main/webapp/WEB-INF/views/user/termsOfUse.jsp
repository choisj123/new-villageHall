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

    <link rel="stylesheet" href="${contextPath}/resources/css/termsOfUse.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>

</head>
<body>

	<section class="termsOfUse-content">

    	<!--헤더 영역 시작-->
        <header>
            <!--로고 영역(클릭하면 메인페이지로 넘어감)-->
           
                <a href="${contextPath}">
                    <img src="${contextPath}/resources/images/logo.png" id="logo">
                </a>
           

        </header>
     	
         <form
    action="termsOfUse"
    method="Post"
    name="TermsOfUse-form"
    onsubmit= "return termsOfUserValidate()"
    >
        <fieldset class="terms-of-use-area">
    	

          
                <div class="villageHall">
                    마을회관
                </div>

                <h4>개인정보 수집·이용 (필수)</h4>
                <table id="table">

                    <tr>   <!-- 1행 -->
                        <th class="th">구분</th>   <!-- 1행 1열 -->
                        <th class="th">목적</th>   <!-- 1행 2열 -->
                        <th class="th">항목</th>   <!-- 1행 3열 -->
                        <th class="th">동의</th>   <!-- 1행 4열 -->
                    </tr>
        
                    <tr> <!-- 2행 -->
                        <td class="td1">필수정보</td> <!-- 2행 1열 -->
                        <td class="td">회원제 서비스 이용 / 본인확인</td> <!-- 2행 2열 -->
                        <td class="td">이메일(아이디), 비밀번호, 닉네임, &nbsp;&nbsp;&nbsp;휴대폰 번호</td> <!-- 2행 3열 -->
                        <td class="tdBtn">
                        	<div id="required">
                				&nbsp;<input type="checkbox" id="required1">
                			</div>  
                        </td>
                    </tr>


                </table>
                <p class="p">*서비스 제공을 위한 최소한의 개인정보이므로 동의를 해주셔야 서비스를 이용하실 수 있습니다.<p>


                <h4>위치기반 서비스 수집·이용 (필수)</h4>
                <table  id="table">

                   <tr>   <!-- 1행 -->
                       <th class="th">구분</th>   <!-- 1행 1열 -->
                       <th class="th">목적</th>   <!-- 1행 2열 -->
                       <th class="th">항목</th>   <!-- 1행 3열 -->
                       <th class="th">동의</th>   <!-- 1행 4열 -->
                   </tr>
       
                   <tr> <!-- 2행 -->
                       <td class="td1">필수정보</td> <!-- 2행 1열 -->
                       <td class="td">이동기기의 위치정보를 이용한 검색결과, 주변결과(맛집, 주변업체, 교통수단 등)제시</td> <!-- 2행 2열 -->
                       <td class="td">위치를 활용한 정보 수신</td> <!-- 2행 3열 -->
                       <td class="tdBtn">
                           <div id="required">
                               &nbsp;<input type="checkbox" id="required2">
                           </div> <!-- 2행 4열 --> 
                       </td>
                   </tr>


               </table>
               <p class="p">*위치기반 서비스 이용약관에 동의를 해주셔야 서비스를 이용하실 수 있습니다.<p>

               <h4>개인정보 수집·이용 (선택)</h4>
                 <table id="table">

                    <tr>   <!-- 1행 -->
                        <th class="th">구분</th>   <!-- 1행 1열 -->
                        <th class="th">목적</th>   <!-- 1행 2열 -->
                        <th class="th">항목</th>   <!-- 1행 3열 -->
                        <th class="th">동의</th>   <!-- 1행 4열 -->
                    </tr>
        
                    <tr> <!-- 2행 -->
                        <td class="td1">선택정보</td> <!-- 2행 1열 -->
                        <td class="td">마케팅 활용(이벤트, 맞춤형 광고)</td> <!-- 2행 2열 -->
                        <td class="td">휴대폰 번호, 쿠키정보</td> <!-- 2행 3열 -->
                        <td class="tdBtn">
                        	<div id="required">
                				&nbsp;<input type="checkbox" id="required2">
                			</div> <!-- 2행 4열 --> 
                        </td>
                    </tr>


                </table>
                <p class="p">*동의하지 않으셔도 서비스는 이용하실 수 있습니다.<p>


                <h4>광고성 정보 수신 (선택)</h4>
                <table id="table">

                    <tr>   <!-- 1행 -->
                        <th class="th">수신</th>   <!-- 1행 1열 -->
                        <th class="th">목적</th>   <!-- 1행 2열 -->
                        <th class="th">항목</th>   <!-- 1행 3열 -->
                        <th class="th">동의</th>   <!-- 1행 4열 -->
                    </tr>
        
                    <tr> <!-- 2행 -->
                        <td class="td1">&nbsp;이메일&nbsp;&nbsp;</td> <!--2행 1열 -->
                        <td class="td">	
                            마케팅 및 광고 활용을 위한 정보 수신 동의
                        </td> <!--2행 2열 -->
                        <td class="td">이메일(아이디)</td> <!-- 2행 3열 -->
                        <td class="tdBtn">
                        	 <div id="option1">
                    			&nbsp;<input type="checkbox" id="option1"> 
                			</div>
                        </td>
                     
                    </tr>

                    <tr> <!-- 3행 -->
                        <td class="td1">&nbsp;&nbsp;SMS&nbsp;</td> <!-- 3행 1열 -->
                        <td class="td">	
                            마케팅 및 광고 활용을 위한 정보 수신 동의
                        </td> <!-- 3행 2열 -->
                        <td class="td">전화번호</td> <!-- 3행 3열 -->
                        <td class="tdBtn">
                        	<div id="option2">
                    			&nbsp;<input type="checkbox" id="option1">
                			</div>
                		</td class="td"> <!-- 3행 4열 -->
                    </tr>


                </table>
                <p class="p">*동의하지 않으셔도 서비스는 이용하실 수 있습니다.<p>
                
                <br>
                <br>
                
                <button  id="nextBtn" >동의하고 가입하기</button><br>
                
                                  
   		<a href="javascript:void(0)" id="kakao-btn" onclick="kakaoLogin()">
        	 <img src="${contextPath}/resources/images/kakao_login_medium_wide.png">
    	</a>
     
        <!-- 카카오 스크립트 -->
		<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
		
		
	
		<script>
		
		Kakao.init('f05c8b2913faad9659a18a205defef9c'); //발급받은 키 중 javascript키를 사용해준다.
		console.log(Kakao.isInitialized()); // sdk초기화여부판단

		var kakaoEmail;
		var kakaoNickname;
		var kakaoUserKey;
			
		//카카오로그인
		function kakaoLogin() {
		
			   
			Kakao.Auth.login({
			    success: function (response) {
				    Kakao.API.request({
			  	     url: '/v2/user/me',
			  	     success: function (response) {
			            //console.log(response)
			           
			           
			            kakaoEmail = JSON.stringify(response.kakao_account.email);
			            kakaoNickname = JSON.stringify(response.properties.nickname);
			            kakaoUserKey = response.id;
			            
			            console.log("kakaoEmail",kakaoEmail);
			            console.log("kakaoNickname", kakaoNickname);
			            console.log("kakaoUserKey", kakaoUserKey);
			            
			           	process(kakaoEmail, kakaoNickname, kakaoUserKey);
				            
			
				          
			          		
			         //접속된 회원의 토큰값 출력됨
			        //alert(JSON.stringify(authObj));
			        
			      },
					fail: function(error) {
						
			    		console.log(error);
			      }
			      
			    });
				
			}
		});
				
		function process(kakaoEmail, kakaoNickname, kakaoUserKey){
				
			$.ajax({
		           url:"kakaoSignUp",
		           data:{"kakaoEmail": kakaoEmail, "kakaoNickname":kakaoNickname, "kakaoUserKey":kakaoUserKey },
		           type:"post",
		           //dataType:"JSON",
		           success:function(data){
		           //성공적으로 하고나면 이동할 url
		           	
		                
		              console.log("data",data);  
		              console.log("aJax",kakaoEmail);
		              console.log("aJax",kakaoNickname);   
		              console.log("aJax",kakaoUserKey);   
		                   
		              
		              location.href='login';
		              
		            },                            
		                   
		            error: function(error,status) {
		                		
		                console.log(error, status);
		            }
		            
		     	});
				
				
			}
			
				
				
		}
		
		</script> 
		
		
        </fieldset>
    
    </form>
</section>
        
        <!-- footer include -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    
     	
   
    <!-- jQuery 라이브러리 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
   
	 <!--FindPw.js 연결 -->
    <script src="${contextPath}/resources/js/termsOfUse.js"></script>

</body>
</html>