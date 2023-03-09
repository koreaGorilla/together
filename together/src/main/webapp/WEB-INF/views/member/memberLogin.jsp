<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">  
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main">

	<form:form action="login.do" id="login_form"
	                       modelAttribute="memberVO">
		<form:errors element="div" 
		                    cssClass="error-color"/> 

		<h1>로그인</h1>
		<!--웹페이지 상단-->

		<!--로그인 부분-->
		<section class="login-wrap">

			<div class="login-id-wrap">
				<form:input id="mem_id" path="mem_id" placeholder="아이디" type="text"/>
			</div>
				<form:errors path="mem_id" 
				              cssClass="error-color"/>
			<div class="login-pw-wrap">
				<form:input id="mem_pw"  path="mem_pw" placeholder="비밀번호" type="password"/>
			</div>
				<form:errors path="mem_pw" 
				              cssClass="error-color"/>
			<div class="login-btn-wrap">
				<form:button id="login-btn">로그인</form:button>
			</div>

		</section>



		<!--class,PW 찾기 및 회원가입 부분-->
		<section class="find-signup-wrap">

			<div id="find-signup-wrap-ko">
				<span class="find-id">
					<a href="${pageContext.request.contextPath}/find/findId.do" title="findId">아이디 찾기</a>
				</span>

				<span class="find-pw">
					<a href="${pageContext.request.contextPath}/find/checkCell.do"
						title="findPw">비밀번호 찾기</a>
				</span>

				<span class="sign-up">
					<a href="${pageContext.request.contextPath}/member/registerUser.do" title="register">회원가입</a>
				</span>
			</div>

		</section>
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->	

<script>
    var check = <%= request.getAttribute("errorMessage") %>;

    if (check == 1) {
        alert("아이디 또는 비밀번호가 틀립니다.");
    }
</script>



