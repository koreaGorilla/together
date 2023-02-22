<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 채팅하기 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/message.js"></script>
<div id="chatDetail" class="page-main">
	<h2>${partyVO.party_name} 채팅방</h2>
	<div class="align-right">
	<div class="listButton">
		<input type="button" value="목록"
		       onclick="location.href='chatList.do'">     
	</div>
	</div>
	<div id="chatting_message"></div>
	<form method="post" id="detail_form">
		<input type="hidden" name="party_num"
		         id="party_num" 
		         value="${partyVO.party_num}">
		<input type="hidden" name="mem_num"
		        id="mem_num" value="${user.mem_num}">
		<ul>
			<li>
				<label for="message"></label>
				<textarea rows="5" cols="40"
				  name="message" id="message"></textarea>
				<input type="submit" value="전송">  
			</li>
		</ul>                 
	</form>
</div>
<!-- 채팅하기 끝 -->


