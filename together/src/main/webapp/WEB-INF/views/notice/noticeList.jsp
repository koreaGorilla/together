<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 중앙 컨텐츠 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<script type="text/javascript">
   let result = '${result}';
   if(!location.hash && result == 'success'){
      alert('글쓰기 완료!!');
      history.replaceState('','','#rs');
   }
</script>
<div class="notice-main">
   <c:if test="${!empty user && user.mem_auth == 9}">
	   <div class="align-right">
	      <input class="notice-wbtn" type="button" value="글쓰기" onclick="location.href='write.do'">
	   </div>
   </c:if>
   <c:if test="${count == 0}">
   <div class="result-display">표시할 게시물이 없습니다.</div>
   </c:if>   
   <c:if test="${count > 0}">
   <table class="noticeList">
      <tr>
         <th style="width:150px">번호</th>
         <th style="width:auto">제목</th>
         <th style="width:150px">작성일</th>
         <th style="width:150px">조회수</th>
      </tr>
      <c:forEach var="notice" items="${list}">
      <tr>
         <td style="text-align:center">${notice.notice_num}</td>
         <td>
            <a href="detail.do?notice_num=${notice.notice_num}">${notice.notice_title}</a>
         </td>
         <td style="text-align:center">${notice.notice_date}</td>
         <td style="text-align:center">${notice.notice_hit}</td>
      </tr>
      </c:forEach>
   </table>
   <div class="align-center">${page}</div>
   </c:if>
</div>
<!-- 중앙 컨텐츠 끝 -->


