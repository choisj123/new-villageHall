<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마을회관</title>

    <link
    rel="stylesheet"
    href="${contextPath}/resources/css/main.css"
    />
    
    <link
    rel="stylesheet"
    href="${contextPath}/resources/css/popularBoard.css"
    />
    

    <!-- fontawesome -->
    <script
      src="https://kit.fontawesome.com/2f1bf0eac7.js"
      crossorigin="anonymous"
    ></script>
    
          <!-- jQuery 라이브러리 추가 -->
      <script
        src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"
      ></script>
    
    <style>
    	@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@400;500&display=swap');
  		* {
    		font-family: 'IBM Plex Sans KR', sans-serif;
  		}
    </style>   
    
    <script src="${contextPath}/resources/js/popularBoard.js"></script>
</head>
<body>
    <main>
        <!-- header include -->
  
        <!-- 내부 접근 절대 경로 -->
        <jsp:include page="/WEB-INF/views/common/header.jsp" />
  
        <!-- 바디 부분 시작 -->
        <section class="body">
          
  
          <!-- 메인 콘텐츠 -->
          <section class="right-body">
          	<h2><a href="${contextPath}/board/popularBoard?sortBy=like" style="text-decoration: none; color: black; padding-left: 30px;">인기게시판</a></h2>
            
            <% String sortBy = (String)request.getAttribute("sortBy"); %>
                
          	<form id="sortBy">
            	<select id="sortBySelect" name="sortBy" onchange="this.form.submit()" style="margin-left: 40px;">
             		<option value="like" <% if(sortBy.equals("like")) { %> selected <% } %>>좋아요순</option>
              		<option value="read" <% if(sortBy.equals("read")) { %> selected <% } %>>조회수순</option>              		
            	</select>
            </form>
          
          
          <div id="popularBoard">
          	<table class="popularBoardTable">
          		<thead>
          			<tr>
	                    <th>카테고리</th>
	                    <th>제목</th>
	                    <th>작성일</th>
	                    <th>작성자</th>
	                    <th>조회</th>
	                    <th>좋아요</th>
	               </tr>
          		</thead>
          		
          		<tbody id="popularBoardList">
          			<c:choose>
          				<c:when test="${empty boardList}">
	                		<tr>
	                			<th colspan="6">게시글이 존재하지 않습니다.</th>
	                		</tr>
	                	</c:when>
	                	
	                	<c:otherwise>
	                		<c:forEach var="board" items="${boardList}">
	                			<tr>
	                				<td>${board.categoryName}</td>
	                				<c:choose>
	                					<c:when test="${board.commentCount > 0}">
	                						<td><a href="${contextPath}/board/boardDetail?boardNo=${board.boardNo}">${board.boardTitle}<span style="color: #55710f;"> [${board.commentCount}]</span></a></td>
	                					</c:when>
	                					<c:otherwise>
	                						<td><a href="${contextPath}/board/boardDetail?boardNo=${board.boardNo}">${board.boardTitle}</a></td>
	                					</c:otherwise>
	                				</c:choose>
	                				<td>${board.boardCreateDate}</td>
	                				<td>${board.userNickname}</td>
	                				<td>${board.readCount}</td>
	                				<td>${board.likeCount}</td>
	                			</tr>
	                		</c:forEach>
	                	</c:otherwise>
          			</c:choose>
          		</tbody>
          	</table>
          </div>
          </section>
        </section>
        
      </main>
  
      <!-- footer include -->
      <!-- 내부 접근 절대 경로 -->
      <jsp:include page="/WEB-INF/views/common/footer.jsp" />
  

  
      <!-- main.js 연결 -->
      <!-- <script src="${pageContext.request.contextPath}/resources/js/main.js"></script> -->

		
</body>
</html>