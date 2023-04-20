// servlet에서 json 객체 불러오기
$.ajax({
        url : "board/kakaoMap",
        dataType : "json",   
        success : function( kakaoMapList ){
	      console.log(kakaoMapList);

$.ajax({
        url : "board/kakaoMapBoardRecent",
        dataType : "json", 
        success : function(kakaoBoardRecent){
          console.log(kakaoBoardRecent);

// map 함수 기본 정의 
var mapContainer = document.getElementById('map'),
    mapOption = { 
        center: new kakao.maps.LatLng("37.511643", "126.976631"), 
        level: 8
    }; 
    
 
// kakao library 메서드
// kakao.maps.Map은 카카오맵 생성자 함수
var map = new kakao.maps.Map(mapContainer, mapOption); 


// 데이터를 담을 배열추가
var markers = [];

// 데이터를 담을 배열추가
var markersData = [];

// 데이터를 담을 바열 추가
var navData = [];


// DB데이터를 배열에 삽입
for (var i = 0; i < kakaoMapList.length; i++) {
	// .push를 이용해 markersData 배열 안에 데이터 삽입
  markersData.push({
    marker : null,  
    name : kakaoMapList[i].userNickname,
    title : kakaoMapList[i].boardTitle,
    createAt : kakaoMapList[i].boardCreateDate,
    location: new kakao.maps.LatLng(kakaoMapList[i].latitude, kakaoMapList[i].longtitude),
    content : kakaoMapList[i].boardContent,
    category : kakaoMapList[i].categoryName,
    boardNo: kakaoMapList[i].boardNo,
    like : kakaoMapList[i].likeCount,
    photoUrl : kakaoMapList[i].profileImg ? kakaoMapList[i].profileImg.substr(1) : "",
    // 여기서 ? 는 조건연산자로 null일경우 value1, 아닐경우 value2 반환 
    comment : kakaoMapList[i].commentCount
  });
}

for(var i = 0; i < kakaoBoardRecent.length; i++){
	navData.push({
		name : kakaoBoardRecent[i].userNickname,
		title : kakaoBoardRecent[i].boardTitle,
		createAt : kakaoBoardRecent[i].boardCreateDate,
		category : kakaoBoardRecent[i].categoryName,
		boardNo : kakaoBoardRecent[i].boardNo,
		photoUrl : kakaoBoardRecent[i].profileImg ? kakaoBoardRecent[i].profileImg.substr(1) : ""
	});
	
}

// marker에 위치 , 맵, 카테고리 삽입
for (var i = 0; i < markersData.length; i++) {
	// kakao.maps.Marker는 kakao라이브러리로 marker 생성 함수
  var marker = new kakao.maps.Marker({
    position: markersData[i].location,
    map: map,
    category: markersData[i].category
  });

  markersData[i].marker = marker;

// 위에서 정의한 markers배열에 marker 데이터 삽입
  markers.push(marker);

// 열려있는 인포윈도우를 담을 변수 설정
var openedInfowindow = null;

// 배열에 담은 데이터를 정의하는 인포윈도우 이벤트 리스너
  kakao.maps.event.addListener(marker, 'click', (function(marker ,i) {
	// 여기서 marker는 마커객체, i는 마커의 인덱스 반환
      return function() {
	const contentWithoutImg = markersData[i].content.replace(/<img[^>]*>/g, '');
	// kakao.maps.InfoWindow는 kakao라이브러리로 인포윈도우 생성 함수
          var infowindow = new kakao.maps.InfoWindow({
              content: 
              '<div class="infowindow-container">' +
              	'<div class="infowindow-header">' + 
                	'<div class="inwi-left">' +
                		'<div class="map-profile-area">' +
                			'<img class= "profile" src="' + markersData[i].photoUrl + '">' +
                		'</div>' +
                 	'</div>' +
                	'<div class="inwi-right">' +
						'<div>' + markersData[i].name + '</div>' +
                 		'<div class="time">' + markersData[i].createAt + '</div>' + 
                 		'<div class="category"> #' +  markersData[i].category + '</div>' +
                	'</div>' +
              	'</div>' +
            	  '<div class="infowindow-content">' +
              		'<div class="info-title">' + markersData[i].title + '</div>' +
              		'<div class="info-content">' + contentWithoutImg + '</div>' +
               '</div>'+
               '<div class="infowindow-footer">' +
               		'<div class="info-like">❤️ ️' + markersData[i].like + '</div>' +
               		'<div class="info-count">댓글(' + markersData[i].comment + ')</div>' +
			  		"<div class='move'><a class='move' href='http://localhost:8080/VillageHall/board/boardDetail?boardNo=" +
				      markersData[i].boardNo +
				      "&cp=1&type=3' onclick ='clickBoardFunction(" +
				      markersData[i].boardNo +
				      "); '> 게시글로 이동 >>" + 
				      "</a></div>" +
			   '</div>' +
             '</div>'

          });
          // 다른 마커를 클릭했을때 이전에 클릭한 마커를 닫히게 하는 기능
          // 인포윈도우가 null이 아닐때 인포윈도우를 닫음
          if(openedInfowindow !== null){
			openedInfowindow.close();
		  }
          // 인포윈도우 이벤트 리스너
          // 인포윈도우의 marker가 위치한 map위에 띄우는 코드
          infowindow.open(map, marker);
          openedInfowindow = infowindow;
          // map을 클릭했을때 인포윈도우가 닫히는 함수
          map.addListener('click',  function() {
            infowindow.close();
            openedInfowindow = null;
          });
      }
      // 클로저 함수로 
  })(marker, i));
}

// option을 정의하는 함수
let category = "전체";

function showMarkersByCategory(newCategory) {
  category = newCategory; // 전역 변수인 category 값을 업데이트
  const filteredNavData = navData.filter((item) => {
    return category === "전체" || item.category === category;
  });
  
  
  const placesList = document.getElementById("placesList");
  placesList.innerHTML = ""; // 이전 목록 삭제
  for (let i = 0; i < filteredNavData.length; i++) {
    const li = document.createElement("li");
	const content =
	  "<div class='listCategory'>#" + filteredNavData[i].category + "</div>" +
      "<div class='listTitle'><a class='listTitle' href='http://localhost:8080/VillageHall/board/boardDetail?boardNo=" +
      filteredNavData[i].boardNo +
      "&cp=1&type=3' onclick ='clickBoardFunction(" +
      filteredNavData[i].boardNo +
      "); '>" + 
      filteredNavData[i].title +
      "</a></div>" +
      "<div class='listCreateDate'>" +
      filteredNavData[i].createAt +
      "</div>" +
      "<hr>";
    li.innerHTML = content;
    placesList.appendChild(li);
  }
  
  
  function clickBoardFunction(boardNo) {
  const boardUrl = `http://localhost:8080/VillageHall/board/boardDetail?boardNo=${boardNo}&cp=1&type=3`;
  window.location.href = boardUrl;
}


  
  for (var i = 0; i < markersData.length; i++) {
    if (category === "전체" || markersData[i].category === category) {
      markers[i].setVisible(true);
    } else {
      markers[i].setVisible(false);
    }
  }
}  
   showMarkersByCategory("전체");

// option 이벤트 리스너
document.getElementsByName("category").forEach(function (element) {
    element.addEventListener("change", function () {
      showMarkersByCategory(this.value);
    });
  });
  
  showMarkersByCategory("전체"); // 초기값 설정


  },
 error : function(request, status, error){
            console.log("2번째 AJAX 에러 발생");
            console.log("상태코드 : " + request.status); // 404, 500
        }
});

},

error : function(request, status, error){
  console.log("1번째 AJAX 에러 발생");
  console.log("상태코드 : " + request.status); // 404, 500
}

});