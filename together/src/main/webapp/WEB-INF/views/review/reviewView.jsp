<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-main">
	<h2>${review.party_name}</h2>
	
	<ul class="detail-info">
		<li>
			<c:if test="${!empty review.photo_name}">
			<img src="imageView.do?r_num=${review.r_num}&review_type=1" width="40" height="40" class="my-photo">
			</c:if>
			<c:if test="${empty review.photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
			
		</li>
		<li>
			<span>${review.mem_name}</span><br>
			<c:if test="${empty review.r_modify_date}">
				<span>작성일 : ${review.r_date}</span>
			</c:if>
			<c:if test="${!empty review.r_modify_date}">
				<span>최근 수정일 : ${review.r_modify_date}</span>
			</c:if>
		</li>
		
		<li>
			<div class="align-right">
				<c:if test="${!empty user && user.mem_num==review.mem_num}">
				<input type="button" value="수정" onclick="location.href='update.do?r_num=${review.r_num}'">
				<input type="button" value="삭제" id="delete_btn">
				
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
				
				<input type="button" value="목록" onclick="location.href='list.do'">
			</div>
		</li>
	</ul>
	<div class="align-center">
		<c:if test="${!empty review.r_photoname}">
			<img src="imageView.do?r_num=${review.r_num}&review_type=2" width="500" height="300" class="detail-img">
		</c:if>
	</div>
	<div class="r_content">
		<span>${review.r_content }</span>
	</div>
	
</div>