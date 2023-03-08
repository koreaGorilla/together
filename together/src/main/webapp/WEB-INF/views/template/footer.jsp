<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 하단 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<div class="float-clear">
	<div class="footer">
		<div class="footer-content-left">
			<div class="footer-menu">
				<ul>
					<li class="footer-logo"><a href="${pageContext.request.contextPath}/main/main.do">Together</a></li>
					<li style="line-height: 30px;  font-size: 10px;"><a href="${pageContext.request.contextPath}/notice/list.do">공지사항</a></li>
					<li style="line-height: 30px; color: #b4b4b5; font-size: 10px;"><a href="${pageContext.request.contextPath}/party/list.do">파티</a></li>
					<li style="line-height: 30px; color: #b4b4b5; font-size: 10px;"><a href="${pageContext.request.contextPath}/review/list.do">리뷰</a></li>
					<li style="line-height: 30px; color: #b4b4b5; font-size: 10px;"><a href="">이벤트</a></li>
				</ul>
			</div>
			<div class="footer-info">
				<span>(주)Together | 대표자:노경한 | 사업자 번호:214-85-29296 | 개인정보처리방침 | 이용약관</span>
				<br>
				<span>주소:서울 강남구 테헤란로 132 한독약품빌딩 8층</span>
				<p>ⓒTogether. ALL RIGHTS RESERVED</p>
			</div>
		</div>
		<div class="footer-content-right">
			<img src="${pageContext.request.contextPath}/image_bundle/naver.png" width="30">
			<img src="${pageContext.request.contextPath}/image_bundle/instagram.png" width="30">
			<img src="${pageContext.request.contextPath}/image_bundle/youtube.png" width="30">
			<img src="${pageContext.request.contextPath}/image_bundle/facebook.png" width="30">
			<img src="${pageContext.request.contextPath}/image_bundle/twitter.png" width="30">
		</div>
	</div>
</div>
<a class="top-btn"><img src="${pageContext.request.contextPath}/image_bundle/top.png" width="50" ></a>
<script>
$(function(){
	$(window).scroll(function(){
		if($(this).scrollTop() > 200){
			$('.top-btn').fadeIn();
		} else{
			$('.top-btn').fadeOut();
		}
	});
	
	$('.top-btn').click(function(){
		$('html, body').animate({scrollTop : 0}, 400);
		
		return false;
	})
 });
</script>
<!-- 하단 끝 -->