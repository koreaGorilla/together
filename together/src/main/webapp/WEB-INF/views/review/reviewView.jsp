<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/review.css">
<script src="${pageContext.request.contextPath}/js/review.fav.js"></script>
<script src="${pageContext.request.contextPath}/js/review.reply.js"></script>


<div class="page-main">
	<ul class="review-button">
		<li>
			<div class="rev-button">
				<c:if test="${!empty user && user.mem_num==review.mem_num}">
					<img src="${pageContext.request.contextPath}/images/modifyicon.png" 
					onclick="location.href='update.do?r_num=${review.r_num}'" style="cursor:pointer;">
					<img src="${pageContext.request.contextPath}/images/deleteicon.png" 
					id="delete_btn" style="cursor:pointer;">

					<script type="text/javascript">
						let delete_btn = document.getElementById('delete_btn');
						delete_btn.onclick=function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								location.replace('delete.do?r_num=${review.r_num}');
							}
						}
					</script>
				</c:if>
				<img src="${pageContext.request.contextPath}/images/listicon.png" 
					onclick="location.href='list.do'" style="cursor:pointer;">
				

			</div>
		</li>
	</ul>
	<h2>${review.party_name}</h2>

	<ul class="detail-infooo" id="detail-infooo">
		<li><c:if test="${!empty review.photo_name}">
				<img src="imageView.do?r_num=${review.r_num}&review_type=1"
					class="my-photoo">
			</c:if> <c:if test="${empty review.photo_name}">
				<img src="${pageContext.request.contextPath}/images/face.png"
					class="my-photoo">
			</c:if></li>
		<li><span class="review_mem_name">${review.mem_name}</span><br>
			<c:if test="${empty review.r_modify_date}">
				<span class="review-date">작성일 : ${review.r_date}</span>
			</c:if> <c:if test="${!empty review.r_modify_date}">
				<span class="review-date">최근 수정일 : ${review.r_modify_date}</span>
			</c:if></li>


	</ul>
	<br>
	<hr class="detail_hr" size="1" width="100%">
	<div class="align-center">
		<c:if test="${!empty review.r_photoname}">
			<img src="imageView.do?r_num=${review.r_num}&review_type=2"
				class="detail-img">
		</c:if>
	</div>
	<div class="r_content">
		<span>${review.r_content }</span>
	</div>
	<%--좋아요 + 댓글 아이콘--%>
	<div id="review_icon">
		<img id="output_fav" data-num="${review.r_num}"
			src="${pageContext.request.contextPath}/images/fav_1.png" width="40">
		<span id="output_fcount"></span> <img id="output_reply"
			src="${pageContext.request.contextPath}/images/reply.png" width="40">
		<span id="output_rcount"></span>
	</div>

	<%--댓글 시작--%>
	<div id="reply">
		<form id="re_form">
			<input type="hidden" name="r_num" value="${review.r_num}" id="r_num">
			<!-- 로그인 안 되어있으면 댓글 입력 못함 -->
			<textarea rows="1" cols="50" name="c_content" id="c_content"
				class="reply_content"
				<c:if test="${empty user}">disabled="disabled"</c:if>><c:if
					test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></textarea>

			<c:if test="${!empty user}">
				<div id="reply_first">
					<span class="letter-count">100/100</span>
				</div>
				<div id="reply_second">
					<input class="btn_css" type="submit" value="등록" id="reggi">

				</div>
			</c:if>
		</form>
	</div>
	<!-- 댓글 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display: none;">
		<input type="button" value="더보기">
	</div>
	<!-- 로딩중 이미지 넣기 -->
	<div id="loading" style="display: none;">
		<img src="${pageContext.request.contextPath}/images/loading.gif"
			width="50" height="50">
	</div>
	<%--댓글 끝--%>





</div>