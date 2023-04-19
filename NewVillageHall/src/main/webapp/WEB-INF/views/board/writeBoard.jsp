<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>마을회관</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/writeBoard.css" />

    <!-- fontawesome -->
    <script
      src="https://kit.fontawesome.com/2f1bf0eac7.js"
      crossorigin="anonymous"
    ></script>
   
    <script
      src="https://code.jquery.com/jquery-3.6.0.js"
      integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
      crossorigin="anonymous"
    ></script>
    
    <!-- 썸머노트 라이브러리 연결 -->
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    
  </head>
  <body>
    <main>
    	<!-- header include -->
        <jsp:include page="/WEB-INF/views/common/header.jsp" />

      <!-- 바디 부분 시작 -->
      <section class="body">

        <!-- 메인 콘텐츠 -->
        <section class="right-body">

            <c:choose>
        		<c:when test="${board == null}">
        			<h2>글 작성</h2>
              
        		</c:when>
        		<c:otherwise>
        			<h2>게시글 수정</h2>
        		</c:otherwise>
        	</c:choose>
     
        	<c:choose>
        		<c:when test="${param.mode == insert}">
        		
        		</c:when>
        	</c:choose>
      
        	<form action="${contextPath}/board/writeBoard?mode=${param.mode}" method="post"
          class="board-write" onsubmit="return writeValidate()">          
          <input type="hidden" name="boardNo" value="${board.boardNo}">
            <hr />
            <c:choose>
            	<c:when test="${board != null}">
            		<% int categoryNo = (int)request.getAttribute("categoryNo"); %>
            		<select name="category" id="category">
              			<option value="">카테고리</option>
              			<c:if test="${loginUser.administer == 'Y'}">
              				<option value="1" <% if(categoryNo == 1) { %> selected <% } %> id="notice">공지사항</option>
              				<option value="2" <% if(categoryNo == 2) { %> selected <% } %> id="FAQ">FAQ</option>
              			</c:if>
              			<option value="3" <% if(categoryNo == 3) { %> selected <% } %> id="issue">이슈 🔍️</option>
              			<option value="4" <% if(categoryNo == 4) { %> selected <% } %> id="delicious">맛집 🍽️</option>
              			<option value="5" <% if(categoryNo == 5) { %> selected <% } %> id="hobby">취미 🏂 </option>
              			<option value="6" <% if(categoryNo == 6) { %> selected <% } %> id="friend">친목 👫</option>
              			<option value="7" <% if(categoryNo == 7) { %> selected <% } %> id="recommend">추천 👍</option>
              			<option value="8" <% if(categoryNo == 7) { %> selected <% } %> id="etc">기타 😎</option>
            		</select>
            	</c:when>
            	<c:otherwise>
            		<select name="category" id="category">            			
              			<option value="">카테고리</option>
              			<c:if test="${loginUser.administer == 'Y'}">
              				<option value="1" id="notice">공지사항</option>
              				<option value="2" id="FAQ">FAQ</option>
              			</c:if>
              			<option value="3" id="issue">이슈 🔍️</option>
              			<option value="4" id="delicious">맛집 🍽️</option>
              			<option value="5" id="hobby">취미 🏂 </option>
              			<option value="6" id="friend">친목 👫</option>
              			<option value="7" id="recommend">추천 👍</option>
              			<option value="8" id="etc">기타 😎</option>
            		</select>
            	</c:otherwise>            	
            </c:choose>
            <input
              type="text"
              name="boardTitle"
              id="boardTitle"
              placeholder="제목을 입력해주세요"
              size="125px" value="${board.boardTitle}"
            />
       		<!-- 내용 영역 -->
            <textarea id="summernote" name="boardContent">${board.boardContent}</textarea>

            <!-- 버튼 영역 -->
            <div class="board-btn-area">
				<c:if test="${param.mode == 'insert'}">
	                	<button type="submit" id="writebtn" onclick="">등록</button>
	           </c:if>
	            <c:if test="${param.mode == 'update'}">
	                	<button type="submit" id="writebtn">수정</button>
	            </c:if>
                <button type="button" onclick="location.href='${header.referer}'">이전으로</button>          
            </div>
             <input type="hidden" name="latitude" id="latitude"  />
    		<input type="hidden" name="longitude" id="longitude"  /> 
            
            <input type="hidden" name="mode" value="${param.mode}">
          </form>
        </section>
      </section>
    </main>
    
	 <!-- footer include -->
      <jsp:include page="/WEB-INF/views/common/footer.jsp" />
  
    
    <script src="${contextPath}/resources/js/writeBoard.js"></script>
  </body>
</html>
