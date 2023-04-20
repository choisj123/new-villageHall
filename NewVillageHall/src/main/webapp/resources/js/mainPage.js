console.log("main.js loaded")

function selectMainPagePopularBoard() {
	const popularTbody = document.getElementById("popularTbody");
	
	$.ajax({
		url : "board/selectMainPagePopularBoard",
		type : "POST",
		dataType : "JSON",
		
		success : function(boardList) {
			
			popularTbody.innerHTML = "";
			for(let item of boardList) {
				const tr = document.createElement("tr");
				const td1 = document.createElement("td");
				if(item.commentCount > 0) {
					td1.innerHTML = '<a href="/VillageHall/board/boardDetail?boardNo=' + item.boardNo + '">' + item.boardTitle + '<span style="color: #55710f;"> [' + item.commentCount + ']</span></a>';
				} else {
					td1.innerHTML = '<a href="/VillageHall/board/boardDetail?boardNo=' + item.boardNo + '">' + item.boardTitle + '</a>';
				}
				
				const td2 = document.createElement("td");
				td2.innerHTML = item.readCount;
				
				tr.append(td1, td2);
				popularTbody.append(tr);				
			}
			
			
		},
		error : function(request, status, error) {
			console.log("ajax 에러발생");
			console.log("상태코드 : " + request.status); // 404, 500
		}
	});
}

function selectMainPageNotice() {
	const noticeTbody = document.getElementById("noticeTbody");
	
	$.ajax({
		url : "board/selectMainPageNotice",
		type : "POST",
		dataType : "JSON",
		
		success : function(boardList) {
			
			
			
			noticeTbody.innerHTML = "";
			for(let item of boardList) {
				const tr = document.createElement("tr");
				const td1 = document.createElement("td");
				td1.innerHTML = '<a href="/VillageHall/board/boardDetail?boardNo=' + item.boardNo + '">' + item.boardTitle + '</a>';
				const td2 = document.createElement("td");
				td2.innerHTML = item.readCount;
				
				tr.append(td1, td2);
				noticeTbody.append(tr);				
			}
			
			
		},
		error : function(request, status, error) {
			console.log("ajax 에러발생");
			console.log("상태코드 : " + request.status); // 404, 500
		}
	});
}

(function(){
	selectMainPageNotice();
})();
(function(){
	selectMainPagePopularBoard();
	
})();