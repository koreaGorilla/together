<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main">
	<div class="party-image">
		<c:if test="${empty party.party_photo_name }">
		<img src="${pageContext.request.contextPath}/images/togetherLogo.jpg" width="100%" height="200" class="party-photo">
		</c:if>
		<c:if test="${!empty party.party_photo_name }">
		<img src="imageView.do?party_num=${party.party_num}&party_type=2" width="100%" height="200" class="party-photo">
		</c:if>
	</div>
	<form id="partyView">
		<div class="party-info">
			<ul class="party-info">
				<li class="party-top">
					<c:if test="${empty party.photo_name}">
					<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
					</c:if>
					<c:if test="${!empty party.photo_name}">
					<img src="imageView.do?party_num=${party.party_num}&party_type=1" width="40" height="40" class="my-photo">
					</c:if>
					<b style="font-size:20px;">${party.party_name }</b>
					<b>파티장 : </b>
					<span>${party.mem_name }</span>
					<b>파티 개설일 : </b>
					<span>${party.party_reg_date }</span>
					
				</li>
				<li class="party-content">
					<span>${party.party_content}</span>
				</li>
				<li class="party-member">
					<b>가입멤버</b>
					<br>
					<span>함께 소통하며 활동하고 있어요</span>
					${list.mem_name }
				</li>
			</ul>	
		</div>
		<div class="party-register">
			<input type="button" value="가입하기" onclick="location.href='${pageContext.request.contextPath}/partymember/partyMemberCheck.do?party_num=${party.party_num}'">
		</div>
	</form>
	
	<div class="party-info">
		<a href="${pageContext.request.contextPath}/partymember/partyMemberList.do?party_num=${party.party_num}">파티멤버</a>
		<ul class="party-info">
		<li>
		</li>
		</ul>
	</div>
	
</div>