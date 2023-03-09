<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partyDetail.css">
<script src="${pageContext.request.contextPath}/js/party.fav.js"></script>

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
			<ul class="info">
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
					<div class="member">
						<c:forEach var="member" items="${list}">
						<c:if test="${member.party_auth == 0}">
							<c:if test="${empty member.photo_name}">
								<div class="member-info">
									<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
									<br>
									<span>${member.mem_name}</span>
								</div>
							</c:if>
							<c:if test="${!empty member.photo_name}">
								<div class="member-info">
									<img src="imageView.do?party_num=${party.party_num}&party_type=1" width="40" height="40" class="my-photo">
									<br>
									<span>${member.mem_name}</span>
								</div>
							</c:if>	
						</c:if>
						</c:forEach>
					</div>	
				</li>
			</ul>	
		</div>
		<%--좋아요--%>
		<div>
			<img id="output_fav" data-num="${party.party_num}" src="${pageContext.request.contextPath}/images/fav_1.png" width="40">
		</div>
			<c:if test="${count==0}">
				
				<div class="party-register">
				<!-- 파티가입시 party_auth값 저장 하단 ajax로 이동-->
				<c:if test="${party.party_reg_type == 0}">
					<input type="hidden" id="party_auth" name="party_auth" value="0">
				</c:if>
				<c:if test="${party.party_reg_type == 1}">
					<input type="hidden" id="party_auth" name="party_auth" value="1">
				</c:if>
					
				</div>
			</c:if>
			
	
		<div class="align-right">
		
		<c:if test="${!empty pMember && pMember.party_auth==9}">
		<input type="button" value="삭제" id="delete_btn"> 
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?party_num=${party.party_num}');
					
				}
			};
			
		</script> 
		 </c:if>		
	</div>
	</form>
	
	<div class="party-info">
	<c:if test="${nowMem.party_auth==9}">
		<a href="${pageContext.request.contextPath}/partymember/partyMemberList.do?party_num=${party.party_num}">파티멤버</a>
	</c:if>
		<ul class="party-info">
		<li>
		</li>
		</ul>
	</div>
</div>