<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<script type="text/javascript">
let result = '${result}';
	if(!location.hash && result == 'success'){
		alert('글쓰기 완료!!');
		history.replaceState('','','#rs');
	}
	$(function(){
		//검색 유효성 체크
		$('#partySearchform').submit(function(){
			if($('#party_keyword').val().trim()==''){ 
				alert('검색어를 입력하세요!');
				$('#party_keyword').val('').focus();
				return false;
			}
		});
	});
	</script>
<div>
	<c:if test="${!empty param.party_hobby  and (param.party_hobby>=1 and param.party_hobby<=4)}">
		<c:if test="${param.party_hobby == 1}"> 운동</c:if>
		<c:if test="${param.party_hobby == 2}"> 독서</c:if>
		<c:if test="${param.party_hobby == 3}"> 음주</c:if>
		<c:if test="${param.party_hobby == 4}"> 문화</c:if>
	</c:if>
	<c:if test="${empty param.party_hobby  or (param.party_hobby<1 or param.party_hobby>4)}">
	<form action="list.do" id="partySearchform" method="get">
		<ul>
			<li>
				<input type="search" name="keyword" id="party_keyword" value="${param.keyword}" autocomplete="off">
			</li>
			<li>
				<input type="submit" value="검색" id="partySearch_btn">
				<input type="button" value="목록" id="partySearch_list" onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	</c:if>
	<c:if test="${!empty user}">
	<div class="align-right">
		<input id="party_write" type="button" value="파티 생성" 
		    onclick="location.href='write.do'" style="margin-right: 20px;">
	</div>
	</c:if>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 파티 정보가 없습니다.</div>
	</c:if>	
	<c:if test="${count > 0}">
	<div class="partyList">
		<c:forEach var="party" items="${list}">
			<div class="party-list-item">
				<a href="${pageContext.request.contextPath}/party/detail.do?party_num=${party.party_num}">
					<div id="party_list_img"><img src="imageView.do?party_num=${party.party_num}&party_type=2"></div>
					<div class="party-list-pname">${party.party_name}</div>
					<c:if test="${party.party_hobby == 1}"><p># 운동</p></c:if>
					<c:if test="${party.party_hobby == 2}"><p># 독서</p></c:if>
					<c:if test="${party.party_hobby == 3}"><p># 음주</p></c:if>
					<c:if test="${party.party_hobby == 4}"><p># 문화</p></c:if>
				</a>
			</div>
		</c:forEach>
	</div>
	<div class="align-center">${page}</div>
	</c:if>
</div>