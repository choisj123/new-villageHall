<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!-- map에 저장된 값을 각각 변수에 저장 -->
<c:set var="boardName" value="${map.boardName}" />
<c:set var="pagination" value="${map.pagination}" />
<c:set var="boardList" value="${map.boardList}" />
<c:set var="categoryNo" value="${map.categoryNo}" />


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
    href="${contextPath}/resources/css/boardList.css"
    />
    

    <!-- fontawesome -->
    <script
      src="https://kit.fontawesome.com/2f1bf0eac7.js"
      crossorigin="anonymous"
    ></script>
    
    <script src="https://code.jquery.com/jquery-3.6.0.js" 
    integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" 
    crossorigin="anonymous"></script>   
</head>
<body>
    <main>
        <!-- header include -->
  
        <!-- 내부 접근 절대 경로 -->
        <jsp:include page="/WEB-INF/views/common/header.jsp" />
  
        <!-- 바디 부분 시작 -->
        <section class="body">
          
          <%-- 검색을 진행한 경우 key, query를 쿼리스트링 형태로 저장한 변수 생성 --%>
        <c:if test="${!empty param.key}">
            <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
        </c:if>
          
          <!-- 메인 콘텐츠 -->
          <section class="right-body">
          
			<h2><a href="${contextPath}/board/list?type=${param.type}&categoryNo=${param.categoryNo}" style="padding-left: 30px;">${boardName}</a></h2>
			
			<c:if test="${!empty param.key && param.type == 3}">
                <h3 style="margin-left:30px;"> "${param.query}" 검색 결과  </h3>
            </c:if>
            
			<% int categoryNo = (int)request.getAttribute("categoryNo"); %>
            <c:if test="${param.type == 3 && param.query == null}">
            	<form id="category">
            		<input type="hidden" name="type" value="3" >
            		<select name="categoryNo" id="categoryNo" onchange="this.form.submit()" style="margin-left: 40px;">
              			<option value="0" <% if(categoryNo == 0) { %> selected <% } %> >전체글</option>
              			<option value="3" <% if(categoryNo == 3) { %> selected <% } %> id="issue">이슈 🔍️</option>
              			<option value="4" <% if(categoryNo == 4) { %> selected <% } %> id="delicious">맛집 🍽️</option>
              			<option value="5" <% if(categoryNo == 5) { %> selected <% } %> id="hobby">취미 🏂 </option>
              			<option value="6" <% if(categoryNo == 6) { %> selected <% } %> id="friend">친목 👫</option>
              			<option value="7" <% if(categoryNo == 7) { %> selected <% } %> id="recommend">추천 👍</option>
              			<option value="8" <% if(categoryNo == 8) { %> selected <% } %> id="etc">기타 😎</option>
            		</select>
            	</form>
            </c:if>
            
            <c:if test="${param.type == 3 && param.query != null}">
            	<form id="category" >
            		<input type="hidden" name="type" value="3">
            		<input type="hidden" name="key" value="${param.key}">
            		<input type="hidden" name="query" value="${param.query}">
            		<select name="categoryNo" id="categoryNo" onchange="this.form.submit()" style="margin-left: 30px;">
              			<option value="0" <% if(categoryNo == 0) { %> selected <% } %> >전체글</option>
              			<option value="3" <% if(categoryNo == 3) { %> selected <% } %> id="issue">이슈 🔍️</option>
              			<option value="4" <% if(categoryNo == 4) { %> selected <% } %> id="delicious">맛집 🍽️</option>
              			<option value="5" <% if(categoryNo == 5) { %> selected <% } %> id="hobby">취미 🏂 </option>
              			<option value="6" <% if(categoryNo == 6) { %> selected <% } %> id="friend">친목 👫</option>
              			<option value="7" <% if(categoryNo == 7) { %> selected <% } %> id="recommend">추천 👍</option>
              			<option value="8" <% if(categoryNo == 8) { %> selected <% } %> id="etc">기타 😎</option>
            		</select>
            	</form>
            </c:if>
	
			<div id='boardListOutBox'>
          	<c:choose>
				<c:when test="${param.type == 1 || param.type == 3}">
					<div id="boardList">
          				<table class="boardListTable">
          					<thead>
          						<tr>
          							<th>카테고리</th>
	                    			<th>제목</th>	                    
	                    			<th>작성자</th>
	                    			<th>작성일</th>
	                    			<th>조회수</th>
	                    			<c:if test="${param.type == 3}">
	                    				<th>좋아요</th>
	                    			</c:if>	                    			
	               				</tr>
          					</thead>
          		
          					<tbody id="noticeBoardList">
          						<c:choose>
          							<c:when test="${empty boardList}">
	                					<tr>
	                						<c:choose>
	                							<c:when test="${param.type == 3}">
	                								<th colspan="6">게시글이 존재하지 않습니다.</th>
	                							</c:when>
	                							<c:otherwise>
	                								<th colspan="5">게시글이 존재하지 않습니다.</th>
	                							</c:otherwise>
	                						</c:choose>
	                						
	                					</tr>
	                				</c:when>
	                	
	                				<c:otherwise>
	                					<c:forEach var="board" items="${boardList}">
	                						<tr>
	                							<td>${board.categoryName}</td>	
	                							<c:choose>
	                								<c:when test="${board.commentCount > 0 && param.type == 3}">
	                									<td><a href="${contextPath}/board/boardDetail?boardNo=${board.boardNo}&cp=${pagination.currentPage}&type=${param.type}${sURL}">
	                										${board.boardTitle}<span style="color: #55710f;"> [${board.commentCount}]</span>
	                									</a></td>
	                								</c:when>
	                								<c:otherwise>
	                									<td><a href="${contextPath}/board/boardDetail?boardNo=${board.boardNo}&cp=${pagination.currentPage}&type=${param.type}${sURL}">
	                										${board.boardTitle}
	                									</a></td>
	                								</c:otherwise>
	                							</c:choose>                				
	                							
	                							<td>${board.userNickname}</td>
	                							<td>${board.boardCreateDate}</td>	                				
	                							<td>${board.readCount}</td>
	                							<c:if test="${param.type == 3}">
	                								<td>${board.likeCount}</td>
	                							</c:if>	                							
	                						</tr>
	                					</c:forEach>
	                				</c:otherwise>
          						</c:choose>
          					</tbody>
          				</table>
          			</div>
				</c:when>
				
				<%-- 여기부터 faq영역 --%>
				<c:otherwise>
          
				<!--FAQ 질문 검색 부분-->
          <article class="FAQ-search">
            <!--FAQ 질문 검색창-->
            <form action="${contextPath}/board/list" name="FAQ-search-form" method="get" id="boardSearch" onsubmit="return searchValidate()">

              <fieldset class="FAQ-search-fieldset">

                 
                 <input type="hidden" name="type" value="${param.type}">
                 <input type="hidden" name="categoryNo" value="2">
				 <input type="hidden" name="key" value="t">

                <input
                  type="text"
                  id="search-query"
                  name="query"
                  autocomplete="off"
                  placeholder="무엇이 궁금하실까요?"
                />

                <!--FAQ 질문 검색버튼-->
                <button
                  id="FAQ-search-btn"
                  class="fa-solid fa-magnifying-glass"
                ></button>
              </fieldset>
              <div id="quickSearchArea">
              	<a href="${contextPath}/board/list?type=2&categoryNo=2" class="quickSearchArea">전체</a>
              	<a href="${contextPath}/board/list?type=2&categoryNo=2&key=t&query=게시글" class="quickSearchArea">게시글</a>
              	<a href="${contextPath}/board/list?type=2&categoryNo=2&key=t&query=회원" class="quickSearchArea">회원</a>
              	<a href="${contextPath}/board/list?type=2&categoryNo=2&key=t&query=로그인" class="quickSearchArea">로그인</a>
              	<a href="${contextPath}/board/list?type=2&categoryNo=2&key=t&query=가입" class="quickSearchArea">가입</a>
              	<a href="${contextPath}/board/list?type=2&categoryNo=2&key=t&query=인증" class="quickSearchArea">인증</a>
              	<a href="${contextPath}/board/list?type=2&categoryNo=2&key=t&query=기타" class="quickSearchArea">기타</a>
              </div>
            </form>

            <!--FAQ 카테고리-->
          </article>
          
					<div class=faqSection>				
						<c:forEach var="board" items="${boardList}">
							<div class="FAQTitle">
								${board.boardTitle} <div class="icon">▼</div>
								<c:if test="${loginUser.administer == 'Y'}">
          							<button type="button" id= "updateBtn" onclick="location.href='${contextPath}/board/writeBoard?mode=update&boardNo=${board.boardNo}'" >수정</button>
          							<button type="button" id= "deleteBtn" onclick="location.href='${contextPath}/board/deleteBoard?boardNo=${board.boardNo}'" >삭제</button>
          						</c:if>
							</div>
							
                    		<div class="contents">${board.boardContent}</div>                    			
						</c:forEach>

					</div>
					
					<script>
					$(function() {
						  $("div.contents").prev("div.FAQTitle").on("click", function() {
						    if($(this).next("div.contents").css("display") == 'none') {
						      $("div.contents").slideUp();
						      $(this).next("div.contents").slideDown();
						    } else {
						      $(this).next("div.contents").slideUp();
						    }
						  });
						});
    				</script>			
				</c:otherwise>
				<%-- 여기까 faq영역 --%>
          	</c:choose>
          	
	          	
	            <c:if test ="${param.type == '3' && !empty loginUser}">
	            	<button type="button" id="boardWriteBtn" onclick="location.href='${contextPath}/board/writeBoard?mode=insert'"  >글쓰기</button>
	            </c:if>
          	
          	</div>
          	
          	
          	
         <div class="pagination-area" style="margin-top: 100px;">

                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
                
                
                <c:set var="url" value="list?type=${param.type}&categoryNo=${categoryNo}&cp="/>
                	
                	
                


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
            
            <!-- /board/list?type=1&cp=3 -->

            <!-- /board/list?type=1&cp=10 &key=t&query=안녕 -->
			<c:if test="${param.type != 2}">
				<div id="faq-search-area">
					<form action="${contextPath}/board/list" method="get" id="boardSearch" onsubmit="return searchValidate()">
						<input type="hidden" name="type" value="${param.type}">
						<input type="hidden" name="categoryNo" value="0">
						<!-- <input type="hidden" name="key" value="t"> -->
		
						<select name="key" id="search-key">
							<option value="t">제목</option>
							  <option value="c">내용</option>
							<option value="tc">제목+내용</option>
							<option value="w">작성자</option>
						</select>
		
						<input type="text" name="query"  id="faq-search-query" placeholder="검색어를 입력해주세요.">
		
						<button id="faq-search-btn">검색</button>
					</form>
				</div>
	 

			</c:if>
          </section>
        </section>
        
      </main>
  
      <!-- footer include -->
      <!-- 내부 접근 절대 경로 -->
      <jsp:include page="/WEB-INF/views/common/footer.jsp" />
  
      <!-- jQuery 라이브러리 추가 -->
      <script
        src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"
      ></script>
  
      <!-- main.js 연결 -->
      <!-- <script src="${pageContext.request.contextPath}/resources/js/main.js"></script> -->
		<script src="${contextPath}/resources/js/board.js"></script>
</body>
</html>