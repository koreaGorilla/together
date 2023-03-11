<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
</head>
<body>
<div class="find-id-div">
	<form method="post" class="findId" action="findMyId" name="findMyId">
		<p class="find-id-logo">Together</p>
		<span class="find-id-span">이름과 전화번호를 입력해주세요.</span>
		<input type="text" id="mem_name" name="mem_name" class="form-control" placeholder="이름 입력" autocomplete="off"/>
		<input type="text" id="mem_cell" name="mem_cell" class="form-control" placeholder="전화번호 입력('-' 제외하고 입력하세요)"  autocomplete="off"/>
		<input id="login_btn" type="submit" value="확인">

		<section class="find-signup-wrap">
			<div id="find-signup-wrap-ko">
				<span class="find-id">
					<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
				</span>
				
				<span class="find-pw">
					<a href="${pageContext.request.contextPath}/find/checkCell.do">비밀번호 찾기</a>
				</span>
				
				<span class="sign-up">
					<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
				</span>
			</div>
		</section>
		<%--결과 출력 --%>
		<div class="result"></div>
	</form>
</div>
</body>
<script>
    var check = <%= request.getAttribute("check") %>;
    var id = '<%= request.getAttribute("id") %>';

    if (check == 1) {
        alert("일치하는 정보가 존재하지 않습니다.");
    } else if (check == 0 && id != null) {
        alert("찾으시는 아이디는 '" + id + "' 입니다.");
		 location.href = "${pageContext.request.contextPath}/member/login.do";
    }
</script>
</html>