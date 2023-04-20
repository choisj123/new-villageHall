console.log("myPage-changePw.js loaded");


// 비밀번호 변경 제출 시 유효성 검사
function changePwValidate(){
	console.log("js loaded..")

    
    const newPw = document.getElementsByName("newPw")[0];
    const newPwConfirm = document.getElementsByName("newPwConfirm")[0];

    // 비밀번호 정규표현식
    const regEx = /^[\w!@#_-]{6,30}$/;


    // 새 비밀번호
    // 미작성
    if(newPw.value.trim().length == 0){
		return printAlert(newPw, "새 비밀번호를 입력해주세요.");

    }

    // 유효하지 않은 경우
    if(!regEx.test(newPw.value)){
		return printAlert(newPw, "영어, 숫자, 특수문자(!,@,#,-,_) 6~30 글자 사이로 작성해주세요.");

    }


    // 새 비밀번호 확인
    // 미작성
    if(newPwConfirm.value.trim().length == 0){
        return printAlert(newPwConfirm, "새 비밀번호 확인을 입력해주세요.");
    }


    // 새 비밀번호 != 새 비밀번호 확인
    if(newPw.value != newPwConfirm.value){
        return printAlert(newPwConfirm, "새 비밀번호가 일치하지 않습니다.");
    }
    

    return true; // 위 조건을 모두 수행하지 않은 경우 true 반환
}


// 경고 출력 + 포커스 + false 반환  함수
function printAlert(el, message){ // 매개변수 el은 요소
    alert(message);
    el.focus();
    return false;
}


