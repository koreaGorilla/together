<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>      
<!-- Calendar 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<li><a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party.party_num}">파티 메인</a></li>
		<li>파티원 목록</li>
		<li>게시판</li>
		<li><a href="${pageContext.request.contextPath}/calendar/calendar.do">일정</a></li>
		<li>채팅</li>
	</ul>
</div>
<!-- Calendar 메뉴 끝 -->