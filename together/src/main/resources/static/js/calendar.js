$(function(){
	//===========fullCalnedar 생성===========//
	var request = $.ajax({
		url: '/calendar/calendarList.do', // 변경하기
		method: 'get',
		dataType: 'json',
		data: {party_num:$('#party_num').val()}
	});

	request.done(function (data) {
		console.log(data); // log 로 데이터 찍어주기.
	
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(calendarEl, {
			headerToolbar: {
				start: 'prev,next today',
				center: 'title',
				end: 'dayGridMonth,timeGridDay',
			},
			initialView: 'dayGridMonth',
			customButtons: {
				add: {
					text: '일정 추가',
					click: function() {
						location.href='calendarWrite.do?party_num=' + $('#party_num').val();
					}
				}
            },
            footerToolbar: {
				end: 'add'
            },
			navLinks: true,
			navLinkWeekClick: function(date, jsEvent) {
				console.log('day', date.toISOString());
				console.log('coords', jsEvent.pageX, jsEvent.pageY);
			},
			events: data,
			locale: 'ko',
			eventClick: function(info){
				location.href='calendarDetail.do?party_num=' + $('#party_num').val() + '&calendar_num=' + info.event.id;
			},
			dateClick: function(info){
				location.href='calendarWrite2.do?party_num=' + $('#party_num').val() + '&start_date=' + info.dateStr;
			}
		});
		calendar.render();
	});

	request.fail(function(jqXHR, textStatus ) {
		alert('달력 불러오기 실패');
	});
});