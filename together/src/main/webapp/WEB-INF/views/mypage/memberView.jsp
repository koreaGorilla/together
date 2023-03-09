<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="btns">
<button type="button" id="review">리뷰 목록</button>
<button type="button" id="fav_review">좋아요 목록</button>
<button type="button" id="in_party">가입한 파티</button>
<button type="button" id="fav_party">찜한 파티</button>
</div>
<div class="page-main">
  <table class="striped-table" id="striped-table" style="display: none;">
    <tr>
      <th>리뷰번호</th>
      <th>파티명</th>
      <th>내용</th>
      <th>등록일</th>
    </tr>
    <c:forEach var="review" items="${reviewList}">
      <tr>
        <td>${review.r_num}</td>
        <td>${review.party_name}</td>   
        <td>
          <a href="${pageContext.request.contextPath}/review/detail.do?r_num=${review.r_num}">
            ${review.r_content}
          </a>
        </td>
        <td>${review.r_date}</td>
      </tr>
    </c:forEach>
  </table>

<!-- 내가 쓴 리뷰 목록 끝 -->
<!-- 좋아요 누른 리뷰 목록 시작 -->
<table class="striped-table" id="striped-table2" style="display: none;">
<tr>
<th>리뷰번호</th>
<th>내용</th>
<th>날짜</th>
</tr>
   <c:forEach var="review" items="${getReview}">
      <tr>
      <td>${review.r_num}</td>
      <td>
         <a href="${pageContext.request.contextPath}/review/detail.do?r_num=${review.r_num}">${review.r_content}</a>
      </td>
      <td>${review.r_date}</td>
      </tr>
   </c:forEach>
</table>
<!-- 좋아요 누른 리뷰 목록 끝 -->
<!-- 내가 가입한 파티 목록 -->
   <table class="striped-table" id="striped-table3" style="display: none;">
   <tr>
   <th>파티 번호</th>
   <th>파티명</th>
   <th>가입 날짜</th>
   </tr>
      <c:forEach var="party" items="${partyList}">
         <tr>
         <td>${party.party_num}</td>
         <td>
            <a href="${pageContext.request.contextPath}/party/detail.do?party_num=${party.party_num}">${party.party_name }</a>
         </td>
         <td>${party.party_reg_date}</td>
         </tr>
      </c:forEach>
   </table>

<!-- 내가 가입한 파티 목록 끝 -->
<!-- 내가 찜한 파티 목록 -->
   <table class="striped-table" id="striped-table4" style="display: none;">
   <tr>
   <th>파티번호</th>
   <th>파티명</th>
   <th>날짜</th>
   </tr>
      <c:forEach var="party" items="${getList}">
         <tr>
         <td>${party.party_num}</td>
         <td>
            <a href="${pageContext.request.contextPath}/party/detail.do?party_num=${party.party_num}">${party.party_name}</a>
         </td>
         <td>${party.party_reg_date}</td>
         </tr>
      </c:forEach>
   </table>
</div>
<!-- 내가 찜한 파티 목록 끝 -->
<script type="text/javascript">
const reviewBtn = document.getElementById("review");
const stripedTable = document.getElementById("striped-table");
const favReviewBtn = document.getElementById("fav_review");
const stripedTable2 = document.getElementById("striped-table2");
const inPartyBtn = document.getElementById("in_party");
const stripedTable3 = document.getElementById("striped-table3");
const favPartyBtn = document.getElementById("fav_party");
const stripedTable4 = document.getElementById("striped-table4");

reviewBtn.addEventListener("click", function() {
  if (stripedTable.style.display === "none") {
    stripedTable.style.display = "table";
  } else {
    stripedTable.style.display = "none";
  }
});

favReviewBtn.addEventListener("click", function() {
	  if (stripedTable2.style.display === "none") {
	    stripedTable2.style.display = "table";
	  } else {
	    stripedTable2.style.display = "none";
	  }
	});

inPartyBtn.addEventListener("click", function() {
	  if (stripedTable3.style.display === "none") {
	    stripedTable3.style.display = "table";
	  } else {
	    stripedTable3.style.display = "none";
	  }
	});

favPartyBtn.addEventListener("click", function() {
	  if (stripedTable4.style.display === "none") {
	    stripedTable4.style.display = "table";
	  } else {
	    stripedTable4.style.display = "none";
	  }
	});
</script>