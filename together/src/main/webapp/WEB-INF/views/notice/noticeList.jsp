<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 중앙 컨텐츠 시작 -->
<script type="text/javascript">
   let result = '${result}';
   if(!location.hash && result == 'success'){
      alert('글쓰기 완료!!');
      history.replaceState('','','#rs');
   }
   
   $(function(){
      //검색 유효성 체크
      $('#search_form').submit(function(){
         if($('#keyword').val().trim()==''){
            alert('검색어를 입력하세요!');
            $('#keyword').val('').focus();
            return false;
         }
      });
   });
   
</script>
<div class="page-main">
   <h2 id="rhdwltkgkd">공지사항</h2>
<%--    <form action="list.do" id="search_form" method="get">
      <ul class="search">
         <li>
            <select name="keyfield" id="keyfield">
               <option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
               <option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
               <option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>제목+내용</option>
            </select>
         </li>
         <li>
            <input type="search" name="keyword" 
              id="keyword" value="${param.keyword}">
         </li>
         <li>
            <input type="submit" value="찾기">
            <input type="button" value="목록"
                     onclick="location.href='list.do'">
         </li>
      </ul>
   </form> --%>
   <c:if test="${!empty user && user.mem_auth == 9}">
   <div class="align-right">
      <input class="qjxms" type="button" value="글쓰기" 
          onclick="location.href='write.do'">
   </div>
   </c:if>
   <c:if test="${count == 0}">
   <div class="result-display">표시할 게시물이 없습니다.</div>
   </c:if>   
   <c:if test="${count > 0}">
   <table class="striped-table" id="stiped-table-hap">
      <tr>
         <th>번호</th>
         <th width="400">제목</th>
         <th></th>
         <th>작성일</th>
         <th>조회수</th>
      </tr>
      <c:forEach var="notice" items="${list}">
      <tr>
         <td>${notice.notice_num}</td>
         <td>
            <a href="detail.do?notice_num=${notice.notice_num}">${notice.notice_title}</a>
         </td>
         <td>
            ${notice.mem_id}
         </td>
         <td>${notice.notice_date}</td>
         <td>${notice.notice_hit}</td>
      </tr>
      </c:forEach>
   </table>
   <div class="align-center">${page}</div>
   </c:if>
</div>
<!-- 중앙 컨텐츠 끝 -->


