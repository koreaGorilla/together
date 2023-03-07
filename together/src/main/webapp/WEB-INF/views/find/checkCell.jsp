<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
<meta http-equiv="X-UA-Compatible" content="ie=edge" /> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/findIdPw.css">
<title>문자 인증</title>
</head>
<body>
	<div class="containerPw">
		<form method="post" class="findPw" action="findMyPw" name="findMyPw">
			<c:if test="${check == null || check == 1}">
			
				<h1>비밀번호 찾기</h1>
					<div class="inputName">
						<label for="mem_name">아이디</label>
						<input type="text" id="mem_name" name="mem_name" class="form-control" />
					</div>
					
					<div class="inputCell">
						<label for="mem_phone">전화번호 입력('-' 제외하고 입력하세요)</label>
						<input type="text" id="mem_cell" name="mem_cell" class="form-control"/>
					</div>
					
					<div class="findpw-btn">
						<input class="findId-submit-btn" type="submit" value="확인">
					</div>
			</c:if>
				
				<!-- 이름과 전화번호가 일치하지 않을 때-->
				<c:if test="${check == 1}">
					<label>일치하는 정보가 존재하지 않습니다.</label>
				</c:if>
		
				<!-- 이름과 비밀번호가 일치할때 -->
				<c:if test="${check == 0 && cell !=null}">
					<h1>비밀번호 찾기</h1>
					<div id="contents"> 
						<label for="invite_phone">전화번호 입력</label>
						<input type="text" id="to" name="to" value="${cell}" disabled/>  
						<button type="button" id="send">전송</button><br> <!-- 문자보내는 전송버튼 -->
						<div id="confrimWrap" class="confrimWrap" name="confrimWrap">
							<label for="confirm_num">인증번호 입력</label>
							 <input type="text" id="userNum" name="userNum">   <!-- 인증번호 입력창 -->
							 
							<button type="button" id="enterBtn">확인</button>  <!-- 인증번호와 내가 입력창에 입력한 인증번호 비교하는 창 -->		
						</div>
				    </div>
				</c:if>
		</form>
	</div>
</body>

<script type="text/javascript">
$('#send').click(function() {
	
	const to = $('#to').val();
	
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

            // 인증번호 입력 폼과 확인 버튼 보이기
 			$('#confrimWrap').show();
			
			$('#enterBtn').click(function() {	
				const userNum = $('#userNum').val();
				
				if(checkNum === userNum) {
					alert('비밀번호는 ${password} 입니다.');
					
					 window.location.href = "${pageContext.request.contextPath}/member/login.do";
				}
				else {
					alert('인증 실패하였습니다. 다시 입력해주세요.');
				}
			});
			
		}
	});
	
});
</script>

</html>