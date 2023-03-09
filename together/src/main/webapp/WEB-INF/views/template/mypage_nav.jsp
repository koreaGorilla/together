<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- MyPage 메뉴 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage.js"></script>
<div class="side-bar">
	<ul>
		<li>
			<img src="${pageContext.request.contextPath}/mypage/photoView.do" class="my-photoo">
		</li>
		<li>
			<div class="align-center">
				<input type="button" value="수정" id="photo_btn">
			</div>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="upload" 
				         accept="image/gif,image/png,image/jpeg">
				<input type="button" value="전송" id="photo_submit">
				<input type="button" value="취소" id="photo_reset">         
			</div>
		</li>
	<div class="mem-detail" id="mem-detail">
	   <h2>회원상세 정보
	   <input type="button" value="회원정보 수정" id="mem_modify"
	         onclick="location.href='update.do'"></h2>
	   <ul id="modify_table">
	      <li>이름 : ${member.mem_name}</li>
	      <li>취미 : ${member.hobby}</li>
	      <li>전화번호 : ${member.mem_cell }</li>
	      <li>이메일 : ${member.mem_email }</li>
	      <li>우편번호 : ${member.mem_zipcode}</li>
	      <li>주소 : ${member.mem_address1} ${member.mem_address2}</li>
	      <li>가입날짜 : ${member.reg_date}</li>
	      <c:if test="${!empty member.modify_date}">
	      <li>정보수정일 : ${member.modify_date}</li>
	      </c:if>
	   </ul>
	</div>
	<li>
	<input type="button" class="menu-btn" value="비밀번호변경" id="change"
			  onclick="location.href='${pageContext.request.contextPath}/mypage/changePassword.do'">

			<input type="button" class="menu-btn"  value="회원탈퇴" id="out"
			  onclick="location.href='${pageContext.request.contextPath}/mypage/delete.do'">
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


