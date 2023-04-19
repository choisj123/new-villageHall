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
    
        <script
      src="https://code.jquery.com/jquery-3.6.0.js"
      integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
      crossorigin="anonymous"
    ></script>
    
    <style>
    	.tab > a:nth-of-type(3) {
			background-color: #55710f;
  			color: white;
  			text-decoration: none;
 			border-radius: 15px;
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

          <!-- 회원 탈퇴 -->
          <div id="deleteUser" class="tabContent">
          	 <form action="deleteUser" method="POST" name="myPage-form" onsubmit="return deleteUserValidate()" >
				<c:choose>
	        		<c:when test="${sessionScope.loginUser.kakaoUserKey == null}">
	                    <div class="myPage-row">
	                        <label>비밀번호</label>
	                        <input type="password" name="userPw" id="userPw" maxlength="30">              
	                    </div>
	                 </c:when>
				</c:choose>
                    
                    <div class="myPage-row info-title">
                        <label>회원 탈퇴 약관</label>
                    </div>

                    <div id="deleteUser-terms">
					<p style= "font-weight:bold; color:red; font-size:13px">※ 회원 탈퇴 시 개인정보 및 마을회관에서 만들어진 모든 데이터는 삭제됩니다. <br>
							(단, 아래 항목은 표기된 법률에 따라 특정 기간 동안 보관됩니다. </p>
						<pre>
1. 계약 또는 청약철회 등에 관한 기록 보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 / 보존 기간 : 5년
2. 대금결제 및 재화 등의 공급에 관한 기록 보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 / 보존 기간 : 5년
3. 전자금융 거래에 관한 기록 보존 이유 : 전자금융거래법 보존 기간 / 5년
4. 소비자의 불만 또는 분쟁처리에 관한 기록 보존 이유 : 전자상거래 등에서의 소비자보호에 관한 법률 보존 기간 / 3년
5. 신용정보의 수집/처리 및 이용 등에 관한 기록 보존 이유: 신용정보의 이용 및 보호에 관한 법률 보존기간 / 3년
6. 전자(세금)계산서 시스템 구축 운영하는 사업자가 지켜야 할 사항 고시(국세청 고시 제 2016-3호) (전자세금계산서 사용자에 한함) : 5년 (단, (세금)계산서 내 개인식별번호는 3년 경과 후 파기)
						</pre>
						<p style="font-weight:bold; font-size:16px"> ▶ 회원탈퇴 시 유의사항 </p>
						<pre>
회원탈퇴 처리 후에는 회원님의 개인정보를 복원할 수 없으며, 회원탈퇴 진행 시 해당 이메일은 영구적으로 삭제되어 재가입이 불가합니다.

단, 상법 및 '전자상거래 등에서 소비자 보호에 관한 법률' 등 관련 법령의 규정에 의하여 다음과 같이 ‘거래 관련 관리의무 관계 확인’ 등을 이유로 일정 기간 보관됩니다.
 - 계약 또는 청약 철회 등에 관한 기록 : 5년
 - 대금결제 및 재화 등의 공급에 관한 기록 : 5년
 - 소비자의 불만 또는 분쟁 처리에 관한 기록 : 3년
 - 설문조사, 이벤트 등 일시적 목적을 위하여 수집한 경우 : 당해 설문조사, 이벤트 등의 종료 시까      지
제1조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4


제2조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4
						
						</pre>	



                    </div>

                    <div>
                        <input type="checkbox" name="agree" id="agree">
                        <label for="agree">위 약관에 동의합니다.</label>
                    </div>


                    <button id="info-update-btn">탈퇴</button>

                </form>
          	
          
                
         </div>
       </section>
     </section>
   </main>

    <!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script src="${contextPath}/resources/js/myPage-deleteUser.js"></script>


  </body>
</html>
