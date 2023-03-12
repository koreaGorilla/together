<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hapalpal.css">
<script src="${pageContext.request.contextPath}/js/party.fav.js"></script>

<div id="partydetail-pagemain">
 <div>
   <div id="party-image">
      <c:if test="${empty party.party_photo_name }">
      <img src="${pageContext.request.contextPath}/images/togetherLogo.jpg" class="partyy-photo">
      </c:if>
      <c:if test="${!empty party.party_photo_name }">
      <img src="imageView.do?party_num=${party.party_num}&party_type=2"class="partyy-photo">
      </c:if>
   </div>
  <div id=dhfmsWhr>   
   <form id="partyView">
      <div class="party-info">
         <ul class="info1">
            <li class="party-top">
            <ul style="font-size:20px; text-align: center;">
               <b>${party.party_name }</b>
            </ul>
               <ul style="text-align: center; margin-top:10px;">
	               <c:if test="${empty party.photo_name}">
	               <img src="${pageContext.request.contextPath}/images/face.png" width="20" height="20" class="my-photo">
	               </c:if>
	               <c:if test="${!empty party.photo_name}">
	               <img src="imageView.do?party_num=${party.party_num}&party_type=1" width="20" height="20" class="my-photo">
	               </c:if>
	               <b>파티장 : </b>
	               <span>${party.mem_name }</span>
	               <b>파티 개설일 : </b>
	               <span>${party.party_reg_date }</span>
               </ul>
               
            </li>
            <li class="party-content">
               <span class="party_content">${party.party_content}</span>
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
                           <span class="partyMembers" >${member.mem_name}</span>
                        </div>
                     </c:if>
                     <c:if test="${!empty member.photo_name}">
                        <div class="member-info">
                           <img src="../mypage/viewProfile.do?mem_num=${member.mem_num}" width="40" height="40" class="my-photo">
                           <br>
                           <span class="partyMembers">${member.mem_name}</span>
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
               <input type="button" value="가입하기" id="apply_btn">
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
            </div>
         </c:if>
         <c:if test="${count==1}">
            <c:if test="${nowMem.party_auth==0 or nowMem.party_auth==9}">
               <input id="detail-enterparty"type="button" value="파티 입장" onclick="location.href='partyMain.do?party_num=${party.party_num}'">
            </c:if>
            <c:if test="${nowMem.party_auth==1}">
               가입 대기중입니다!
            </c:if>
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
       
       <c:if test="${!empty pMember && pMember.party_auth==9}">
            
               <input type="button" value="수정" id="modify_btnn" onclick="location.href='partyUpdate.do?party_num=${party.party_num}'">
            
            </c:if>
            
   </div>
   </form>
  
  </div>   
 </div>
    
   
</div>