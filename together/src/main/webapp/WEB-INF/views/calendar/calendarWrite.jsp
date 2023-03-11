<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.4/index.global.min.js'></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d66fecf4a3d09020f40dff08e5f3c4b5&libraries=services"></script>
<script src="${pageContext.request.contextPath}/js/calendarWrite.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<div class="calendar-main">
	<div id="calendar"></div>
</div>
<div id="calendarWrite">
	<form:form action="calendarWrite.do" id="addSchedule_form" modelAttribute="calendarVO">
		<input type="hidden" value="${party_num}" id="party_num" name="party_num">
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="title" id="title" class="addSchedule_form_input"/>
			</li>
			<li>
				<label for="content" id="write_content">내용</label>
				<form:textarea path="content" id="content" />
			</li>
			<li>
				<label>일시</label>
				<c:if test="${!empty start_date}">
					<input type="text" id="start_date" name="start_date" value="${start_date}" readonly>
				</c:if>
				<c:if test="${empty start_date}">
					<input type="text" id="start_date" name="start_date" autocomplete="off" readonly>
				</c:if>
				- <input type="text" id="end_date" name="end_date" autocomplete="off" readonly>
			</li>
			<li>
				<label>시간</label>
				<input type="text" id="start_time" name="start_time"  autocomplete="off" readonly> -
				<input type="text" id="end_time" name="end_time"  autocomplete="off" readonly>
			</li>
			<li>
				<label for="location">장소</label>
				<input type="text" id="location" name="location" class="addSchedule_form_input" readonly>
			</li>
			<li>
				<form:radiobutton path="color" value="#5b99ff" id="color1" checked="checked"/><div class="write-color" style="background-color:#5b99ff;"></div>
				<form:radiobutton path="color" value="#ff6565" id="color2"/><div class="write-color" style="background-color:#ff6565;"></div>
				<form:radiobutton path="color" value="#ffae00" id="color3"/><div class="write-color" style="background-color:#ffae00;"></div>
				<form:radiobutton path="color" value="#ffa5cb" id="color4"/><div class="write-color" style="background-color:#ffa5cb;"></div>
			</li>
		</ul>
		<div class="write-btn">
			<form:button id="write_btn">등록</form:button>
			<input type="button" value="취소" id="cancel_btn">
		</div>
	</form:form>
	<div id="write_map">
		<div class="search-area">
			<input type="text" id="keyword" autocomplete="off"><button id="search_btn" type="button">검색</button>
			<div id="menu-wrap">
				<ul id="placesList"></ul>
				<div id="pagination"></div>
			</div>
		</div>
	</div>
</div>