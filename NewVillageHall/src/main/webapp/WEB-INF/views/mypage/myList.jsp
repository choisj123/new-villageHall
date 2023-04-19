<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!-- map에 저장된 값을 각각 변수에 저장 -->
<c:set var="boardName" value="${map.boardName}" />
<c:set var="pagination" value="${map.pagination}" />
<c:set var="list" value="${map.list}" />

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
    
    <link rel="stylesheet"
    href= "${contextPath}/resources/css/myPage-myBoard.css" />

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
    
    <c:choose>
    	<c:when test="${param.type == 1}">
    		<style>
    			.tab > a:first-of-type {
					background-color: #55710f;
  					color: white;
  					text-decoration: none;
 					border-radius: 10px;
				}
    		</style>
    	</c:when>
    	<c:when test="${param.type == 2}">
    		<style>
    			.tab > a:nth-of-type(2) {
					background-color: #55710f;
  					color: white;
  					text-decoration: none;
 					border-radius: 15px;
				}
    		</style>
    	</c:when>
    	<c:otherwise>
    		<style>
    			.tab > a:nth-of-type(3) {
					background-color: #55710f;
  					color: white;
  					text-decoration: none;
 					border-radius: 15px;
				}
    		</style>
    	</c:otherwise>
    </c:choose>
</head>
<body>
    <main>
        <!-- header include -->
  
        <!-- 내부 접근 절대 경로 -->
        <jsp:include page="/WEB-INF/views/common/header.jsp" />
  
        <!-- 바디 부분 시작 -->
        <section class="body">
          
  
          <!-- 메인 콘텐츠------------------------- -->
	        <section class="right-body">
	
	            <h2>마이 페이지</h2>
	            <hr>
	          <!-- 탭 메뉴 -->
	        	<div class="tab">
	          		<a href="${contextPath}/mypage/myList?type=1">내 글</a>
	          		<a href="${contextPath}/mypage/myList?type=2">내 댓글</a>
	          		<a href="${contextPath}/mypage/myList?type=3">내 좋아요</a>
				</div>
				
				<c:choose>
					<c:when test="${param.type == 1}">
						<!-- 내글 목록 리스트 -->
	            		<div id="myContent" class="tabContent">
	            			<h3>${boardName} 목록</h3>
	            			<table class="contentTable">
	                			<thead>
	                  				<tr>
	                    				<th>카테고리</th>
	                    				<th>제목</th>
	                    				<th>작성일</th>
	                    				<th>조회</th>
	                    				<th>좋아요</th>
	                  				</tr>
	                			</thead>
	
	                			<tbody id="myBoardList">
	                				<c:choose>
	                					<c:when test="${empty list}">
	                						<tr>
	                							<th colspan="5">게시글이 존재하지 않습니다.</th>
	                						</tr>
	                					</c:when>
	                		
	                					<c:otherwise>
	                						<c:forEach var="board" items="${list}">
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
	                								<td>${board.readCount}</td>
	                								<td>${board.likeCount}</td>
	                							</tr>
	                						</c:forEach>
	                					</c:otherwise>
	                				</c:choose> 
	                			</tbody>                
	              			</table>
	            		</div>
					</c:when>
					
					<c:when test="${param.type == 2}">
						<!--  내 댓글 목록 리스트 -->
            			<div id="myComment" class="tabContent">
              				<h3>${boardName} 목록</h3>
              				<table class="commentTable">
                				<tbody id="myCommentList">
                					<c:choose>
                						<c:when test="${empty list}">
                							<tr>
                								<th>작성한 댓글이 존재하지 않습니다.</th>
                							</tr>
                						</c:when>
                		
                						<c:otherwise>
                							<c:forEach var="comment" items="${list}">
                								<tr>
                									<td>
                										<a href="${contextPath}/board/boardDetail?boardNo=${comment.boardNo}">
                											<span>${comment.commentContent}</span><br>
                											<span>${comment.commentCreateDate}</span><br>
                											<span>${comment.boardTitle}</span><br>
                										</a>
                										<hr>
                									</td>
                								</tr>
                							</c:forEach>
                						</c:otherwise>
                					</c:choose>                  
                				</tbody>
              				</table>
            			</div>
					</c:when>
					
					<c:otherwise>
						<!--  내 좋아요 목록 리스트 -->
            			<div id="myLike" class="tabContent">
              				<h3>${boardName} 목록</h3>
              				<table class="likeTable">
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

                				<tbody id="myLikeList">
                					<c:choose>
                						<c:when test="${empty list}">
                							<tr>
                								<th colspan="6">좋아요를 누른 게시글이 존재하지 않습니다.</th>
                							</tr>
                						</c:when>
                		
                						<c:otherwise>
                							<c:forEach var="board" items="${list}">
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
					</c:otherwise>
				</c:choose>
				
	            
	            <div class="pagination-area">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
                <c:set var="url" value="myList?type=${param.type}&cp="/>


                <ul class="pagination">
                    <!-- 첫 페이지로 이동 -->
                    <li><a href="${url}1${sURL}">&lt;&lt;</a></li>

                    <!-- 이전 목록 마지막 번호로 이동 -->
                    <li><a href="${url}${pagination.prevPage}${sURL}">&lt;</a></li>

                    <!-- 범위가 정해진 일반 for문 사용 -->
                    <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">

                        <c:choose>
                            <c:when test="${i == pagination.currentPage}">
                                <li><a class="current">${i}</a></li>
                            </c:when>

                            <c:otherwise>
                                <li><a href="${url}${i}${sURL}">${i}</a></li>        
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                    
                    <!-- 다음 목록 시작 번호로 이동 -->
                    <li><a href="${url}${pagination.nextPage}${sURL}">&gt;</a></li>

                    <!-- 끝 페이지로 이동 -->
                    <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>

                </ul>
            </div>          
			</section>
        	<!-- 메인 콘텐츠 종료-------------------------------------- -->
		</section>
	</main>
  
    <!-- footer include -->
    <!-- 내부 접근 절대 경로 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
  

  
	<!-- main.js 연결 -->
	<!-- <script src="${pageContext.request.contextPath}/resources/js/main.js"></script> -->
	<script src="${contextPath}/resources/js/myPage-myBoard.js"></script>

</body>
</html>