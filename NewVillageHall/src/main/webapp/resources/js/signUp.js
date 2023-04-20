console.log("signUp.js loaded");



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

	


// 유효성 검사 여부를 기록할 객체 생성
const checkObj = {
	
    "userEmail" : false, // 1) 정규표현식 이메일 형식(@) 맞는지 체크 
                           // 2) -> 맞으면 ajax 통신(중복검사)
                           // 3) 통과하면 true
    "userPw" : false, // 정규표현식 체크
    "userPwConfirm" : false, // 비밀번호랑 맞는지 체크
    "userNickname" : false, // 1) 정규표현식 닉네임 형식 맞는지 체크
                              // (영어/숫자/한글 2~10글자 사이) 
                              // 2) -> 맞으면 ajax 통신(중복검사)
                              // 3) 통과하면 true
    "userTel" : false, // 정규표현식 체크 + 중복검사(할 수 있으면)
    "sendEmail" : false,
    "checkNum" :false
}



/***********************이메일 확인******************************** */
// 이메일 유효성 검사


const userEmail = document.getElementById("userEmail");
const emailMessage = document.querySelector("#emailMessage");

userEmail.addEventListener("input", function(){

    // 입력이 되지 않은 경우
    if( userEmail.value.length == 0 ){
        //emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
        emailMessage.classList.remove("confirm", "error");

        checkObj.userEmail = false; // 유효 X 기록
        return;
    }

    // 입력된 경우
    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

    if( regExp.test(userEmail.value) ){ // 유효한 경우
        
        // ****** 이메일 중복 검사(ajax) 진행 예정 ******

        //  $.ajax( {k:v , k:v} );  // jQuery ajax 기본형태

        // userEmail.value  == 입력된 이메일

        $.ajax({
            url : "emailDupCheck",   
            data : { "userEmail" : userEmail.value },
            type : "GET", // 데이터 전달 방식 type
            
            success : function(result){
                // 비동기 통신(ajax)가 오류 없이 요청/응답 성공한 경우
                
                // 매개변수 result : servlet에서 출력된 result 값이 담겨있음
                // console.log(result);

                if(result == 1){ // 중복 O
                    emailMessage.innerText = "이미 사용중인 이메일 입니다.";
                    emailMessage.classList.add("error");
                    emailMessage.classList.remove("confirm");
                    checkObj.userEmail = false; // 유효 X 기록
                    

                } else { // 중복 X
                    emailMessage.innerText = "사용 가능한 이메일 입니다.";
                    emailMessage.classList.add("confirm");
                    emailMessage.classList.remove("error");
                    checkObj.userEmail = true; // 유효 O 기록
                }
            },
            
            error : function(){
                // 비동기 통신(ajax) 중 오류가 발생한 경우
                console.log("에러 발생");
            }

        });
        


    }else{
        emailMessage.innerText = "이메일 형식이 유효하지 않습니다.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");

        // 유효 X 기록
    }

}); 



//*****************인증번호 발송/확인 ********************************

// 인증번호 보내기
const sendBtn = document.getElementById("sendBtn");
const cMessage = document.getElementById("cMessage");

// 타이머에 사용될 변수
let checkInterval; // setInterval을 저장할 변수
let min = 4;
let sec = 59;

sendBtn.addEventListener("click", function(){

console.log("click");
    if( checkObj.userEmail ){ // 유효한 이메일이 작성되어 있을 경우에만 메일 보내기

        $.ajax({
            url : "sendEmail"  ,
            data : { "inputEmail" : userEmail.value },
            type : "GET",
            success : function(result){
                console.log("이메일 발송 성공");
                console.log(result);

                // 인증 버튼이 클릭되어 정상적으로 메일이 보내졌음을 checkObj에 기록
                checkObj.sendEmail = true;

            },
            error : function(){
                console.log("이메일 발송 실패")
            }
        });


        // Mail 발송 Ajax 코드는 동작이 느림....
        // -> 메일은 메일대로 보내고, 타이머는 버튼이 클릭 되자 마자 바로 실행
        // --> ajax 코드가 비동기이기 때문에 메일이 보내지는 것을 기다리지 않고
        //      바로 다음 코드가 수행된다!!

        // 5분 타이머
        // setInerval(함수, 지연시간) : 지연시간이 지난 후 함수를 수행 (반복)

        cMessage.innerText = "5:00"; // 초기값 5분
        min = 4;
        sec = 59; // 분, 초 초기화

        cMessage.classList.remove("confirm");
        cMessage.classList.remove("error");

        // 변수에 저장해야지 멈출 수 있음
        checkInterval = setInterval(function(){
            if(sec < 10) sec = "0" + sec;
            cMessage.innerText = min + ":" + sec;

            if(Number(sec) === 0){
                min--;
                sec = 59;
            }else{
                sec--;
            }

            if(min === -1){ // 만료
                cMessage.classList.add("error");
                cMessage.innerText = "인증번호가 만료되었습니다.";

                clearInterval(checkInterval); // checkInterval 반복을 지움
            }

        }, 1000); // 1초 지연 후 수행

        
        alert("인증번호가 발송되었습니다. 이메일을 확인해주세요.");
    }
});


