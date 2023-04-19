<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix = "c"
uri="http://java.sun.com/jsp/jstl/core" %>

<div class="tab">
  <a href="${contextPath}/mypage/changeInfo">내 정보 변경</a>

  <c:choose>
    <c:when test="${sessionScope.loginUser.kakaoUserKey == null}">
      <a href="${contextPath}/mypage/changePw">비밀번호 변경</a>
    </c:when>
    <c:otherwise> </c:otherwise>
  </c:choose>
  <a href="${contextPath}/mypage/deleteUser">회원 탈퇴</a>
</div>
