<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/findIdPw.css">
</head>

<body>
  <div class="containerId">
<form method="post" class="findId" action="findMyId" name="findMyId">
		<div class="inputName">
			<label for="mem_name">이름</label>
			<input type="text" id="mem_name" name="mem_name" class="form-control"/>
		</div>
		
		<div class="inputCell">
			<label for="mem_phone">전화번호</label>
			<input type="text" id="mem_cell" name="mem_cell" class="form-control"/>
		</div>

		<div class="findId-btn">
			<input class="findId-submit-btn"
				type="submit" value="확인"">
		</div>
		<!-- 이름과 전화번호가 일치하지 않을 때-->
		<div class="result">
			<c:if test="${check == 1}">
				<label>일치하는 정보가 존재하지 않습니다.</label>
			</c:if>
	
			<!-- 이름과 비밀번호가 일치하지 않을 때 -->
			<c:if test="${check == 0 && id!=null}">
			<label>찾으시는 아이디는' ${id}' 입니다.</label>
			</c:if>
		</div>
	</form>
	</div>
	
</body>
</html>