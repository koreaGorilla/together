<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- MyPage 메뉴 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage.btn.js"></script>
<div class="side-bar">
	<ul>
		<li>
			<img src="${pageContext.request.contextPath}/mypage/photoView.do" class="my-photoo">
			<div class="camera" id="photo_btn"><img src="${pageContext.request.contextPath}/image_bundle/camera.png" width="35" style="margin-top:8px;margin-left:7px;"></div>
		</li>
		<li>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="upload" accept="image/gif,image/png,image/jpeg">
				<input type="button" value="전송" id="photo_submit">
				<input type="button" value="취소" id="photo_reset">         
			</div>
		</li>
		<li>
			<input type="button" class="myPage-button" value="내 정보" id="myinfo">
		</li>
		<li>
			<input type="button" class="myPage-button" value="나의 파티" id="in_party">
		</li>
		<li>
			<input type="button" class="myPage-button" value="나의 리뷰" id="review">
		</li>
		<li>
			<input type="button" class="myPage-button" value="가입한 파티" id="my_party">
		</li>
		<li>
			<input type="button" class="myPage-button" value="채팅방 목록" id="chat">
			<script type="text/javascript">
				$("#chat").on('click', function(e){
					event.preventDefault();
					window.open("${pageContext.request.contextPath}/chat/chatList.do?mem_num=${party.party_num}", "chat", "width=600, height=700, top=200, left=200, resizable=no");
				});
			</script>
		</li>
		<li>
			<input type="button" class="myPage-button" value="찜한 파티" id="fav_party">
		</li>
		<li style="margin-bottom: 20px;">
			<input type="button" class="myPage-button" value="좋아요 누른 리뷰" id="fav_review">
		</li>
		<li>
			<hr style="color:#ccc">
		</li>
		<li style="margin-top: 20px;">
			<input type="button" class="member-btn" value="비밀번호변경" id="change" onclick="location.href='${pageContext.request.contextPath}/mypage/changePassword.do'">
		</li>
		<li style="margin-bottom: 20px;">
			<input type="button" class="member-btn" value="회원탈퇴" id="out" onclick="location.href='${pageContext.request.contextPath}/mypage/delete.do'">
		</li>
	</ul>
</div>
<!-- MyPage 메뉴 끝 -->
<script type="text/javascript">
	var button = document.getElementById("mem_modify");
	var div = document.getElementById("mem-detail");
	
	button.onclick = function() {
	    div.style.display = "none";
	    window.location.href = "update.do";
	};
</script>


