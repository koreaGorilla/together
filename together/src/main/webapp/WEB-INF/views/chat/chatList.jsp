<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 채팅목록 시작 -->
<div class="page-main">
	<h2>채팅목록</h2>
	<c:if test="${empty list}">
	<div class="result-display">표시할 채팅방이 없습니다.</div>
	</c:if>
	<c:if test="${!empty list}">
	<table class="striped-table">
		<tr class="menu">
			<td style="text-align:left">파티 이름</td>
			<td style="text-align:right">파티 생성일자</td>
		</tr>
		<c:forEach var="chat" items="${list}">
		<tr>
			<td style="text-align:left">
				<a href="chatDetail.do?party_num=${chat.party_num}"><br>
					<span>${chat.party_name}</span>
				</a>
			</td>
			<td style="text-align:right">
				<span>${chat.party_reg_date}</span>
			</td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
</div>
<!-- 채팅목록 끝 -->
