<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
<meta http-equiv="X-UA-Compatible" content="ie=edge" /> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>문자인증</title>
</head>
<body>
	<div id="contents"> 
		전송할 번호 : <input type="text" id="to" name="to"/>   
		초대문자 : <input type="text" id="message">
		
		<button type="button" id="send">전송</button>
    </div>
    
</body>

<script type="text/javascript">
$('#send').click(function() {
	
	const to = $('#to').val();
	const message = $('#message').val();
	const currentUrl = window.location.href;
	
	$.ajax ({
		url: '/sms',
		type: 'POST',
		data: {
			"to" : to,
			"message" : message,
			"url" : currentUrl
		},
		success: function(data) {
			const message = data.message;
			const url = data.url;
			alert('메시지를 보냈습니다');
		
			
		}
	});
	
});
</script>

</html>
