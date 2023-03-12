<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberModify.css"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<title>로그인</title>
</head>
<body> 
<div class="memDelete-div">
	<p class="memDelete-logo"><a href="${pageContext.request.contextPath}/main/main.do">Together</a></p>
	<form:form action="delete.do" id="delete_form" modelAttribute="memberVO">
		<span class="changePasswd-span">회원탈퇴</span>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<form:input path="mem_id" class="form-control-pw" placeholder="아이디" autocomplete="off"/>
				<form:errors path="mem_id" cssClass="error-color"/>
			</li>
			<li>
				<form:password path="mem_pw" class="form-control-delete" placeholder="비밀번호"/>
				<form:errors path="mem_pw" cssClass="error-color"/>
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