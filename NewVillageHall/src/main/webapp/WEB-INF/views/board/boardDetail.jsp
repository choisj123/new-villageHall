<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pagination" value="${map.pagination}" />
<c:set var="commentList" value="${map.commentList}" />


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
    href="${contextPath}/resources/css/boardDetail.css"
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
</head>
<body>
    <main>
        <!-- header include -->
        <jsp:include page="/WEB-INF/views/common/header.jsp" />
  
        <!-- 바디 부분 시작 -->
        <section class="body">
          
          <!-- 메인 콘텐츠 -->
          <section class="right-body">
          	<div id="boardContainer">
				<div class="boardBox">
					<div class="boardBoxHeader">#${board.categoryName}</div>
					<div class="boardBoxTitle">${board.boardTitle}</div>
					<div class="boardBoxEtc">
						<div >${board.userNickname}</div>
						<div>${board.boardCreateDate}</div>
						<div><i class="fa-sharp fa-regular fa-eye"></i> ${board.readCount}</div>
						<div><i class="fa-regular fa-heart"></i> ${board.likeCount}</div>
					</div>
					<div class="boardBoxContent">${board.boardContent}</div>
				</div>


            <%--여기까지!!!!!!!!!!!!!!!!!!!!!!!!! --%>
	          		<c:if test="${loginUser != null && board.categoryNo != 1 && board.categoryNo != 2}"> 
	          			<a href="${contextPath}/board/like?boardNo=${board.boardNo}" id="like"><i class="fa-regular fa-heart fa-lg"></i> 좋아요 ${board.likeCount}</a>				
	          		</c:if>
            	<div class="board-btn-area">
	          		<c:if test="${loginUser.userNickname == board.userNickname || loginUser.administer == 'Y'}">
	          			<button type="button" id="updateBtn" onclick="location.href='${contextPath}/board/writeBoard?mode=update&boardNo=${board.boardNo}'" >수정</button>
	          			<button type="button" id="deleteBtn" onclick="location.href='${contextPath}/board/deleteBoard?boardNo=${board.boardNo}'" >삭제</button>
	          		</c:if>
            	</div>
            
          	</div>
          	
          	
          	<c:if test="${board.categoryNo != 1 && board.categoryNo != 2}">
          		<div id="commentContainer">
	          		<c:choose>
	          			<c:when test="${empty commentList}">
	          				<div>
	          					<hr>
	          					<span>등록된 댓글이 없습니다.</span>
	          				</div>
	          			</c:when>
	          			<c:otherwise>
	          				<c:forEach var="comment" items="${commentList}">
	          					<div class="commentDetail" id="commentDetail-${comment.commentNo}">	          						
	          						<div class="profile-area">
	          							<div class="profile-image-area">
	          								<img class ="profile-img" src="${contextPath}${comment.profileImg}"><br>
	          							</div>
	          							<span class="comment-userName">${comment.userNickname}</span>
	          						</div>
	          						<div id="commentContent">
	          							<span class= "comment-content">${comment.commentContent}</span><br>
	          								          								          							          							
	          							<div class="commentEditForm" style="display:none;">
	          								<form action="${contextPath}/comment/updateComment">
	          									<input type="hidden" name="commentNo" value="${comment.commentNo}">
	          									<input type="hidden" name="boardNo" value="${board.boardNo}">	          								
										   		<textarea style= "resize:none; width: 650px; height: 60px;" id="updateCommentContent" name="commentContent">${comment.commentContent}</textarea>
										   		
										   		<div class= "btn-area">
											   		<button class="saveCommentButton">저장</button>
											   		<button type="button" id="cancelCommentButton" class="cancelCommentButton">취소</button>
										   		
										   		</div>
										   		
										   </form>
										   
										</div>
											
	          						</div>
	          						<div class= "createDate">
	          							<span class= "comment-createDate">${comment.commentCreateDate}</span>
	          						</div>
	          						<c:if test="${loginUser.userNickname == comment.userNickname || loginUser.administer == 'Y'}">
	          							<div class="editButton">
	          								<button id="updateCommentButton" class="updateCommentButton">수정</button>&nbsp;
	          								<form action="${contextPath}/comment/deleteComment" method="get">
	          									<input type="hidden" name="commentNo" value="${comment.commentNo}">
	          									<input type="hidden" name="boardNo" value="${board.boardNo}">
	          									<button id="deleteCommentButton">삭제</button>
	          								</form>
	          							</div>
	          						</c:if>	          						
	          					</div>
	          				</c:forEach>
	          			</c:otherwise>
	          		</c:choose>
	          	</div>
	          	
	          	
	          	<c:if test="${loginUser != null}">          		
	          		<div>	          			
	          			<br>
	          			<form action="${contextPath}/comment/insertComment?boardNo=${board.boardNo}&userNo=${loginUser.userNo}" method="post">
	          				<div class="insertComment">
	          				<div>
	          					<textarea id="insertCommentContent" name="commentContent" placeholder="댓글을 입력해주세요."></textarea>
	          				</div>
	          				<div>
	          					<button id="insertCommentBtn">댓글 등록</button>
	          				</div>
	          				</div>
	          			</form>
	          		</div>          			
	          	</c:if>
	          	<c:if test="${loginUser == null}">
	          		<div>	          			
	          			<br>
	          			<form action="${contextPath}/comment/insertComment?boardNo=${board.boardNo}&userNo=${loginUser.userNo}" method="post">
	          				<div class="insertComment">
	          				<div>
	          					<textarea id="insertCommentContent" name="commentContent" placeholder="로그인 후 이용해주세요." disabled="disabled"></textarea>
	          				</div>
	          				<div>
	          					<button id="insertCommentBtn" disabled="disabled">댓글 등록</button>
	          				</div>
	          				</div>
	          			</form>
	          		</div>
	          	</c:if>	     
	          	
	          	
	          	<c:if test="${!empty commentList}">
	          		<div class="pagination-area">
	
		                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
		                <c:set var="url" value="boardDetail?boardNo=${board.boardNo}&cp="/>
		
		
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
	          	</c:if>
	          	
          	</c:if>
            
            
          </section>
        </section>
      </main>
  
      <!-- footer include -->
      <jsp:include page="/WEB-INF/views/common/footer.jsp" />
  
  
      <!-- main.js 연결 -->
      <!-- <script src="${pageContext.request.contextPath}/resources/js/main.js"></script> -->
      <script src="${contextPath}/resources/js/boardDetail.js"></script>

</body>
</html>