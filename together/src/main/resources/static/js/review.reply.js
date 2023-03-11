$(function(){
	let currentPage;
	let count;
	let rowCount;
	
	//댓글 목록
	function selectList(pageNum){
		currentPage = pageNum;
		
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'listReply.do',
			type:'post',
			data:{pageNum:pageNum,r_num:$('#r_num').val()}, //jsp에서 hidden 값으로 넣은 값 가져오기
			dataType:'json',
			success:function(param){
				//로딩 이미지 감추기
				$('#loading').hide();
				count=param.count;
				console.log(count);
				rowCount = param.rowCount;
				
				if(pageNum==1){
					//처음 호출이 해당 ID의 div의 내부 내용물 제거
					$('#output').empty();
					//댓글 아이콘 옆에 댓글 개수 표시
					$('#output_rcount').text(param.count);
				}
				//댓글 목록 작업
				$(param.list).each(function(index,item){
					let output = '<div class="item">';
					output += '<ul class="detail-infoo" id="detail-infoo">';
					output += '<li>';
					output += '<img src="../mypage/viewProfile.do?mem_num='+item.mem_num+'"width="40" height="40" class="my-photo">';
					output += '</li>';
					output += '<li>';
					output += '<span class="reply_mem_name">'+item.mem_name+'</span>'  ;
					if(item.c_modify_date){
						output += '<span class="modify-date">' + item.c_modify_date+' 수정</span>';
					}else{
						output += '<span class="modify-date"> ' + item.c_date + '</span>';
					}
					output += '</li>';
					output += '</ul>';
					output += '<div class="sub-item">';
					output += '<p class="reply-content">' + item.c_content.replace(/\r\n/g,'<br>')+'</p>';
					
					if(param.user_num==item.mem_num){
						output += '<input type="button" data-num="'+item.c_num+'" value="수정" class="modify-btnn">';
						output += '<input type="button" data-num="'+item.c_num+'" value="삭제" class="delete-btnn">';
					}
					
					output += '<hr class="review-hr">';
					output += '</div>';
					output += '</div>';
					
					//문서 객체에 데이터 추가
					$('#output').append(output);
					
				});
				//paging button 처리
				if(currentPage>=Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
					//console.log(currentPage);
					//console.log(count/rowCount);
					
				}else{
					//다음 페이지가 존재
					$('.paging-button').show();
				}
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류 발생')
			}
		});
	}
	
	//다음 댓글 보기 버튼 클릭시 데이터 추가
	$('.paging-button input').click(function(){
		selectList(currentPage+1);
	});
	
	//댓글 등록
	$('#re_form').submit(function(event){
		//기본 이벤트 제거
		event.preventDefault();
		
		if($('#c_content').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#c_content').val('').focus();
			return false;
		}
		let form_data  = $(this).serialize();
		$.ajax({
			url:'writeReply.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				
				if(param.result=='logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result=='success'){
					//폼 초기화
					initForm();
					//등록된 데이터가 표시될 수 있도록 목록 갱신
					selectList(1);
					//console.log(currentPage);
					//console.log(rowCount);
					
				}
			},
			error:function(){
				alert('네트워크 오류 발생!!!!');
			}
		});
	});
	
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#reply_first .letter-count').text('100/100');
	}
	
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
		//입력한 글자수 구하기
		let inputLength = $(this).val().length;
		
		if(inputLength > 100){ //100자를 넘을 경우
			$(this).val($(this).val.substring(0,100));
		}else{//100자 이하인 경우
			//남은 글자수 구하기
			let remain = 100 - inputLength;
			remain +=  '/100';
			if($(this).attr('id') == 'c_content'){
				//등록 폼 글자수
				$('#reply_first .letter-count').text(remain);
			}else{
				//수정 폼 글자수 
				$('#mreply_first .letter-count').text(remain);
			}
		}
	});
	
	//댓글 수정
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-btnn',function(){
		//댓글 글번호
		let c_num = $(this).attr('data-num'); //커스텀 데이터 속성에서 가져오기
		//댓글 내용 읽어오기
		let content = $(this).parent().find('p').html().replace(/<br>/g,'\r\n');
		
		//댓글수정 폼 UI
		let modifyUI = '<form id="mreply_form">';
		modifyUI += '<input type="hidden" name="c_num" id="mc_num" value="'+c_num+'">';//댓글 글번호 hidden
		modifyUI += '<textarea rows="1" cols="50" name="c_content" id="mc_content" class="rep-content">' + content +'</textarea>';//입력한 내용 가져오기
		modifyUI += '<div id="mreply_first"><span class="letter-count">100/100</span></div>';
		modifyUI += '<div id="mreply_second" class="align-right">';
		modifyUI += '<input type="submit" value="수정">';
		modifyUI += '<input type="button" value="취소" class="content-reset">';
		modifyUI += '</div>';
		modifyUI += '<hr size="1" noshade width="96%">';
		modifyUI += '</form>';
		
		//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면 숨김
		//sub-item을 환원시키고 수정폼을 초기화
		initModifyForm();
		//지금 클릭해서 수정하고자 하는 원래 데이터는 감추기
		//수정버튼을 감싸고 있는 div(sub-item)
		$(this).parent().hide();//parent가 sub-item
		
		//수정폼을 수정하고자 하는 데이터가 이는 div에 노출
		$(this).parents('.item').append(modifyUI); //parent:직계부모, parents:모든부모 //모든 부모 중에서 item
		
		//입력한 글자수 세팅
		let inputLength = $('#mc_content').val().length;
		let remain = 100 - inputLength;
		remain += '/100';
		
		//문서 객체에 반영
		$('#mreply_first .letter-count').text(remain);
	});
	
	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.content-reset',function(){
		initModifyForm();
	});
	
	//댓글 수정 폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mreply_form').remove();
	}
	
	//댓글 수정 처리
	$(document).on('submit','#mreply_form',function(event){
		//기본 이벤트 제거
		event.preventDefault();
		if($('#mc_content').val().trim()==''){
			alert('내용을 입력하세요!');
			$('#mc_content').val('').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url:'modifyReply.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result=='logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(param.result=='success'){
					$('#mreply_form').parent().find('p').html($('#mc_content').val().replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/\r\n/g,'<br>').replace(/\r/g,'<br>').replace(/\n/g,'<br>')); //textarea로부터 데이터 읽어와서 p태그에 넣어줌
					//최근 수정일 표시
					$('#mreply_form').parent().find('.modify-date').text('최근 수정일 : 5초미만');
					//수정폼 초기화
					initModifyForm();//폼이 사라짐
				}else if(param.result=='wrongAccess'){
					alert('타인의 글은 수정할 수 없습니다.');
				}else{
					alert('댓글 수정시 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//댓글 삭제
	$(document).on('click','.delete-btnn',function(){
		//댓글 번호
		let c_num = $(this).attr('data-num');
		
		//서버와 통신
		$.ajax({
			url:'deleteReply.do',
			type:'post',
			data:{c_num:c_num},
			dataType:'json',
			success:function(param){
				if(param.result=='logout'){
					alert('로그인해야 삭제할 수 있습니다.');
					return false;
				}else if(param.result=='success'){
					alert('댓글이 삭제되었습니다!');
					selectList(1);
				}else if(param.result=='wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('댓글 삭제시 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	
	//초기데이터(목록) 호출
	selectList(1); //1페이지 읽어오기
});