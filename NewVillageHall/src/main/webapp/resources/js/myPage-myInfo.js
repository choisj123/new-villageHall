const checkObj = {
	
    "newNickname" : false,

}

// 닉네임 유효성 검사
const newNickname = document.getElementById("newNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

newNickname.addEventListener("input", function(){

  
    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if( regExp.test(newNickname.value) ){ // 유효한 경우
        

        // ****** 닉네임 중복 검사(ajax) 진행 예정 ******

        $.ajax({
            url : "newNicknameDup",  // 필수 작성 속성
            data : { "newNickname" : newNickname.value }, // 서버로 전달할 값(파라미터)
            type : "GET", // 데이터 전달 방식(기본값 GET)

            success : function(result){ // 비동기 통신 성공 시(에러 발생 X)

                // 매개변수 res : Servlet에서 응답으로 출력된 데이터가 저장

                if(result == 0){ // 닉네임 중복 X
                    //nicknameMessage.innerText = "중복X";
                    checkObj.newNickname = true; // 유효 O 기록

                } else if(result == 1) { // 닉네임 중복 O
                    //nicknameMessage.innerText = "중복O";
                    checkObj.newNickname = false; // 유효 O 기록
                }
            },

            error : function(){ // 비동기 통신 중 에러가 발생한 경우
                console.log("에러 발생");
            }
        });



    }else{
        //nicknameMessage.innerText = "닉네임 형식X";
        checkObj.newNickname = false; // 유효 X 기록
    }

});



// 내 정보 수정 유효성 검사
function infoValidate(){
	console.log("js loaded..")
	
	const userNickname = document.getElementById("newNickname");
    const userTel = document.getElementById("newTel");
    


    const regExp1 = /^[a-zA-Z0-9가-힣]{2,10}$/;       // 닉네임 정규식
    const regExp2 = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/; // 전화번호 정규식
	

// 닉네임 중복검사

	if(checkObj.newNickname){
		
		alert("중복된 닉네임입니다.");
		return false;

	}

    // 닉네임 유효성 검사

    if(userNickname.value.length == 0){ // 미작성 시 : 닉네임을 입력해주세요.
        
        return printAlert(userNickname, "닉네임을 입력해주세요.");
    }

    if(!regExp1.test(userNickname.value)){ // 유효하지 않은 경우
        
        return printAlert(userNickname, "닉네임은 영어/숫자/한글 2~10 글자 사이로 작성해주세요.");
    }
    
 

    // 전화번호 유효성 검사
    if(userTel.value.length == 0){ // 미작성 시
        return printAlert(userTel, "전화번호를 입력해주세요.(- 제외)");
    }

    if(!regExp2.test(userTel.value)){ // 유효하지 않은 경우
        // alert(" 전화번호 형식이 올바르지 않습니다.");
        // memberTel.focus();
        // return false;

        return printAlert(userTel, "전화번호 형식이 올바르지 않습니다.");
    }
    

    return true; // form태그 기본 이벤트 수행

}




// 경고 출력 + 포커스 + false 반환  함수
function printAlert(el, message){ // 매개변수 el은 요소
    alert(message);
    el.focus();
    return false;
}


// 회원 프로필 이미지 변경(미리보기)
const inputImage = document.getElementById("input-image");

if(inputImage != null){ // inputImage 요소가 화면에 존재 할 때
 
    // input type="file" 요소는 파일이 선택 될 때 change 이벤트가 발생한다.
    inputImage.addEventListener("change", function(){
       
        // this : 이벤트가 발생한 요소 (input type="file")

        // files : input type="file"만 사용 가능한 속성으로
        //         선택된 파일 목록(배열)을 반환
        console.log(this.files)
        console.log(this.files[0]) // 파일목록에서 첫 번째 파일 객체를 선택

        if(this.files[0] != undefined){ // 파일이 선택되었을 때

            const reader = new FileReader();
            // 자바스크립트의 FileReader
            // - 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 사용하는 객체

            reader.readAsDataURL(this.files[0]);
            // FileReader.readAsDataURL(파일)
            // - 지정된 파일의 내용을 읽기 시작함.
            // - 읽어오는게 완료되면 result 속성 data:에 
            //   읽어온 파일의 위치를 나타내는 URL이 포함된다.  
            //  -> 해당 URL을 이용해서 브라우저에 이미지를 볼 수 있다.


            // FileReader.onload = function(){}
            // - FileReader가 파일을 다 읽어온 경우 함수를 수행
            reader.onload = function(e){ // 고전 이벤트 모델
                // e : 이벤트 발생 객체
                // e.target : 이벤트가 발생한 요소(객체) -> FileReader
                // e.target.result : FileReader가 읽어온 파일의 URL

                // 프로필 이미지의 src 속성의 값을 FileReader가 읽어온 파일의 URL로 변경
                const profileImage = document.getElementById("profile-image");

                profileImage.setAttribute("src", e.target.result);
                // -> setAttribute() 호출 시 중복되는 속성이 존재하면 덮어쓰기

                document.getElementById("delete").value = 0;
                // 새로운 이미지가 선택 되었기 때문에 1 -> 0(안눌러짐 상태)으로 변경
            }

        }
    });
}





// 이미지 선택 확인
function profileValidate(){
    const inputImage = document.getElementById("input-image");

    const del = document.getElementById("delete"); // hidden 타입


    if( inputImage.value == ""  &&  del.value == 0 ){ 
        // 빈문자열 == 파일 선택 X / del의 값이 0 == x를 누르지도 않았다
        // --> 아무것도 안하고 변경버튼을 클릭한 경우

        alert("이미지를 선택한 후 변경 버튼을 클릭해주세요.");
        return false;
    }

    return true;
}




// 프로필 이미지 옆 x버튼 클릭 시
document.getElementById("delete-image").addEventListener("click", function(){
    // 0 : 안눌러짐
    // 1 : 눌러짐

    const del = document.getElementById("delete");

    if(del.value == 0){ // 눌러지지 않은 경우

        // 1) 프로필 이미지를 기본 이미지로 변경
        document.getElementById("profile-image").setAttribute("src", "${contextPath}/resources/images/profile.png");                     

        // 2) input type="file"에 저장된 값(value)에 "" 대입 
        document.getElementById("input-image").value = "";

        del.value = 1; // 눌러진걸로 인식
    }

});



