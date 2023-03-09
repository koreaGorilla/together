<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 중앙 컨텐츠 시작 -->
<script src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hapal.css">
<div class="page-main">
   <h2>${notice.notice_title}</h2>
   <ul class="detail-info">
      <li>
         <br>
         <c:if test="${!empty notice.notice_modifydate}">
         최근 수정일 : ${notice.notice_modifydate}
         </c:if>
         <c:if test="${empty notice.notice_modifydate}">
         작성일 : ${notice.notice_date}
         </c:if>
         조회 : ${notice.notice_hit}
      </li>
   </ul>
   <c:if test="${!empty notice.notice_filename}">
   <ul>
      <li>
         첨부파일 : <a href="file.do?notice_num=${notice.notice_num}">${notice.notice_filename}</a>
      </li>   
   </ul>
   </c:if>
   <hr size="1" width="100%">
   <c:if test="${fn:endsWith(notice.notice_filename,'.jpg') || 
                 fn:endsWith(notice.notice_filename,'.JPG') ||
              fn:endsWith(notice.notice_filename,'.jpeg') ||
              fn:endsWith(notice.notice_filename,'.JPEG') ||
              fn:endsWith(notice.notice_filename,'.gif') ||
              fn:endsWith(notice.notice_filename,'.GIF') ||
              fn:endsWith(notice.notice_filename,'.png') ||
              fn:endsWith(notice.notice_filename,'.PNG')}">
    <div class="align-center">
      <img src="imageView.do?notice_num=${notice.notice_num}" class="detail-img">
   </div>
   </c:if>
   <p>
      ${notice.notice_content}
   </p>
   <hr size="1" width="100%">
   <div class="align-right">
      <c:if test="${!empty user && user.mem_num == notice.mem_num}">
      <input type="button" value="수정" 
        onclick="location.href='update.do?notice_num=${notice.notice_num}'">
      <input type="button" value="삭제" id="delete_btn"> 
      <script type="text/javascript">
         let delete_btn = document.getElementById('delete_btn');
         delete_btn.onclick=function(){
            let choice = confirm('삭제하시겠습니까?');
            if(choice){
               location.replace('delete.do?notice_num=${notice.notice_num}');
            }
         };
      </script> 
      </c:if> 
      <input type="button" value="목록"
                 onclick="location.href='list.do'">
   </div>
</div>
<!-- 중앙 컨텐츠 끝 -->



