<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<div class="header">
	<div class="header-content">
		<div class="logo-cate">
			<div class="logo"><a href="${pageContext.request.contextPath}/main/main.do">Together</a></div>
			<div class="cate">
				<ul>
					<li><a href="${pageContext.request.contextPath}/notice/list.do">공지</a></li>
					<li><a href="${pageContext.request.contextPath}/party/list.do">파티</a></li>
					<li><a href="${pageContext.request.contextPath}/review/list.do">리뷰</a></li>
					<li><a href="">이벤트</a></li>
				</ul>
			</div>
		</div>
		<div class="button">
			<a href="${pageContext.request.contextPath}/member/login.do"><img src="${pageContext.request.contextPath}/image_bundle/lock.png" height="25"></a>
			<a href="${pageContext.request.contextPath}/main/main.do"><img src="${pageContext.request.contextPath}/image_bundle/home.png" height="25"></a>
		</div>
	</div>
	<!-- 나중에 수정해야 함 -->
	<div class="align-right">
		<a href="${pageContext.request.contextPath}/calendar/calendar.do">일정</a>
	    <a href="${pageContext.request.contextPath}/party/list.do">파티</a>
		<a href="${pageContext.request.contextPath}/review/list.do">리뷰</a>
		<a href="${pageContext.request.contextPath}/notice/list.do">공지사항</a>
	
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
</div>
<script type="text/javascript">
	$("#chat").on('click', function(e){
		window.open("/chat/chatList.do", "chat", "width=600, height=700, top=200, left=200, resizable=no");
		})
</script>
<!-- 상단 끝 -->