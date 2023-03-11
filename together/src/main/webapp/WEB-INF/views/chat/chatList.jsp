<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 채팅목록 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<div class="page-main">
	<div class="chat-list">
		<h3>채팅방 목록</h3>
	</div>
	<c:if test="${empty list}">
	<div class="result-display">표시할 채팅방이 없습니다.</div>
	</c:if>
	<c:if test="${!empty list}">
	<table class="chat-table">
	<tbody>
		<c:forEach var="chat" items="${list}">
		<tr>
				<th scope="cols" style="text-align:left">
			<div class="party-img">
					<c:if test="${!empty chat.party_photo_name}">
						<a href="chatDetail.do?party_num=${chat.party_num}">
							<img src="imageView.do?party_num=${chat.party_num}&party_type=2">
						</a>
					</c:if>
					<c:if test="${empty chat.party_photo_name}">
						<a href="chatDetail.do?party_num=${chat.party_num}">
							<img src="${pageContext.request.contextPath}/images/togetherLogo.jpg">
						</a>
					</c:if>
			</div>
				</th>
			<th scope="row" style="text-align:left" class="chat-name">
				<a href="chatDetail.do?party_num=${chat.party_num}"><br>
					<span>${chat.party_name}</span>
				</a>
			</th>
			<th scope="row" style="text-align:center" class="chat-reg-date">
				<span>${chat.party_reg_date}</span>
			</th>
		</tr>
		</c:forEach>
	</tbody>
	</table>
	</c:if>
</div>
<!-- 채팅목록 끝 -->
