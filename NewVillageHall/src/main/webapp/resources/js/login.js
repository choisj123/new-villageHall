let container = document.getElementById('container')

toggle = () => {
  container.classList.toggle('sign-in')
  container.classList.toggle('sign-up')
}

setTimeout(() => {
  container.classList.add('sign-in')
}, 200)


function loginValidate(){ // 로그인 유효성 검사
console.log("login.js loaded");
   
    // 이메일 입력 input 요소 
    const inputEmail = document.getElementsByName("userEmail")[0];

    // 비밀번호 입력 input 요소
    const inputPw = document.getElementsByName("userPw")[0];


    // 이메일이 입력되지 않은 경우 false를 반환
    if(inputEmail.value.trim().length == 0){
        // 문자열.trim() : 문자열 양쪽 공백을 제거
        // 문자열.length : 문자열 길이(몇 글자?)

        alert("이메일을 입력해주세요.");
        inputEmail.value = ""; // 이메일 input에 입력된 내용을 모두 삭제
        inputEmail.focus(); // 이메일 input에 포커스를 맞춰줌
        return false; // 기본 이벤트 제거를 위해 false 반환
       
    }

	  console.log("login2222.js loaded");
	  
    // 비밀번호를 입력하지 않은 경우 false 반환
    if(inputPw.value.trim() == ""){
        alert("비밀번호를 입력해주세요.");
        inputPw.value = "";
        inputPw.focus();
        return false;
    }


    return true; // form 태그 기본 이벤트 정상 수행
}


// 아이디 저장 체크박스가 체크 되었을 때 이벤트 처리

// radio, checkbox 체크 시 change 이벤트 발생
document.getElementById("saveId").addEventListener("change", function(){

    // 체크 여부 확인
    console.log(this.checked)
    // this : change 이벤트가 발생한 요소(체크박스)
    // 체크박스요소.checked   :  체크 여부 반환(true/false)

    // 체크박스요소.checked = true;  : 체크박스 체크
    // 체크박스요소.checked = false; : 체크박스 체크 해제


    if( this.checked ){ // 체크박스가 체크된 경우

        const str = "개인 정보 보호를 위해 개인 PC에서의 사용을 권장합니다. 개인 PC가 아닌 경우 취소를 눌러주세요.";

        //confirm(str) // 확인(true) / 취소(false) 대화상자

        if( !confirm(str)  ){ // 취소를 눌렀을 때
            this.checked = false; // 체크 해제
        }
    }


});


/***********************카카오로그인 **************************************** */

/***********************카카오로 시작하기 ************************* */
/*
Kakao.init('f05c8b2913faad9659a18a205defef9c'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단

var userEmail;
var userNickname;
var kakaoUserKey;
	
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
		
function process(userEmail, userNickname, kakaoUserKey){
		
	$.ajax({
           url:"kakaoTest",
           data:{"userEmail": userEmail, "userNickname":userNickname, "kakaoUserKey":kakaoUserKey },
           type:"post",
           //dataType:"JSON",
           success:function(data){
           //성공적으로 하고나면 이동할 url
           	
                
              console.log("data",data);  
              console.log("aJax",userEmail);
              console.log("aJax",userNickname);   
              console.log("aJax",kakaoUserKey);   
                   
              alert("성공");
              location.href='login';
              
            },                            
                   
            error: function(error,status) {
                		
                console.log(error, status);
            }
            
     	});
		
		
	}
	
		
		
} */

	