<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberModify.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<title>로그인</title>
</head>
<body> 
<div class="changePasswd-div">
	<p class="changePasswd-logo"><a href="${pageContext.request.contextPath}/main/main.do">Together</a></p>
	<form:form action="changePassword.do" id="change_form" modelAttribute="memberVO">
		<span class="changePasswd-span">비밀번호 변경</span>	
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<form:password path="now_passwd" class="form-control-pw" placeholder="현재 비밀번호"/>
				<form:errors path="now_passwd" cssClass="error-color"/>
			</li>
			<li>
				<form:password path="mem_pw" class="form-control-pwo" placeholder="새 비밀번호"/>
				<form:errors path="mem_pw" cssClass="error-color"/>
			</li>
			<li>
				<input type="password" id="confirm_passwd" class="form-control-pwc" placeholder="새 비밀번호 확인"/><br>
				<span id="message_id"></span>
			</li>
			<li>
				<form:button id="send" class="change-btn">확인</form:button>
			</li>
			<li>
				<input type="button" value="취소" id="myPagee" class="myPage-btn" onclick="location.href='myPage.do'">
			</li>
		</ul>                           
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->