// 인증번호 확인 클릭시에 대한 동작
const cNumber = document.getElementById("cNumber");
const cBtn = document.getElementById("cBtn");
// + cMessage, memberEmail 요소도 사용

cBtn.addEventListener("click", function(){

    // 1. 인증번호 받기 버튼이 클릭되어 이메일 발송되었는지 확인
    if(checkObj.sendEmail){

        // 2. 입력된 인증번호가 6자리가 맞는지 확인
        if( cNumber.value.length == 6 ){ // 6자리 맞음

            $.ajax({
                url : "checkNumber",
                data : { "cNumber" : cNumber.value,
                         "inputEmail" : userEmail.value },
                type : "GET",
                success : function(result){
                    console.log(result);  
                    // 1 : 인증번호 일치 O, 시간 만족O
                    // 2 : 인증번호 일치 O, 시간 만족X
                    // 3 : 인증번호 일치 X

                    if(result == 1){

                        clearInterval(checkInterval); // 타이머 멈춤     

                        cMessage.innerText = "인증되었습니다.";
                        cMessage.classList.add("confirm");
                        cMessage.classList.remove("error");
                        checkObj.checkNum = true;

                    } else if(result == 2){
                        alert("만료된 인증 번호 입니다.");
                        checkObj.checkNum = false;

                    } else{ // 3
                        alert("인증 번호가 일치하기 않습니다.");
                        checkObj.checkNum = false;
                    }


                },

                error : function(){
                    console.log("이메일 인증 실패")
                }
            });



        } else { // 6자리 아님
            alert("인증번호를 정확하게 입력해주세요.");
            cNumber.focus();
            checkObj.checkNum = false;
        }

    }else{ // 인증번호를 안받은 경우
        alert("인증번호 받기 버튼을 먼저 클릭해주세요.");
        checkObj.checkNum = false;
    }

});


/********************* 비밀번호 확인 ************************************* */

// 비밀번호 유효성 검사
const userPw = document.getElementById("userPw");
const userPwConfirm = document.getElementById("userPwConfirm");
const pwMessage = document.getElementById("pwMessage");

userPw.addEventListener("input", function(){

    if(userPw.value.length == 0){
        //pwMessage.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자 사이로 작성해주세요.";
        pwMessage.classList.remove("confirm", "error");

        checkObj.userPw = false; // 유효 X 기록
        return;
    }

    
    const regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,30}$/;

    if( regExp.test(userPw.value) ){ // 비밀번호 유효

        checkObj.userPw = true; // 유효 O 기록
        console.log(userPw.value)

        if(userPwConfirm.value.length == 0){ // 비밀번호 유효, 확인 작성 X
            pwMessage.innerText = "안전한 비밀번호 입니다.";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
        
        } else { // 비밀번호 유효, 확인 작성 O
        
            checkPw(); // 비밀번호 일치 검사 함수 호출()
        }

    }else{
        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");
        checkObj.userPw = false; // 유효 X 기록
    }
});



// 비밀번호 확인 유효성 검사

// 함수명() : 함수 호출(수행)
// 함수명   : 함수에 작성된 코드 반환
userPwConfirm.addEventListener("input", checkPw );
// -> 이벤트가 발생 되었을 때 정의된 함수를 호출하겠다


function checkPw(){ // 비밀번호 일치 검사
    // 비밀번호 / 비밀번호 확인이 같을 경우
    if(userPw.value == userPwConfirm.value){
        pwMessage.innerText = "비밀번호가 일치합니다.";
        pwMessage.classList.add("confirm");
        pwMessage.classList.remove("error");

        checkObj.userPwConfirm = true; // 유효 O 기록

    } else{
        pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");

        checkObj.userPwConfirm = false; // 유효 X 기록
    }
}


/******************************* 닉네임 ********************************* */


