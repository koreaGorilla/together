<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<h2 class="align-center">SpringPage</h2>
<div class="align-right">
	<a href="${pageContext.request.contextPath}/calendar/calendar.do">일정</a>
    <a href="${pageContext.request.contextPath}/party/list.do">파티</a>
	<a href="${pageContext.request.contextPath}/review/list.do">리뷰</a>
	<a href="${pageContext.request.contextPath}/board/list.do">게시판</a>

	
	<c:if test="${!empty user && user.mem_auth == 2}">
		<a href="${pageContext.request.contextPath}/mypage/myPage.do">MY페이지</a>
	</c:if>
	
	<c:if test="${!empty user}">
		<img src="${pageContext.request.contextPath}/mypage/photoView.do" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user && !empty user.mem_name}">
		[<span class="user_name">${user.mem_name}</span>]
	</c:if>
	<c:if test="${!empty user && empty user.mem_name}">
		[<span class="user_name">${user.mem_mem_id}</span>]
	</c:if>
	
	<c:if test="${!empty user}">
		<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		<a href="#" id="chat" title="채팅">채팅</a>
	</c:if>
	
	<c:if test="${empty user}">
		<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
		<a href="${pageContext.request.contextPath}/find/findList.do">아이디/비번찾기</a>
	</c:if>
	
	<c:if test="${empty user || user.mem_auth < 9}">
		<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
	</c:if>
	
	<c:if test="${!empty user && user.mem_auth == 9}">
		<a href="${pageContext.request.contextPath}/main/admin.do">관리자메인</a>
	</c:if>
</div>
<script>
	$("#chat").on('click',function(e){
		e.preventDefault();
		window.open("/chat/chatList.do", "chat", "width=800, height=800, top=200, left=200");
	})
</script>
<!-- 상단 끝 -->