<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberModify.css"> 
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">
	<h2>비밀번호변경</h2>
	<form:form action="changePassword.do" 
	       id="change_form" modelAttribute="memberVO">
		<form:errors element="div" 
		                      cssClass="error-color"/>
		<ul>
			<li>
				<label for="now_passwd">현재 비밀번호</label>
				<form:password path="now_passwd"/>
				<form:errors path="now_passwd"
				              cssClass="error-color"/>
			</li>
			<li>
				<label for="mem_pw">새비밀번호</label>
				<form:password path="mem_pw"/>
				<form:errors path="mem_pw"
				              cssClass="error-color"/>
			</li>
			<li>
				<label for="confirm_passwd">비밀번호 확인</label>
				<input type="password" id="confirm_passwd"/>
				<span id="message_id"></span>
			</li>
		</ul> 
		<div class="align-center">
			<form:button id="send">전송</form:button>
			<input type="button" value="My페이지" id="myPagee"
			     onclick="location.href='myPage.do'">
		</div>                            
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->



