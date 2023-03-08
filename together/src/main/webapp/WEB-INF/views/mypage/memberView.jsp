<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">
	<h2>회원상세 정보<input type="button" value="회원정보 수정"
			onclick="location.href='update.do'"></h2>
	<ul>
		<li>이름 : ${member.mem_name}</li>
		<li>취미 : ${member.hobby}</li>
		<li>전화번호 : ${member.mem_cell }</li>
		<li>이메일 : ${member.mem_email }</li>
		<li>우편번호 : ${member.mem_zipcode}</li>
		<li>주소 : ${member.mem_address1} ${member.mem_address2}</li>
		<li>가입날짜 : ${member.reg_date}</li>
		<c:if test="${!empty member.modify_date}">
		<li>정보수정일 : ${member.modify_date}</li>
		</c:if>
	</ul>

<!-- 중앙 컨텐츠 끝 -->
<!-- 내가 쓴 리뷰 목록 -->
	<h2>내가 쓴 리뷰 목록</h2>
	<table class="striped-table">
		<tr>
		<th>리뷰번호</th>
		<th>파티명</th>
		<th>내용</th>
		<th>등록일</th>
		</tr>
			<c:forEach var="review" items="${reviewList}">
		<tr>
			<td>${review.r_num}</td>
			<td>${review.party_name}</td>	
			<td>
				<a href="${pageContext.request.contextPath}/review/detail.do?r_num=${review.r_num}">${review.r_content}</a>
			</td>
			<td>${review.r_date}</td>
		</tr>
		</c:forEach>
	</table>

<!-- 내가 쓴 리뷰 목록 끝 -->
<!-- 좋아요 누른 리뷰 목록 시작 -->
<h2>좋아요 누른 리뷰 목록</h2>
<table class="striped-table">
<tr>
<th>리뷰번호</th>
<th>내용</th>
<th>날짜</th>
</tr>
	<c:forEach var="review" items="${getReview}">
		<tr>
		<td>${review.r_num}</td>
		<td>
			<a href="${pageContext.request.contextPath}/review/detail.do?r_num=${review.r_num}">${review.r_content}</a>
		</td>
		<td>${review.r_date}</td>
		</tr>
	</c:forEach>
</table>
<!-- 좋아요 누른 리뷰 목록 끝 -->
<!-- 내가 가입한 파티 목록 -->
	<h2>내가 가입한 파티 목록</h2>
	<table class="striped-table">
	<tr>
	<th>파티 번호</th>
	<th>파티명</th>
	<th>가입 날짜</th>
	</tr>
		<c:forEach var="party" items="${partyList}">
			<tr>
			<td>${party.party_num}</td>
			<td>
				<a href="${pageContext.request.contextPath}/party/detail.do?party_num=${party.party_num}">${party.party_name }</a>
			</td>
			<td>${party.party_reg_date}</td>
			</tr>
		</c:forEach>
	</table>
<!-- 내가 가입한 파티 목록 끝 -->
<!-- 내가 찜한 파티 목록 -->
	<h2>찜한 파티 목록</h2>
	<table class="striped-table">
	<tr>
	<th>파티번호</th>
	<th>파티명</th>
	<th>날짜</th>
	</tr>
		<c:forEach var="party" items="${getList}">
			<tr>
			<td>${party.party_num}</td>
			<td>
				<a href="${pageContext.request.contextPath}/party/detail.do?party_num=${party.party_num}">${party.party_name}</a>
			</td>
			<td>${party.party_reg_date}</td>
			</tr>
		</c:forEach>
	</table>

<!-- 내가 찜한 파티 목록 끝 -->

</div>