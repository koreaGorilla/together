<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 비밀번호찾기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/findIdPw.css">
</head>
<body>
  <div class="container">
    <h1>아이디/비밀번호 찾기</h1>
    <div class="link-container">
      <div class="link-box">
        <a href="${pageContext.request.contextPath}/find/findId.do">아이디 찾기</a>
      </div>
      <div class="link-box">
        <a href="${pageContext.request.contextPath}/find/checkCell.do">비밀번호 찾기</a>
      </div>
    </div>
  </div>
</body>
</html>