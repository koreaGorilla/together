<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 채팅하기 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/message.js"></script>
<div id="chatDetail" class="page-main">
<div class="chat-header">
	<h3>${partyVO.party_name} 채팅방		
		<a href="${pageContext.request.contextPath}/chat/chatList.do">
			<img src="${pageContext.request.contextPath}/images/menu.png" >
		</a> </h3>
</div>
	<div id="chat-messages"></div>
	<form method="post" id="detail_form">
		<input type="hidden" name="party_num"
		         id="party_num" 
		         value="${partyVO.party_num}">
		<input type="hidden" name="mem_num"
		        id="mem_num" value="${user.mem_num}">
		<ul>
			<li>
				<label for="message"></label>
			<div class="message-input">
				<textarea rows="5" cols="40"
				  name="message" id="message"></textarea>
				<input type="submit" value="전송">  
			</div>
			</li>
		</ul>                 
	</form>
</div>
<!-- 채팅하기 끝 -->


