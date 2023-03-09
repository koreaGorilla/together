<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberModify.css"> 
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">
<h2>회원탈퇴</h2>
<form:form action="delete.do" id="delete_form"
					modelAttribute="memberVO">
	<form:errors element="div"
						cssClass="error-color"/>
	<ul>
		<li>
		<label for="mem_id">아이디</label>
		<form:input path="mem_id"/>
		<form:errors path="mem_id"
						cssClass="error-color"/>
		</li>
		<li>
			<label for="mem_pw">비밀번호</label>
			<form:password path="mem_pw"/>
			<form:errors path="mem_pw"
						cssClass="error-color"/>
		</li>
	</ul>	
	<div class="align-center">
	<form:button id="send">전송</form:button>
	<input type="button" value="MY페이지" id="myPagee"
			onclick="location.href='myPage.do'">
	</div>				
</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->