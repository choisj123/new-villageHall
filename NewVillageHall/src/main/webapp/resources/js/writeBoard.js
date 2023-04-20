console.log("js loaded");
 

// 썸머노트 호출
$(document).ready(function() {
	$("#summernote").summernote({
	    placeholder: "내용을 입력해주세요",
	    tabsize: 2,
	    height: 500,
	    minHeight: null, // set minimum height of editor
	    maxHeight: null, // set maximum height of editor
	    focus: true,
	    lang: "ko-KR",
	    toolbar: [
		  ['fontname', ['fontname']],
		  ['fontsize', ['fontsize']],
	      ["style", ["style"]],
	      ["font", ["bold", "underline", "clear"]],
	      ["color", ["color"]],
	      ["para", ["ul", "ol", "paragraph"]],
	      ["table", ["table"]],
	      ["insert", ["link", "picture"]],
	      ["view", ["fullscreen", "codeview", "help"]],
	    ],
	      fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
		  fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
		   
		   /* 이미지 삽입 후 서버에 저장을 위한 callback */
		   
		  callbacks: {
        			onImageUpload : function(files, editor, welEditable) {
			            for (var i = files.length - 1; i >= 0; i--) {
			            	sendFile(files[i], this);
		            	}
					} 
	        	}
	 });
	  
	  
	/* 이미지 서버 저장 후 url 반환 받는 함수 */  
	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file', file);
	
		console.log("boardImg!!!!")
		$.ajax({
		   	data: form_data,
		   	type: "POST",
		   	url: 'boardImg',
		   	cache: false,
		   	contentType: false,
		   	enctype: 'multipart/form-data',
		   	dataType : "json",
		   	processData: false,
		   	success: function(image) {
	  		//filePath == url : 서버에 업로드된 url을 반환받아 <img> 태그 src에 저장
	  			var imageUrl = image.filePath + image.fileName
	  			var image = $("<img>").attr({src: imageUrl, width:"100%", height:"100%"})
	     		$(el).summernote('editor.insertImage', imageUrl );
	  			
	  			console.log("서버 업로드 성공");
	   		}
	 	});
	} 
});
  

 
let latitude = 0;
let longitude = 0;

(function() {
	// 현재 내 위치(위도, 경도) 받아오는 api 호출
	if (navigator.geolocation) {
	  navigator.geolocation.getCurrentPosition(
	    function(position) {
	      	latitude = position.coords.latitude;
       		longitude = position.coords.longitude;
	      	console.log("위도 경도 받아오기 ", latitude, longitude)
	      	console.log("위도 경도 받아오기 ", typeof latitude, typeof longitude)
	     
		     
		     $("#latitude").val(latitude)
			 console.log( $("#latitude").val(latitude));
			 console.log(typeof $("#latitude").val(latitude));
			 
			 $("#longitude").val(longitude)
			 console.log( $("#longitude").val(longitude) );
		
	    },
	    function(error) {
	      console.log(error);
	    }
	  );
	}  
	
})();



  

      // 게시글 작성 유효성 검사
function writeValidate(){
    const boardTitle = document.getElementsByName("boardTitle")[0];
    const boardContent = document.querySelector("[name='boardContent']");
	
	if(category.value == "" ){
		alert("카테고리를 선택해주세요.")
		return false;
	}

    if(boardTitle.value.trim().length == 0){
        alert("제목을 입력해주세요.");
        boardTitle.value = "";
        boardTitle.focus();
        return false;
    }

    if(boardContent.value.trim().length == 0){
        alert("내용을 입력해주세요.");
        boardContent.value = "";
        boardContent.focus();
        return false;
    }
    
    if(latitude == 0 || longitude == 0){
		alert("위치 정보를 받아오는 중입니다. 잠시 후 다시 시도해주세요.");
		return false;
		
	}
    
    return true;
  
}