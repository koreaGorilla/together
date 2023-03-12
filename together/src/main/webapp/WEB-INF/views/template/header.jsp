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
				</ul>
			</div>
		</div>
		<div class="button">
			<ul class="button-ul">
				<li>
					<c:if test="${empty user}">
						<img src="${pageContext.request.contextPath}/image_bundle/lock.png" height="25">
	 					<ul class="dropdown-login">
							<li><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
							<li><a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a></li>
						</ul>
					</c:if>
 					<c:if test="${!empty user && user.mem_auth < 9}">
						<img src="${pageContext.request.contextPath}/image_bundle/my_page.png" height="25">
	 					<ul class="dropdown-mypage">
	 						<li>
	 							<div id="profile">
	 								<div id="profile-img"><img src="${pageContext.request.contextPath}/mypage/photoView.do"></div>
	 								<span>${user.mem_name} 님</span>
	 							</div>
	 						</li> 
							<li><a href="${pageContext.request.contextPath}/mypage/myPage.do">회원상세정보</a></li>
							<li><a href="${pageContext.request.contextPath}/mypage/myPage.do">나의 파티</a></li>
							<li><a href="${pageContext.request.contextPath}/mypage/myPage.do">나의 리뷰</a></li>
							<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
						</ul>
					</c:if> 
					<c:if test="${!empty user && user.mem_auth == 9}">
						<img src="${pageContext.request.contextPath}/image_bundle/my_page.png" height="25">
	 					<ul class="dropdown-login">
							<li><a href="${pageContext.request.contextPath}/main/admin.do">관리자페이지</a></li>
							<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
						</ul>
					</c:if>
				</li>
				<li><a href="${pageContext.request.contextPath}/main/main.do"><img src="${pageContext.request.contextPath}/image_bundle/home.png" height="25"></a></li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#chat").on('click', function(e){
		window.open("/chat/chatList.do", "chat", "width=600, height=700, top=200, left=200, resizable=no");
		})
</script>
<!-- 상단 끝 -->