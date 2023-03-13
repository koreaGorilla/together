<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<script src="${pageContext.request.contextPath}/js/party.fav.js"></script>
<div class="partyDetail-main">
	<div class="partyDetail-img">
		<c:if test="${empty party.party_photo_name}">
			<img src="${pageContext.request.contextPath}/images/togetherLogo.jpg" class="partyDetail-photo">
		</c:if>
		<c:if test="${!empty party.party_photo_name}">
			<img src="imageView.do?party_num=${party.party_num}&party_type=2"class="partyDetail-photo">
		</c:if>
	</div>
	<div class="partyDetail-content">   
		<div class="partyDetail-info">
			<h2>${party.party_name}</h2>
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
			<div class="partyDetail-con">
				<span>${party.party_content}</span>
			</div>
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
				<img id="output_fav" data-num="${party.party_num}" src="${pageContext.request.contextPath}/image_bundle/fav_01.png" width="40">
			</div>
		</div>
		<div class="partyDetail-btn">
			<c:if test="${count==0}">
            	<!-- 파티가입시 party_auth값 저장 하단 ajax로 이동-->
				<c:if test="${party.party_reg_type == 0}">
					<input type="hidden" id="party_auth" name="party_auth" value="0">
				</c:if>
				<c:if test="${party.party_reg_type == 1}">
					<input type="hidden" id="party_auth" name="party_auth" value="1">
				</c:if>
				<input type="button" value="가입" id="apply_btn">
				<script type="text/javascript">
					$(function(){
						$('#apply_btn').click(function(){
							let choice = confirm('가입하시겠습니까?');
								
							//파티가입 권한 전달받고 하단의 data를 통해 controller로 전송
							let party_auth = $('#party_auth').val();
							if(choice){
								 $.ajax({
									url:'/partymember/apply',
	                             	type:'post',
	                              	data:{
	                                 	party_num:${party.party_num},
	                                 	"party_auth" : party_auth
	                                 },
									dataType:'json',
	                              	success:function(param){
	                                 	if(param.result == 'logout'){
	                                    	alert('로그인 후 사용하세요');
	                                    	location.href='../member/login.do';
	                                 	}else if(param.result == 'success'){
	                                    	alert('신청 완료되었습니다.');
	                                    	location.href='../party/detail.do?party_num=${party.party_num}';
	                                 	}else{
	                                    	alert('기타 네트워크 오류 발생');
	                                 	}
	                              	},
	                              	error:function(){
	                                 	alert('신청에 네트워크 오류 발생');
	                              	}
	                           	});
	                        }
	                     });
					});
				</script>
			</c:if>
			<c:if test="${count==1}">
				<c:if test="${nowMem.party_auth==0 or nowMem.party_auth==9}">
					<input id="partyEnter"type="button" value="입장" onclick="location.href='partyMain.do?party_num=${party.party_num}'">
				</c:if>
				<c:if test="${nowMem.party_auth==1}">
					<input id="partyWait" type="button" value="가입 대기중">
				</c:if>
         	</c:if>
         	<c:if test="${!empty pMember && pMember.party_auth==9}">
				<input type="button" value="수정" id="party_modify" onclick="location.href='partyUpdate.do?party_num=${party.party_num}'">
			</c:if>
			<c:if test="${!empty pMember && pMember.party_auth==9}">
				<input type="button" value="삭제" id="party_delete"> 
				<script type="text/javascript">
					let delete_btn = document.getElementById('party_delete');
					delete_btn.onclick=function(){
						let choice = confirm('삭제하시겠습니까?');
						if(choice){
							location.replace('delete.do?party_num=${party.party_num}');
						}
					};
				</script> 
			</c:if>
		</div>
	</div>   
</div>