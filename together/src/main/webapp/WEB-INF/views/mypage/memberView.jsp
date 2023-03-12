<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내 정보 시작 -->
<div class="member-detail">
	<h3>이름</h3>
	<input type="text" class="myPage-detail" value="${member.mem_name}" readonly/>
	
	<h3>취미</h3>
	<input type="text" class="myPage-detail" value="${member.hobby}" readonly/>
	
	<h3>전화번호</h3>
	<input type="text" class="myPage-detail" value="${member.mem_cell}" readonly/>
	
	<h3>이메일</h3>
	<input type="text" class="myPage-detail" value="${member.mem_email}" readonly/>
	
	<h3>주소</h3>
	<input type="text" class="myPage-detail" value="(${member.mem_zipcode}) ${member.mem_address1} ${member.mem_address2}" readonly/>
	
	<h3>가입일</h3>
	<input type="text" class="myPage-detail" value="${member.reg_date}" readonly/>
	
	<c:if test="${!empty member.modify_date}">
		<h3>수정일</h3>
		<input type="text" class="myPage-detail" value="${member.modify_date}" readonly/>
	</c:if>
	
	<input type="button" value="회원정보 수정" id="member_modify_btn" onclick="location.href='update.do'">
</div>
<!-- 내 정보 끝 -->

<!-- 나의 파티 시작 -->
<div class="mpList">
	<table>
		<tr>
			<th style="width:100px">파티 번호</th>
			<th style="width:auto">파티 이름</th>
			<th style="width:150px">생성 날짜</th>
		</tr>
		<c:forEach var="party" items="${partyList}">
         	<tr>
         		<td style="text-align:center">${party.party_num}</td>
         		<td style="padding:0 15px"><a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party.party_num}">${party.party_name}</a></td>
         		<td style="text-align:center">${party.party_reg_date}</td>
         	</tr>
		</c:forEach>
	</table>
</div>
<!-- 나의 파티 끝 -->

<!-- 나의 리뷰 시작 -->
<div class="mrList">
	<table>
		<tr>
			<th style="width:100px">리뷰 번호</th>
			<th style="width:150px">파티 이름</th>
			<th style="width:280px">내용</th>
			<th style="width:150px">등록일</th>
		</tr>
		<c:forEach var="review" items="${reviewList}">
			<tr>
				<td style="text-align:center">${review.r_num}</td>
				<td style="margin-left:5px">${review.party_name}</td>   
				<td style="margin-left:5px"><a href="${pageContext.request.contextPath}/review/detail.do?r_num=${review.r_num}"><span>${review.r_content}</span></a></td>
	        	<td style="text-align:center">${review.r_date}</td>
			</tr>
	    </c:forEach>
	</table>
</div>
<!--  나의 리뷰 끝 -->

<!-- 가입한 파티 시작 -->
<div class="mpartyList">
	<table>
		<tr>
			<th style="width:150px">파티번호</th>
			<th>파티 이름</th>
		</tr>
		<c:forEach var="party" items="${getMyparty}">
			<tr>
				<td style="text-align:center">${party.party_num}</td>
				<td><a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party.party_num}">${party.party_name}</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<!-- 가입한 파티 끝 -->

<!-- 찜한 파티 시작 -->
<div class="fpList">
	<table>
		<tr>
			<th style="width:150px">파티번호</th>
			<th>파티 이름</th>
		</tr>
		<c:forEach var="party" items="${getList}">
			<tr>
				<td style="text-align:center">${party.party_num}</td>
				<td><a href="${pageContext.request.contextPath}/party/detail.do?party_num=${party.party_num}">${party.party_name}</a></td>
         	</tr>
      </c:forEach>
	</table>
</div>
<!-- 찜한 파티 끝 -->

<!-- 좋아요 누른 리뷰 시작 -->
<div class="frList">
	<table>
		<tr>
			<th style="width:150px">리뷰 번호</th>
			<th>내용</th>
		</tr>
		<c:forEach var="review" items="${getReview}">
			<tr>
				<td style="text-align:center">${review.r_num}</td>
				<td><a href="${pageContext.request.contextPath}/review/detail.do?r_num=${review.r_num}">${review.r_content}</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<!-- 좋아요 누른 리뷰 끝 -->
<script type="text/javascript">
const reviewBtn = document.getElementById("review");
const stripedTable = document.getElementById("striped-table");
const favReviewBtn = document.getElementById("fav_review");
const stripedTable2 = document.getElementById("striped-table2");
const inPartyBtn = document.getElementById("in_party");
const stripedTable3 = document.getElementById("striped-table3");
const favPartyBtn = document.getElementById("fav_party");
const stripedTable4 = document.getElementById("striped-table4");
const myPartyBtn = document.getElementById("my_party");
const stripedTable5 = document.getElementById("striped-table5");

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
myPartyBtn.addEventListener("click", function() {
	  if (stripedTable5.style.display === "none") {
	    stripedTable5.style.display = "table";
	  } else {
	    stripedTable5.style.display = "none";
	  }
	});
</script>