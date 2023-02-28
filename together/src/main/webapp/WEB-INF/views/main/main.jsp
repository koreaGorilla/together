<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/party.css"> 
<!-- 메인 시작 -->
<!-- 메인 시작 -->
<div id="page-main">
	<section>
	<div class="slideshow">
	
	<div class="Slides fade">
	  	<a href="#">
	  		<img src="../image_bundle/face.png">
	  	</a>
	</div>
	
	<div class="Slides fade">
	  	<a href="#">
	  		<img src="../image_bundle/face.png">
	  	</a>
	</div>
	
	<div class="Slides fade">
	  	<a href="#">
	  		<img src="../image_bundle/face.png">
	  	</a>
	</div>
	
	</div>
	<br>
	
	<div style="text-align:center">
	  <span class="dot"></span> 
	  <span class="dot"></span> 
	  <span class="dot"></span> 
	</div>
	<div class="party_menu">
		<nav>
			<ul>
				<li><a href="#"><img src="../image_bundle/face.png"><br>카테고리1</a></li>
				<li><a href="#"><img src="../image_bundle/face.png"><br>카테고리2</a></li>
			</ul>
		</nav>
	</div>
		<script>
			let slideIndex = 0;
			showSlides();
			function showSlides() {
			  let i;
			  let slides = document.getElementsByClassName("Slides");
			  let dots = document.getElementsByClassName("dot");
			  for (i = 0; i < slides.length; i++) {
			    slides[i].style.display = "none";  
			  }
			  slideIndex++;
			  if (slideIndex > slides.length) {slideIndex = 1}    
			  for (i = 0; i < dots.length; i++) {
			    dots[i].className = dots[i].className.replace(" active", "");
			  }
			  slides[slideIndex-1].style.display = "block";  
			  dots[slideIndex-1].className += " active";
			  setTimeout(showSlides, 2000); // 2초마다 슬라이드 변경
			}
		</script>
	</section>
</div>
	<div class="item-space">
		<c:forEach var="party" items="${list}">
		<div class="horizontal-area">
			<a href="${pageContext.request.contextPath}/party/detail.do?party_num=${party.party_num}">
				<img src="imageView.do?party_num=${party.party_num}">
				<span>${party.party_name}</span>
				<c:if test="${party.party_hobby == 1}">운동</c:if>
				<c:if test="${party.party_hobby == 2}">독서</c:if>
				<c:if test="${party.party_hobby == 3}">음주</c:if>
				<c:if test="${party.party_hobby == 4}">문화</c:if>
			</a>
		</div>
		</c:forEach>
		<div class="float-clear">
			<hr width="100%" size="1" noshade="noshade">
		</div>
	</div>
<!-- 메인 끝 -->