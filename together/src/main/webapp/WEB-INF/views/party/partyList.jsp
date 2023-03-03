<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/party.css">
<script type="text/javascript">
let result = '${result}';
	if(!location.hash && result == 'success'){
		alert('글쓰기 완료!!');
		history.replaceState('','','#rs');
	}
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
	</script>
<div class="page-main">
	<h2>파티목록  
	<c:if test="${!empty param.party_hobby  and (param.party_hobby>=1 and param.party_hobby<=4)}">
		>
		<c:if test="${param.party_hobby == 1}">운동</c:if>
		<c:if test="${param.party_hobby == 2}">독서</c:if>
		<c:if test="${param.party_hobby == 3}">음주</c:if>
		<c:if test="${param.party_hobby == 4}">문화</c:if>
	</c:if>
	</h2>
	<c:if test="${empty param.party_hobby  or (param.party_hobby<1 or param.party_hobby>4)}">
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					
				</select>
			</li>
			<li>
				<input type="search" name="keyword" 
				  id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록"
				         onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	</c:if>
	<c:if test="${!empty user}">
	<div class="align-right">
		<input type="button" value="파티 생성" 
		    onclick="location.href='write.do'">
	</div>
	</c:if>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 파티 정보가 없습니다.</div>
	</c:if>	
	<c:if test="${count > 0}">
	<div class="item-space">
		<c:forEach var="party" items="${list}">
		<div class="horizontal-area">
			<a href="detail.do?party_num=${party.party_num}">
				<c:if test="${!empty party.party_photo_name}">
				<img src="imageView.do?party_num=${party.party_num}&party_type=2">
				</c:if>
				<c:if test="${empty party.party_photo_name}">
				<img src="${pageContext.request.contextPath}/images/face.png">
				</c:if>
				<span>${party.party_name}</span>
				<br>
				<b>
				<c:if test="${party.party_hobby == 1}">운동</c:if>
				<c:if test="${party.party_hobby == 2}">독서</c:if>
				<c:if test="${party.party_hobby == 3}">음주</c:if>
				<c:if test="${party.party_hobby == 4}">문화</c:if>
				</b>
			</a>
		</div>
		</c:forEach>
		<div class="float-clear">
			<hr width="100%" size="1" noshade="noshade">
		</div>
	</div>
	<div class="align-center">${page}</div>
	</c:if>
</div>