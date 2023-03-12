<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
<meta http-equiv="X-UA-Compatible" content="ie=edge" /> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/confirmId.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<title>회원가입</title>
</head>
<body> 
<div class="register-div">
	<p class="register-logo"><a href="${pageContext.request.contextPath}/main/main.do">Together</a></p>
	<form:form action="registerUser.do" id="register_form" modelAttribute="memberVO">
		<form:errors element="div" cssClass="error-color"/>   
	
		<h3>아이디</h3>
		<input id="mem_id" name="mem_id" type="text" class="form-control-register" placeholder="영문,숫자만 4~12자" autocomplete="off"/>
		<input type="button" class="confirmId" id="confirmId" value="ID중복체크">
	
		<span id="message_id"></span>
		<form:errors path="mem_id" cssClass="error-color"/>    
	                
		<h3>비밀번호</h3>
		<input id="mem_pw" name="mem_pw" class="form-control-register-common" type="password"/>
		<span class="pw-lock"></span>
	    <form:errors path="mem_pw" cssClass="error-color"/>
	
		<h3>이름</h3>
		<input id="mem_name" name="mem_name" class="form-control-register-common" type="text" autocomplete="off"/>
		<form:errors path="mem_name" cssClass="error-color"/>
	
		<h3>이메일</h3>
		<input id="mem_email" name="mem_email" class="form-control-register-common" type="text" autocomplete="off"/>
		<form:errors path="mem_email" cssClass="error-color"/>
	
		<h3>휴대전화</h3>
		<input id="mem_cell" name="mem_cell" class="form-control-register" type="text" placeholder="전화번호 '-' 제외하고 입력" autocomplete="off"/>
	    <input type="button" class="send" id="send" value="인증번호받기">
		<form:errors path="mem_cell" cssClass="error-color"/>
		
		<div id="checkNumber">
			<div id="confrimWrap" class="confrimWrap" name="confrimWrap">
				<input id="userNum" name="userNum" class="form-control-register" type="text" placeholder="인증번호 입력하세요" autocomplete="off"/>
				<input type="button" class="enterBtn" id="enterBtn" value="인증">
			</div>
		</div>
	
		<h3>주소</h3>
		<input id="mem_zipcode" name="mem_zipcode" class="form-control-register" type="text" placeholder="우편번호" readonly/>
		<input type="button" id="zip-code" onclick="execDaumPostcode()" value="우편번호 찾기">
		<form:errors path="mem_zipcode" cssClass="error-color"/>
	
		<input id="mem_address1" name="mem_address1" class="form-control-register-common" type="text" placeholder="주소" readonly/>
		<form:errors path="mem_address1" cssClass="error-color"/>
	                
		<input id="mem_address2" name="mem_address2" class="form-control-register-common" type="text" placeholder="상세주소" autocomplete="off"/>
		<form:errors path="mem_address2" cssClass="error-color"/>
	
		<form:button id="register_btn">가입하기</form:button>                        
		<input type="button" id="home_btn" value="취소"  onclick="location.href='/main/main.do'">
	</form:form>
</div>
<!-- 우편번호 검색 시작 -->
<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_layer = document.getElementById('layer');

    function closeDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_layer.style.display = 'none';
    }

    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    //(주의)address1에 참고항목이 보여지도록 수정
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //(수정) document.getElementById("address2").value = extraAddr;
                
                } 
                //(수정) else {
                //(수정)    document.getElementById("address2").value = '';
                //(수정) }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('mem_zipcode').value = data.zonecode;
                //(수정) + extraAddr를 추가해서 address1에 참고항목이 보여지도록 수정
                document.getElementById("mem_address1").value = addr + extraAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("mem_address2").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_layer.style.display = 'none';
            },
            width : '100%',
            height : '100%',
            maxSuggestItems : 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }

    // 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
    // resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
    // 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
    function initLayerPosition(){
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
</script>
<script type="text/javascript">
$('#send').click(function() {
	const to = $('#mem_cell').val();
	var element = document.getElementById("cnum-btn-wrap3");
	
	$.ajax ({
		url: '/find/checkSms',
		type: 'POST',
		data: {
			"to" : to
		},
		success: function(data) {
			const checkNum = data;
			alert('인증번호 전송 완료!');

			$('#send').hide();
			
            // 인증번호 입력 폼과 확인 버튼 보이기
 			$('#checkNumber').show();
			
			$('#enterBtn').click(function() {	
				const userNum = $('#userNum').val();
				
				if(checkNum === userNum) {
					alert('인증되었습니다.');
					
		            // 가입 버튼 보이기
		 			$('.cnum-btn-wrap').show();
		 			$('#register_btn').show();
		 			element.style.width = "50%";
		 			element.style.marginLeft = "10px";
				}
				else {
					alert('인증 실패하였습니다. 다시 입력해주세요.');
				}
			});
			
		}
	});
	
});
</script>
<!-- 우편번호 검색 끝 -->
<!-- 중앙 컨텐츠 끝 -->
