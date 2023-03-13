<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<script src="${pageContext.request.contextPath}/js/party.fav.js"></script>
<div class="partyMain">
	<div class="partyMain-img">
		<c:if test="${empty party.party_photo_name }">
			<img src="${pageContext.request.contextPath}/images/togetherLogo.jpg" width="100%">
		</c:if>
		<c:if test="${!empty party.party_photo_name }">
			<img src="imageView.do?party_num=${party.party_num}&party_type=2" width="100%">
		</c:if>
	</div>
	<div class="partyMain-info">
		<ul>
			<li>
				<h2 class="partyMain-name">${party.party_name}</h2>
			</li>
			<li>
				<div class="partyDetail-chief">
					<span>
						<c:if test="${empty party.photo_name}">
							<img src="${pageContext.request.contextPath}/images/face.png" width="20" height="20"> ${party.mem_name} | ${party.party_reg_date}
						</c:if>
						<c:if test="${!empty party.photo_name}">
							<img src="imageView.do?party_num=${party.party_num}&party_type=1" width="20" height="20"> ${party.mem_name} | ${party.party_reg_date}
						</c:if>
					</span>
				</div>
			</li>
			<li class="partyMain-content" style="min-height: 150px;">
				<span>${party.party_content}</span>
			</li>
			<li style="margin-top: 20px;">
				<div class="partyDetail-member">
					<c:forEach var="member" items="${list}">
						<c:if test="${member.party_auth == 0}">
							<c:if test="${empty member.photo_name}">
								<div class="member-img">
									<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo" title="${member.mem_name}">
	                        	</div>
							</c:if>
								
							<c:if test="${!empty member.photo_name}">
								<div class="member-img">
									<img src="../mypage/viewProfile.do?mem_num=${member.mem_num}" width="40" height="40" class="my-photo" title="${member.mem_name}">
								</div>
							</c:if>   
						</c:if>
					</c:forEach>
				</div>
				<div class="partyDetail-fav">
					<img id="output_fav" data-num="${party.party_num}" src="${pageContext.request.contextPath}/image_bundle/fav_1.png" width="40" style="margin-left: 40px;">
				</div>
			</li>
		</ul>
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
	<div class="align-center">
		<c:if test="${!empty pMember && pMember.party_auth==9}">
			<input type="button" value="수정" id="partyMain_modify">
			<input type="button" value="삭제" id="partyMain_delete"> 
			<script type="text/javascript">
				let delete_btn = document.getElementById('partyMain_delete');
				delete_btn.onclick=function(){
					let choice = confirm('삭제하시겠습니까?');
					if(choice){
						location.replace('delete.do?party_num=${party.party_num}');
					}
				};
			</script> 
		</c:if>		
	</div>
	<c:if test="${nowMem.party_auth==0 or nowMem.party_auth==9}">
		<hr style="margin-top: 30px;">
		<div id="invite">
			<input type="hidden" id="partyname" name="partyname" value="${party.party_name}"> 
			초대할 번호 <input type="text" id="to" name="to" class="invite-input"/>  
        	<input type="button" id="partysend" value="전송">
       </div>
   </c:if>
<script type="text/javascript">
$('#send').click(function() {
   const to = $('#to').val();
   const partyName = $('#partyname').val();
   const partyNum = '${party.party_num}';
   const currentUrl = "http://localhost:8001/party/detail.do?party_num=" + partyNum;
   
   $.ajax ({
      url: currentUrl,
      type: 'POST',
      data: {
         "to" : to,
         "partyName" : partyName,
         "url" : currentUrl
      },
      success: function(data) {
         const message = data.message;
         const url = data.url;
         alert('메시지를 보냈습니다'); 
      }
   });
});
</script>
</div>