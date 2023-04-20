/**
 * 
 */

 // 회원 탈퇴 유효성 검사
function deleteUserValidate(){
	console.log("js loaded")
    const userPw = document.getElementById("userPw");
    const agree = document.getElementById("agree");

    // 비밀번호 미작성
    if(userPw.value.trim().length == 0){
        alert("비밀번호를 입력해주세요.");
        userPw.focus();
        return false;
    }

    // 약관 동의 체크 여부
    // - 체크박스요소.checked  : 체크 시 true, 해제 시 false 반환

    if( !agree.checked ){ // 체크를 안했을 때
        alert("약관 동의 후 탈퇴 버튼을 클릭해주세요.");
        agree.focus();
        return false;
    }

    // 정말 탈퇴할지 확인
    // - window.confirm("내용") : 대화 상자에 확인/취소 생성
    //      확인 클릭 시 true / 취소 클릭 시 false
    //      window는 생략 가능
    
    if( !confirm("정말 탈퇴 하시겠습니까?") ){ //  취소를 누른 경우
        return false;
    }

    return true;
}