<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">

<script type="text/javascript">
   $(function(){
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
   <h2 class="page-title">리뷰</h2>
      <div class="review-main">
      <form class="search-form" action="list.do" id="search-form" method="get">
         <ul class="search_form">
            <li>
               <select name="keyfield" id="keyfield">
                  <option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>작성자</option>
                  <option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>내용</option>
                  <option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>작성자+내용</option>
                  
               </select>
            </li>
            <li>
               <input type="search" name="keyword" id="keyword" value="${param.keyword}">
            </li>
            <li>
               <input type="submit" value="검색">
               <input type="button" value="목록" onclick="location.href='list.do'">
               
            </li>
         </ul>
      </form>
      <div class="align_right">
         <c:if test="${!empty user && partyCount>0}">
         <input class="align_right" type="button" value="리뷰작성" onclick="location.href='write.do'">
         </c:if>
      </div>
      
      <!-- <div class="sort">
         <input type="button" value="날짜순" data-sortnum=0 onclick="location.href='list.do'">
         <input type="button" value="좋아요순" data-sortnum=1 onclick="location.href='list.do'">
      </div> -->
      
      <c:if test="${count==0}">
      <div class="result-display">표시할 리뷰가 없습니다.</div>
      </c:if>
      <c:if test="${count>0}">
      <c:forEach var="review" items="${list}" varStatus="status">
      <table class="review-table"   onclick="location.href='detail.do?r_num=${review.r_num}'" style="cursor:pointer;">
         <tr>
            <td  rowspan="2" width="40px" height="40px"  >
            <c:if test="${!empty review.photo_name}">
            <img src="imageView.do?r_num=${review.r_num }&review_type=1" width="40" height="40" class="my-photo">
            </c:if>
            <c:if test="${empty review.photo_name}">
            <img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
            </c:if>
            </td>
            <td class="review_mem_name" width="100px" >${review.mem_name}<br></td>
            
            
            <c:if test="${!empty review.r_photoname}">
            <td width="100px" rowspan="4" class="review-photo">
            <img class="review-image" src="imageView.do?r_num=${review.r_num }&review_type=2" width="200px" height="150px">
            </td>
            </c:if>
            
         </tr>
         <tr>
            <td class="review-date" width="500px">${review.r_date} 작성</td>
            
         </tr>
            
         
         
         <tr class="review_content">
         <c:choose>
            <c:when test="${fn:length(review.r_content) > 123}">
                <td colspan="2   "><c:out value="${fn:substring(review.r_content,0,122)}" escapeXml="false"/>. . . 더보기</td>   
             </c:when>
             <c:otherwise>
                <td colspan="2"><c:out value="${review.r_content}" escapeXml="false"/></td>   
             </c:otherwise> 
           </c:choose>
           </tr>
   
         <%-- <tr id="review_icon">
            좋아요 + 댓글 아이콘
            <td>
            <img id="output_fav" data-num="${review.r_num}" src="${pageContext.request.contextPath}/images/fav_1.png" width="40">
            <span id="output_fcount"></span>
            <img id="output_reply"    src="${pageContext.request.contextPath}/images/reply.png" width="40">
            <span id="output_rcount"></span>
            <span>${favcount[status.index].fav}</span>
            </td>
            
         </tr> --%>
         <tr>
            <th class="party_name" colspan="2">${review.party_name }</th>
         </tr>
      </table>
      <hr size="1" width="100%">
      </c:forEach>
      </c:if>
   </div>
   <div class="align-center">
      ${page}
   </div>
</div>