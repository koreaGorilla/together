<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방 세부</title>
<script>
	var user="${user}"
	//ENTER키로 메시지 전송 가능하도록 활성화
	$("#msg").on("keypress", function(e) {
		if(e.keyCode == 13 && !e.shiftKey){
			e.preventDefault();
			var message = $("#msg").val();
			if(message == ""){
				alert("메시지를 입력하세요");
				$("#msg").focus();
				return;
			}
		}
		
	})
</script>
</head>
<body>
	<div class="chat_main">
		<div class="header">
			<h2>${chatRoom.party_name}</h2>
		</div>
		<div id="chat"></div>
		<div class="senderPosition">
			<div class="sender"></div>
			<div class="message"></div>
			<div class="timestamp"></div>
		</div>
		<div class="input_msg">
			<textarea id="msg" cols="30" rows="10" 
				placeholder="메시지를 입력해주세요"></textarea>
		</div>
	</div>

</body>

</html>