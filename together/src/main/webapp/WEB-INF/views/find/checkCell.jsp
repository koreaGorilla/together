<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
<meta http-equiv="X-UA-Compatible" content="ie=edge" /> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<title>비밀번호 찾기</title>
</head>
<body>
<div class="find-passwd">
	<form method="post" class="findPw" action="findMyPw" name="findMyPw">
		<c:if test="${check == null || check == 1}">
			<p class="find-passwd-logo">Together</p>
			<span class="find-passwd-span">아이디와 전화번호를 입력해주세요.</span>
			<input type="text" id="mem_name" name="mem_name" class="form-control" placeholder="아이디 입력" autocomplete="off"/>
			<input type="text" id="mem_cell" name="mem_cell" class="form-control" placeholder="전화번호 입력('-' 제외하고 입력하세요)"  autocomplete="off"/>
			<input id="login_btn" type="submit" value="확인">

			<section class="find-signup-wrap">
				<div id="find-signup-wrap-ko">
					<span class="find-id">
						<a href="${pageContext.request.contextPath}/member/login.do" title="findId">로그인</a>
					</span>
						
					<span class="find-id">
						<a href="${pageContext.request.contextPath}/find/findId.do" title="findId">아이디 찾기</a>
					</span>
			
					<span class="sign-up">
						<a href="${pageContext.request.contextPath}/member/registerUser.do" title="register">회원가입</a>
					</span>
				</div>
			</section>
		</c:if>
				
		<!-- 이름과 전화번호가 일치하지 않을 때-->
		<c:if test="${check == 1}">
			<label class="noteq">일치하는 정보가 존재하지 않습니다.</label>
		</c:if>
		
		<!-- 이름과 비밀번호가 일치할때 -->
		<c:if test="${check == 0 && cell !=null}">
			<p class="find-passwd-logo">Together</p>
			<div style="display: flex;">
				<span class="find-pw-phone" style="width:100%; margin: 10px 0px 0px 0px">
					<input type="text" id="to" name="to" value="${cell}" disabled/> 
				</span>
				<span class="cnum-btn-wrap2" id="cnum-btn-wrap2"> 
					<button type="button" id="send">전송</button> <!-- 문자보내는 전송버튼 -->
				</span>
			</div>
			<div id="checkNumber">
				<div style="display: flex;" id="confrimWrap" class="confrimWrap" name="confrimWrap">
					<span class="find-pw-phone" style="width:100%; margin: 10px 0px 0px 0px">
						<input type="text" id="userNum" name="userNum" placeholder="인증번호 입력" style="cursor: text" autocomplete="off">   <!-- 인증번호 입력창 -->
					</span>
					<span class="cnum-btn-wrap">
						<button type="button" id="enterBtn">확인</button>  <!-- 인증번호와 내가 입력창에 입력한 인증번호 비교하는 창 -->		
					</span>
				</div>
			</div>
			<section class="find-signup-wrap">
				<div id="find-signup-wrap-ko">
					<span class="find-id">
						<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
					</span>				
					<span class="find-id">
						<a href="${pageContext.request.contextPath}/find/findId.do">아이디 찾기</a>
					</span>
					<span class="sign-up">
						<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
					</span>
				</div>
			</section>
		</c:if>
	</form>
</div>
</body>

<script type="text/javascript">
$('#send').click(function() {
	const to = $('#to').val();
	
	if (to === "") {
		  alert("인증번호를 입력해주세요.");
	}else if(isNaN(to)) {
		  alert("인증번호는 숫자로만 이루어져야 합니다.");
	}else{
		$.ajax ({
			url: '/find/checkSms',
			type: 'POST',
			data: {
				"to" : to
			},
			success: function(data) {
				const checkNum = data;
				alert('인증번호 전송 완료!');
					
				// 전송 버튼 숨기기
				$('#send').hide();
				$('#cnum-btn-wrap2').hide();
		
				// 인증번호 입력 폼과 확인 버튼 보이기
		 		$('#checkNumber').show();
					
				$('#enterBtn').click(function() {	
					const userNum = $('#userNum').val();
						
					if(checkNum === userNum) {
						alert('비밀번호는 ${password} 입니다.');
						window.location.href = "${pageContext.request.contextPath}/member/login.do";
					} else {
						alert('인증 실패하였습니다. 다시 입력해주세요.');
					}
				});
			}
		});
	}
});
</script>
<script>
    var check = <%= request.getAttribute("check") %>;	

    if (check == 1) {
        alert("일치하는 정보가 존재하지 않습니다.");
    }
</script>
</html>