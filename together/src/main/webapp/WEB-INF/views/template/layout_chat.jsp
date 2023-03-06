<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/chat.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head> 
<body>
<div id="chat">
	<div id="chat_body">
		<tiles:insertAttribute name="body"/>
	</div>
</div>	
</body>
</html>




