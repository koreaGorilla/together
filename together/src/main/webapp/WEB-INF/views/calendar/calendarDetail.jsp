<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 중앙 컨텐츠 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.4/index.global.min.js'></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d66fecf4a3d09020f40dff08e5f3c4b5&libraries=services"></script>
<script src="${pageContext.request.contextPath}/js/calendarDetail.js"></script>
<div id="calendar"></div>
<div id="calendarDetail">
	<input type="text" value="${party_num}" id="party_num">
	<h2>상세</h2>
	<ul>
		<li>${calendar.title}</li>
		<li>${calendar.content}</li>
		<li>${calendar.start_date} ${calendar.start_time} - ${calendar.end_date} ${calendar.end_time}</li>
		<li>${calendar.location}<input type="button" id="find" value="길찾기" data-location="${calendar.location}"></li>
		<li>
			<div id="map" style="width:100%;height:350px;"></div>
		</li>
	</ul>	
	<c:if test="${!empty user && user.mem_num == calendar.mem_num}">
		<div class="align-center">
			<input type="button" value="수정" onclick="location.href='calendarModify.do?calendar_num=${calendar.calendar_num}&party_num=${party_num}'">
			<input type="button" value="삭제" id="delete_btn">
			<script type="text/javascript">
				let delete_btn = document.getElementById('delete_btn');
					
				delete_btn.onclick=function(){
					let choice = confirm('삭제하시겠습니까?');
						
					if(choice){
						location.replace('calendarDelete.do?calendar_num=${calendar.calendar_num}');
					}
				};
			</script> 
		</div>
	</c:if>
	<c:if test="${count > 0}">
		<div class="align-center">
			<b>참여인원 : ${count}</b><br>
			<c:forEach var="member" items="${member}">
				<img src="profileImg.do?mem_num=${member.mem_num}" width="40" height="40" class="my-photo">
			</c:forEach>		
		</div>
	</c:if>
	<c:if test="${!empty user && user.mem_num != calendar.mem_num}">
		<div class="align-center">
			<input type="hidden" value="참여" id="participation_btn">
			<input type="hidden" value="미참여" id="notparticipation_btn">
		</div>
	</c:if>
	<input type="hidden" id="calendar_num" data-calendarnum="${calendar.calendar_num}">
</div> 
<!-- 중앙 컨텐츠 끝 -->