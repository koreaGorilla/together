<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>채팅방 목록</title>
</head>
<body>
	<h1>채팅방 목록</h1>
	
	<table>
		<tbody>
		<c:if test="${not empty chatRooms}">		
	      <c:forEach var="chatRoom" items="${chatRooms}">
	        <c:if test="${chatRoom.party_num == party_num}">
	          <tr>
	            <td>
	            	<a href="chatDetail.do?party_num=${chatRoom.party_num}">${chatRoom.party_name}</a>
	            </td>
	            <td>${chatRoom.party_name}</td>
	          </tr>
	        </c:if>
	      </c:forEach>
		</c:if>
	     <c:if test="${empty chatRooms}" >
	     	파티를 가입해주세요
	     </c:if>
		</tbody>
	</table>
	
</body>
</html>