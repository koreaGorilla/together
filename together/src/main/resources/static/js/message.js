$(function(){
	let message_socket;//웹소켓 식별자
	
	//웹소켓 연결
	function alarm_connect(){
		message_socket = new WebSocket("ws://localhost:8001/message-ws.do");
		message_socket.onopen=function(evt){
			//채팅 페이지에 진입하면 채팅 메시지 발송
			if($('#chatDetail').length == 1){
				message_socket.send("msg:");
			}
			console.log("채팅페이지 접속");
		};
		//서버로부터 메시지를 받으면 호출되는 함수 지정
		message_socket.onmessage=function(evt){
			let data = evt.data;
			if($('#chatDetail').length == 1 && 
			      data.substring(0,4) == 'msg:'){
				selectMsg();
			}
		};
		message_socket.onclose=function(evt){
			//소켓이 종료된 후 부과적인 작업이 있을 경우 명시
			console.log('채팅 종료');
		}
	}
	alarm_connect();
	
	//======채팅 데이터 읽기========//
	function selectMsg(){
		$.ajax({
			url:'../chat/chatDetailAjax.do',
			type:'post',
			data:{party_num:$('#party_num').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
					message_socket.close();
				}else if(param.result == 'success'){
					$('#chatting_message').empty();
					
					//채팅 날짜 표시
					let timestamp='';
					$(param.list).each(function(index,item){
						let output = '';
						//날짜 추출
						if(timestamp != item.timestamp.split(' ')[0]){
							timestamp = item.timestamp.split(' ')[0];
							output += '<div class="date-position"><span>'+timestamp+'</span></div>';
						}
						
						if(item.message.indexOf('@{exit}@')>=0){
							//탈퇴 메시지 처리 불필요 refactor 가능
							output += '<div class="exit-message">';
							output += item.message.substring(0,item.message.indexOf('@{exit}@'));
							output += '</div>';
						}else{
							//일반 메시지 처리
							if(item.mem_num == $('#mem_num').val()){
								//본인 메시지
								output += '<div class="from-position">' + item.mem_name;
								output += '<div>';
							}else{
								//타인 메시지
								output += '<div class="to-position">';
								output += '<div class="space-photo">';
								//output += '<img src="../member/viewProfile.do?mem_num='+item.mem_num+'" width="40" height="40" class="my-photo">';
								output += '</div><div class="space-message">';
								output += item.mem_name;
							}
							output += '<div class="item">';
							output += '<span>' + item.message.replace(/\r\n/g,'<br>').replace(/\r/,'<br>').replace(/\n/,'<br>') + '</span>';
							//시간 표시
							if (item.timestamp && item.timestamp !== '') {
  								output += '<div class="align-right">' + item.timestamp.split(' ')[1] + '</div>';
								}							
							output += '</div>';
							output += '</div><div class="space-clear"></div>';
							output += '</div>';
						}
						
						//문서 객체에 추가
						$('#chatting_message').append(output);
						//스크롤을 하단에 위치시킴
						$('#chatting_message').scrollTop(
							   $('#chatting_message')[0].scrollHeight);
					});
					
				}else{
					alert('채팅 메시지 읽기 오류 발생');
					message_socket.close();
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
				message_socket.close();
			}
		});
	}
	
	//=========메시지 입력 후 enter 이벤트 처리=====//
	$('#message').keydown(function(event){
		if(event.keyCode == 13 && !event.shiftKey){
			$('#detail_form').trigger('submit');
		}
	});
	
	//=======채팅 등록========//
	$('#detail_form').submit(function(event){
		//기본 이벤트 제거
		event.preventDefault();
		
		if($('#message').val().trim()==''){
			alert('메시지를 입력하세요!');
			$('#message').val('').focus();
			return false;
		}
		
		//글자수 체크
		if($('#message').val().length>300){
			alert('메시지는 300자까지만 입력 가능합니다.');
			return false;
		}
		
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url:'../chat/writeChat.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
					message_socket.close();
				}else if(param.result == 'success'){
					//폼 초기화
					$('#message').val('').focus();
					//메시지가 저장되었다고 소켓에 신호를 보냄
					message_socket.send('msg:');
				}else{
					alert('메시지 등록 오류');
					message_socket.close();
				}
			},
			error:function(){
				alert('네트워크 오류!');
				message_socket.close();
			}
		});
	});
	
	
});




