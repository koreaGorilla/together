<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
<meta http-equiv="X-UA-Compatible" content="ie=edge" /> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<title>로그인</title>
</head>
<body> 
<div class="login-div">
	<form:form action="login.do" id="login_form" modelAttribute="memberVO">
		<p class="login-logo"><a href="${pageContext.request.contextPath}/main/main.do">Together</a></p>
		<form:errors element="div" cssClass="error-color"/> 

		<form:input id="mem_id" path="mem_id" placeholder="아이디" type="text" class="form-control" autocomplete="off"/>
		<form:errors path="mem_id" cssClass="error-color"/>
		
		<form:input id="mem_pw"  path="mem_pw" placeholder="비밀번호" type="password" class="form-control"/>
		<form:errors path="mem_pw" cssClass="error-color"/>
		
		<form:button id="login_btn">로그인</form:button>
	</form:form>

	<section class="find-signup-wrap">
		<div id="find-signup-wrap-ko">
			<span class="find-id">
				<a href="${pageContext.request.contextPath}/find/findId.do">아이디 찾기</a>
			</span>

			<span class="find-pw">
				<a href="${pageContext.request.contextPath}/find/checkCell.do">비밀번호 찾기</a>
			</span>

			<span class="sign-up">
				<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
			</span>
		</div>
	</section>
</div>
<script>
    var check = <%= request.getAttribute("errorMessage") %>;

    if (check == 1) {
        alert("아이디 또는 비밀번호가 틀립니다.");
    }
</script>
</html>