console.log("termsOfUse.js loaded");

// 유효성 검사 여부를 기록할 객체 생성
/*
const checkObj = {
	
    "required1" : false, 
    "required2" : false, 
    "option1":true,
    "option2":true 


}*/

/****** 취소버튼 */
/*

var cancelBtn = document.getElementById("cancelBtn");

cancelBtn.addEventListener('click', function(){
	
	history.back();
	
	
});*/


/******필수동의 항목 유효성 */

/*
var checkbox1 = document.querySelector("input[id=required1]");
var checkbox2 = document.querySelector("input[id=required2]");

checkbox1.addEventListener( 'change', function() {
	
    if(this.checked) {
		console.log("체크박스1");
        checkObj.required1 = true;
    } 
});

checkbox2.addEventListener( 'change', function() {
    if(this.checked) {
        console.log("체크박스2");
        checkObj.required2 = true;
    }
});*/


/*******************유효성 확인  */

function termsOfUserValidate(){

	console.log("js loaded")
    const required1 = document.getElementById("required1");
    const required2 = document.getElementById("required2");


    // 약관 동의 체크 여부
    // - 체크박스요소.checked  : 체크 시 true, 해제 시 false 반환

    if( !required1.checked ){ // 체크를 안했을 때
        alert("필수동의 항목을 동의해주시기 바랍니다.");
        required1.focus();
        return false;
    }
    
      if( !required2.checked ){ // 체크를 안했을 때
        alert("필수동의 항목을 동의해주시기 바랍니다.");
        required2.focus();
        return false;
    }

    return true;
}