// 닉네임 유효성 검사
const userNickname = document.getElementById("userNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

userNickname.addEventListener("input", function(){

    // 입력되지 않은 경우
    if(userNickname.value.length == 0){
        //nicknameMessage.innerText = "영어/숫자/한글 2~10글자 사이로 작성해주세요.";
        nicknameMessage.classList.remove("confirm", "error");

        checkObj.userNickname = false; // 유효 X 기록
        return;
    }

    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if( regExp.test(userNickname.value) ){ // 유효한 경우
        

        // ****** 닉네임 중복 검사(ajax) 진행 예정 ******

        $.ajax({
            url : "nicknameDupCheck",  // 필수 작성 속성
            data : { "userNickname" : userNickname.value }, // 서버로 전달할 값(파라미터)
            type : "GET", // 데이터 전달 방식(기본값 GET)

            success : function(res){ // 비동기 통신 성공 시(에러 발생 X)

                // 매개변수 res : Servlet에서 응답으로 출력된 데이터가 저장

                if(res == 0){ // 닉네임 중복 X
                    nicknameMessage.innerText = "사용 가능한 닉네임 입니다.";
                    nicknameMessage.classList.add("confirm");
                    nicknameMessage.classList.remove("error");
                    checkObj.userNickname = true; // 유효 O 기록

                } else { // 닉네임 중복 O
                    nicknameMessage.innerText = "이미 사용중인 닉네임 입니다.";
                    nicknameMessage.classList.add("error");
                    nicknameMessage.classList.remove("confirm");
                    checkObj.userNickname = false; // 유효 O 기록
                }
            },

            error : function(){ // 비동기 통신 중 에러가 발생한 경우
                console.log("에러 발생");
            }
        });



    }else{
        nicknameMessage.innerText = "닉네임 형식이 유효하지 않습니다.";
        nicknameMessage.classList.add("error");
        nicknameMessage.classList.remove("confirm");

        checkObj.userNickname = false; // 유효 X 기록
    }

});

/**************************** 전화번호 확인************************* */

// 전화번호 유효성 검사
const userTel = document.getElementById("userTel");
const telMessage = document.getElementById("telMessage");

// ** input 이벤트 **
// -> 입력과 관련된 모든 동작(key관련, mouse관련, 붙여넣기)
userTel.addEventListener("input", function(){

    // 입력이 되지 않은 경우
    if(userTel.value.length == 0){
        //telMessage.innerText = "전화번호를 입력해주세요.(- 제외)";

        //telMessage.classList.remove("confirm");
        //telMessage.classList.remove("error");
        telMessage.classList.remove("confirm", "error");

        checkObj.userTel = false; // 유효하지 않은 상태임을 기록

        return;
    }

    // 전화번호 정규식
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

    if(regExp.test(userTel.value)){ // 유효한 경우
        telMessage.innerText = "유효한 전화번호 형식입니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");

        checkObj.userTel = true; // 유효한 상태임을 기록
        
    } else{ // 유효하지 않은 경우
        telMessage.innerText = "전화번호 형식이 올바르지 않습니다.";
        telMessage.classList.add("error");
        telMessage.classList.remove("confirm");

        checkObj.userTel = false; // 유효하지 않은 상태임을 기록
    }
});

/* *******************************회원가입 가능확인 **********************************/



// 회원가입 버튼 클릭 시 유효성 검사가 완료 되었는지 확인하는 함수
function signUpValidate(){

    // checkObj에 있는 모든 속성을 반복 접근하여
    // false가 하나라도 있는 경우에는 form태그 기본 이벤트 제거

    let str;

    for( let key  in checkObj ){ // 객체용 향상된 for문

        // 현재 접근 중인 key의 value가 false인 경우
        if( !checkObj[key] ){ 

            switch(key){
            case "userEmail":      alert("이메일이 유효하지 않습니다."); break;
            case "userPw":        alert("비밀번호가 유효하지 않습니다."); break;    
            case "userPwConfirm": alert("비밀번호 확인이 유효하지 않습니다."); break;
            case "userNickname":  alert("닉네임이 유효하지 않습니다."); break;
            case "userTel":       alert("전화번호가 유효하지 않습니다."); break;
            }

            //str += " 유효하지 않습니다.";

            //alert(str);

            document.getElementById(key).focus();
            
            return false; // form태그 기본 이벤트 제거
        }
        
        if(!checkObj.checkNum){
		
			alert("인증번호가 유효하지 않습니다.");			
			//checkObj.checkNum = false;
			return false;	
		}
    }

    return true; // form태그 기본 이벤트 수행

}



























