$(function(){
	var geocoder = new kakao.maps.services.Geocoder();
	var lat, lng;
	
	//주소로 경도, 위도 구하기
	var callback = function(result, status) {
	    if (status === kakao.maps.services.Status.OK) {
	        lat = result[0].x;
			lng = result[0].y;
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = { 
			        center: new kakao.maps.LatLng(lng, lat), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };
		
			var map = new kakao.maps.Map(mapContainer, mapOption);
			
			// 마커가 표시될 위치입니다 
			var markerPosition  = new kakao.maps.LatLng(lng, lat); 
			
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});
			
			kakao.maps.event.addListener(marker, 'click', function() {
				window.open('https://map.kakao.com/link/to/' + $('#find').attr('data-location') + ',' + lng + ',' + lat);
			});
			
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
	    }
	};

	geocoder.addressSearch($('#find').attr('data-location'), callback);

	//길찾기 버튼
	$('#find').click(function(){
		window.open('https://map.kakao.com/link/to/' + $(this).attr('data-location') + ',' + lng + ',' + lat);
	});
	
	//참여 여부 읽기
	$.ajax({
		url : 'getParticipation.do',
		type : 'post',
		data : {calendar_num:$('#calendar_num').attr('data-calendarnum')},
		dataType : 'json',
		success : function(param){
			if(param.status == 'participation'){
				$('#notparticipation_btn').prop('type', 'button');
			} else if(param.status == 'notparticipation'){
				$('#participation_btn').prop('type', 'button');
			} else{
				alert('읽기 오류');
			}
		},
		error : function(){
			alert('네트워크 오류');
		}
	});
	
	//참여 버튼
	$('#participation_btn').click(function(){
		$.ajax({
			url : 'participation.do',
			type : 'post',
			data : {calendar_num:$('#calendar_num').attr('data-calendarnum')},
			dataType : 'json',
			success : function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용해주세요');
				} else if(param.result == 'success'){
					$('#participation_btn').prop('type', 'hidden');
					$('#notparticipation_btn').prop('type', 'button');
					
					location.href='calendarDetail.do?calendar_num=' + $('#calendar_num').attr('data-calendarnum');
				} else{
					alert('참여 오류');
				}
			},
			error : function(){
				alert('네트워크 오류');
			}
		});
	});
	
	//미참여 버튼
	$('#notparticipation_btn').click(function(){
		$.ajax({
			url : 'notparticipation.do',
			type : 'post',
			data : {calendar_num:$('#calendar_num').attr('data-calendarnum')},
			dataType : 'json',
			success : function(param){
				if(param.result == 'success'){
					$('#participation_btn').prop('type', 'button');
					$('#notparticipation_btn').prop('type', 'hidden');
					
					location.href='calendarDetail.do?calendar_num=' + $('#calendar_num').attr('data-calendarnum');
				} else{
					alert('참여 오류');
				}
			},
			error : function(){
				alert('네트워크 오류');
			}
		});
	});
});