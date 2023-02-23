<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function(){
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
	<h2>리뷰</h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>작성자</option>
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>작성자+내용</option>
					
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="검색">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	
	<c:if test="${!empty user }">
	<div class="align-right">
		<input type="button" value="리뷰작성" onclick="location.href='write.do'">
	</div>
	</c:if>
	
	<c:if test="${count==0}">
	<div class="result-display">표시할 리뷰가 없습니다.</div>
	</c:if>
	<c:if test="${count>0}">
	<c:forEach var="review" items="${list}">
	<table class="review-table" onclick="location.href='detail.do?r_num=${review.r_num}'" style="cursor:pointer;">
		<tr>
			<td width="50px" height="50px" rowspan="2" >
			<c:if test="${!empty review.photo_name}">
			<img src="imageView.do?r_num=${review.r_num }&review_type=1" width="40" height="40" class="my-photo">
			</c:if>
			<c:if test="${empty review.photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
			</td>
			<td width="500px" >${review.mem_name}</td>
			
			<c:if test="${!empty review.r_photoname}">
			<td width="100px" rowspan="4" class="review-photo">
			<img src="imageView.do?r_num=${review.r_num }&review_type=2" width="170px" height="150px">
			</td>
			</c:if>
			
		</tr>
		<tr>
			<td >${review.r_date}</td>
		</tr>
		<tr>
			<th colspan="2">${review.party_name }</th>
		</tr>
		<tr>
			<td colspan="2">${review.r_content }</td>	
		</tr>
		
	</table>
	<hr size="1" width="100%">
	</c:forEach>
	</c:if>
	
	<div class="align-center">
		${page}
	</div>
	
</div>