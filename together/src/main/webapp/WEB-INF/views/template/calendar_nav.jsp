<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Calendar 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<c:if test="${!empty party.party_num}">
			<li><a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party.party_num}">파티 메인</a></li>
			<li><a href="${pageContext.request.contextPath}/calendar/calendar.do?party_num=${party.party_num}">일정</a></li>
		</c:if>
		
		<c:if test="${empty party.party_num}">
			<li><a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party_num}">파티 메인</a></li>
			<li><a href="${pageContext.request.contextPath}/calendar/calendar.do?party_num=${party_num}">일정</a></li>
		</c:if>
		
		<li>파티원 목록</li>
		<li>게시판</li>
		
		<li>
			<a href="#" id="chatParty" title="채팅">파티 채팅</a>
			
			<!-- 윈도우창으로 채팅 오픈 -->
			<script type="text/javascript">
				$("#chatParty").on('click', function(e){
					event.preventDefault();
					window.open("${pageContext.request.contextPath}/chat/chatDetail.do?party_num=${party.party_num}", "chat", "width=600, height=700, top=200, left=200, resizable=no");
				});
			</script>
		</li>
	</ul>
</div>
<!-- Calendar 메뉴 끝 -->


