<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>

<body>
<div class="page-main">
<form method="post" class="findId" action="findMyId" name="findMyId">
	<h1>아이디 찾기</h1>
		<section class="login-wrap">

			<div class="login-id-wrap">
				<input type="text" id="mem_name" name="mem_name"  class="form-control" placeholder="이름" />
			</div>
			<div class="login-pw-wrap">
				<input type="text" id="mem_cell"  name="mem_cell"  class="form-control" placeholder="전화번호"/>
			</div>
			<div class="login-btn-wrap">
				<input id="submit-btn" type="submit" value="찾기"">
			</div>

		</section>
			<section class="find-signup-wrap">
				<div id="find-signup-wrap-ko">
					<span class="find-pw">
						<a href="${pageContext.request.contextPath}/find/checkCell.do"
							title="findPw">비밀번호 찾기</a>
					</span>
					<span class="sign-up">
						<a href="${pageContext.request.contextPath}/member/registerUser.do" title="register">회원가입</a>
					</span>
				</div>

			</section>
		
		<!-- 이름과 전화번호가 일치하지 않을 때-->
		<div class="result">
			<c:if test="${check == 1}">
				<label>일치하는 정보가 존재하지 않습니다.</label>
			</c:if>
	
			<!-- 이름과 비밀번호가 일치하지 않을 때 -->
			<c:if test="${check == 0 && id!=null}">
			<label>찾으시는 아이디는' ${id}' 입니다.</label>
			</c:if>
		</div>
	</form>
	</div>
	
</body>
</html>