<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 중앙 컨텐츠 시작 -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/datepicker.css">
<script src="${pageContext.request.contextPath}/js/calendarModify.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<div id="calendarModify">
	<h3>수정</h3>
		<form:form action="calendarModify.do" id="calendarModify_form" modelAttribute="calendar">
			<input type="hidden" value="${calendar.calendar_num}">
			<form:hidden path="calendar_num"/>
			<form:errors element="div" cssClass="error-color" />
			<ul>
				<li>
					<label for="title">제목</label>
					<form:input path="title" id="title" /> <form:errors path="title" cssClass="error-color" />
				</li>
				<li>
					<label for="content">내용</label>
					<form:textarea path="content" id="content" />
					<form:errors path="content" cssClass="error-color" />
				</li>
				<li>
					<label>일시</label>
					<input type="text" id="start_date" name="start_date" value="${calendar.start_date}"> - <input type="text" id="end_date" name="end_date" value="${calendar.end_date}">
					<form:errors path="start_date" cssClass="error-color" />
					<form:errors path="end_date" cssClass="error-color" />
				</li>
				<li>
					<label for="start_time">시작시간</label>
					<input type="text" id="start_time" name="start_time" value="${calendar.start_time}">
					<form:errors path="start_time" cssClass="error-color" />
				</li>
				<li>
					<label for="end_time">종료시간</label>
					<input type="text" id="end_time" name="end_time" value="${calendar.end_time}">
					<form:errors path="end_time" cssClass="error-color" />
				</li>
				<li>
					<label for="location">장소</label>
					<input type="text" id="location" name="location" value="${calendar.location}">
					<form:errors path="location" cssClass="error-color" />
					<div id="map" style="width: 75%; height: 400px;"></div>
					키워드 : <input type="text" id="keyword" size="15">
					<button id="search_btn" type="button">검색하기</button>
					<div id="menu-wrap">
						<ul id="placesList"></ul>
						<div id="pagination" class="align-center"></div>
					</div>
				</li>
				<li>
					<label>색상</label>
					<form:radiobutton path="color" value="#5b99ff" id="color1"/><div style="width:30px;height:30px;background-color:#5b99ff;"></div>
					<form:radiobutton path="color" value="#ff6565" id="color2"/><div style="width:30px;height:30px;background-color:#ff6565;"></div>
					<form:radiobutton path="color" value="#ffae00" id="color3"/><div style="width:30px;height:30px;background-color:#ffae00;"></div>
					<form:radiobutton path="color" value="#ffa5cb" id="color4"/><div style="width:30px;height:30px;background-color:#ffa5cb;"></div>
				</li>
			</ul>
			<div class="align-center">
				<form:button>수정</form:button>
				<input type="button" value="취소" id="cancel_btn">
			</div>
		</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->