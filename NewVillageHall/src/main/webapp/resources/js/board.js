// 검색창에 이전 검색기록 반영하기
(function(){
    const select = document.getElementById("search-key");

    // const option = select.children;
    const option = document.querySelectorAll("#search-key > option");

    const input = document.getElementById("search-query");

    if(select != null){ // 검색창이 화면이 존재할 때에만 코드 적용

        // 현재 주소에서 쿼리스트링(파라미터) 얻어오기
        const params = new URL(location.href).searchParams;

        // 얻어온 파라미터 중 key, query만 변수에 저장
        const key = params.get("key");
        const query = params.get("query");

        // input에 query 값 대입
        input.value = query;

        // option을 반복 접근해서 value와 key가 같으면 selected 속성 추가
        for(let op of option){
            if(op.value == key ){
                op.selected = true;
            }
        }
    }

})();

/*
// 검색 유효성 검사(검색어를 입력 했는지 확인)
function searchValidate(){

    const query = document.getElementById("search-query");

    if(query.value.trim().length == 0){ // 미작성
        query.value = ""; // 빈칸
        query.focus();

        return false;
    }

    return true;
}
*/