<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${count == 0}">
	<div class="result-display">표시할 회원정보가 없습니다.</div>
</c:if>
<c:if test="${count > 0}">
	<table class="striped-table">
		<h2>파티원 목록</h2>
		<tr>
			<th>이름</th>
			<th>파티 가입일</th>
			<th>파티번호</th>
			<th>회원분류</th>
		</tr>
		<c:forEach var="member" items="${list}">
			<c:if test="${member.party_auth == 0}">
				<tr>
					<td>${member.mem_name}</td>
					<td>${member.partymem_reg_date}</td>
					<td>${member.party_auth}</td>
					<td>
						<c:if test="${member.party_auth==0}">일반회원</c:if> 
						<c:if test="${member.party_auth==9}">파티장</c:if>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
</c:if>


<c:if test="${count > 0}">
	<table class="striped-table">
		<h2>대기회원 목록</h2>
		<tr>
			<th>이름</th>
			<th>회원분류</th>
		</tr>
		<c:forEach var="member" items="${list}">
			<c:if test="${member.party_auth == 1}">
				<tr>
					<td>${member.mem_name}</td>
					<td>${member.partymem_reg_date}</td>
					<td>
						<c:if test="${member.party_auth==1}">대기회원</c:if>
						<c:if test="${member.party_auth==9}">파티장</c:if>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
</c:if>
</body>
</html>