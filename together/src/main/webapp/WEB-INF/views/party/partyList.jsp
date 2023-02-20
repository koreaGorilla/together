<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
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
	<h2>파티 목록</h2>
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
	
	<div class="align-right">
		<input type="button" value="글쓰기" 
		    onclick="location.href='write.do'">
	</div>
	
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>
	</c:if>	
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			
			<th>제목</th>
			<th>카테고리</th>
		
		</tr>
		<c:forEach var="party" items="${list}">
		<tr>
			
			<td>
				<a href="detail.do?party_num=${party.party_num}">${party.party_name}</a>
			</td>
			<td>${party.party_hobby}</td>
	
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>