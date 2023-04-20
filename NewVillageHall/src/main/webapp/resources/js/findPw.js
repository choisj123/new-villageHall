console.log("FindPw.js loaded");

// 유효성 검사 여부를 기록할 객체 생성
const checkObj = {

	"userEmail" : false,
    "newPw" : false,
    "newPwConfirm" : false,
    "sendEmail" : false,
    "checkNum" :false
    
}



/***********************이메일 확인******************************** */
// 이메일 유효성 검사

// 타이머에 사용될 변수
let checkInterval; // setInterval을 저장할 변수
let min = 4;
let sec = 59;
            	

function check(){
	
	const userEmail = document.getElementById("userEmail");
	
	// 입력이 되지 않은 경우
    if( userEmail.value.length == 0 ){
        
        alert("이메일을 입력해주세요.");
        
        checkObj.userEmail = false;
        return;
    }
    
    // 입력된 경우
    
    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;
    
    if(regExp.test(userEmail.value)){
		
		   $.ajax({
            url : "emailDupCheck",   
            data : { "userEmail" : userEmail.value },
            type : "GET", // 데이터 전달 방식 type
            
            success : function(result){
                // 비동기 통신(ajax)가 오류 없이 요청/응답 성공한 경우
                
                // 매개변수 result : servlet에서 출력된 result 값이 담겨있음
                // console.log(result);

                if(result == 1){ // 중복 O
        
            		
            		console.log("해당 이메일 확인됨");
            		checkObj.userEmail = true;
            		
            		
            		const cMessage = document.getElementById("cMessage");
            		
            	
            		
            		$.ajax({
           			 url : "sendEmail"  ,
            		 data : { "inputEmail" : userEmail.value },
            		 type : "GET",
             		 success : function(result){
                		console.log("이메일 발송 성공");
                		
                		checkObj.sendEmail = true;

          	  },
           			 error : function(){
                		console.log("이메일 발송 실패")
           			 }
      		  });
      		  
      		  
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
                 

                } else { // 중복 X
                
                  	 alert("입력하신 정보로 가입 된 회원은 존재하지 않습니다.");
                  	 checkObj.userEmail = false; 
     
                }
            },
            
            error : function(){
                // 비동기 통신(ajax) 중 오류가 발생한 경우
                console.log("에러 발생");
            }

        });
        
	}else{
		
        alert("이메일 형식이 유효하지 않습니다.");
        checkObj.userEmail = false;
   
    }


}


// 인증번호 확인 클릭시에 대한 동작
const userEmail = document.getElementById("userEmail");
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
                        $("#display").show();
                       
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


/*************************다음 버튼 클릭시 이벤트***************************** */

/********************* 비밀번호 확인 ************************************* */

// 비밀번호 유효성 검사
const newPw = document.getElementById("newPw");
const newPwConfirm = document.getElementById("newPwConfirm");
const pwMessage = document.getElementById("pwMessage");

newPw.addEventListener("input", function(){

    if(newPw.value.length == 0){
        pwMessage.innerText = "영어, 숫자, 특수문자 6~30글자 사이로 작성해주세요.";
        pwMessage.classList.remove("confirm", "error");

        checkObj.newPw = false; // 유효 X 기록
        return;
    }

    const regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{6,30}$/;

    if( regExp.test(newPw.value) ){ // 비밀번호 유효

        checkObj.newPw = true; // 유효 O 기록
        console.log(newPw.value)

        if(newPwConfirm.value.length == 0){ // 비밀번호 유효, 확인 작성 X
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
newPwConfirm.addEventListener("input", checkPw );
// -> 이벤트가 발생 되었을 때 정의된 함수를 호출하겠다


function checkPw(){ // 비밀번호 일치 검사
    // 비밀번호 / 비밀번호 확인이 같을 경우
    if(newPw.value == newPwConfirm.value){
        pwMessage.innerText = "비밀번호가 일치합니다.";
        pwMessage.classList.add("confirm");
        pwMessage.classList.remove("error");

        checkObj.newPwConfirm = true; // 유효 O 기록

    } else{
        pwMessage.innerText = "비밀번호가 일치하지 않습니다.";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");

        checkObj.newPwConfirm = false; // 유효 X 기록
    }
    
}

/**********************변경 가능 확인************************* */


// 회원가입 버튼 클릭 시 유효성 검사가 완료 되었는지 확인하는 함수


function pwFindValidate(){

    // checkObj에 있는 모든 속성을 반복 접근하여
    // false가 하나라도 있는 경우에는 form태그 기본 이벤트 제거

    let str;

    for( let key  in checkObj ){ // 객체용 향상된 for문

        // 현재 접근 중인 key의 value가 false인 경우
        if( !checkObj[key] ){ 

            switch(key){
            case "userEmail":     alert("이메일이 유효하지 않습니다."); break;
            case "newPw":        alert("새 비밀번호가 유효하지 않습니다."); break;    
            case "newPwConfirm": alert("새 비밀번호 확인이 유효하지 않습니다."); break;
   
            }

           // str += " 유효하지 않습니다.";

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







