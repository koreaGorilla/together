<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main">
	<div class="party-image">
		<c:if test="${empty party.party_photo_name }">
		<img src="${pageContext.request.contextPath}/images/togetherLogo.jpg" width="100%" height="100" class="party-photo">
		</c:if>
		<c:if test="${!empty party.party_photo_name }">
		<img src="imageView.do?party_num=${party.party_num}&party_type=1" width="100%" height="100" class="party-photo">
		</c:if>
	</div>
	<div class="party-info">
		<ul class="party-info">
		<li>
		</li>
	</ul>	
	</div>

</div>