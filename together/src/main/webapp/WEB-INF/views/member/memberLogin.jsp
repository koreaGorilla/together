<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>  
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">
	<h2>회원로그인</h2>
	<form:form action="login.do" id="login_form"
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
			<form:button>로그인</form:button>
			<input type="button" value="홈으로"
			 onclick="location.href='/main/main.do'">
		</div>                                       
		
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->	



