<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- 메인 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<div id="main_body">
	<div class="slider">
	  <ul>
	    <li class="item item1">
		    <a href="${pageContext.request.contextPath}/party/detail.do?party_num=${recent_list[0].party_num}">
		    	<img src="imageView.do?party_num=${recent_list[0].party_num}" width="800">
				<div class="txt">${recent_list[0].party_name}</div>
			</a>
	    </li>
	    <li class="item item2"> 
		    <a href="${pageContext.request.contextPath}/party/detail.do?party_num=${recent_list[1].party_num}">
				<img src="imageView.do?party_num=${recent_list[1].party_num}" width="800">
				<div class="txt">${recent_list[1].party_name}</div>
			</a>
	    </li>
	    <li class="item item3">
		    <a href="${pageContext.request.contextPath}/party/detail.do?party_num=${recent_list[2].party_num}">
				<img src="imageView.do?party_num=${recent_list[2].party_num}" width="800">
				<div class="txt">${recent_list[2].party_name}</div>
			</a>
	    </li>
	    <li class="item item4">
		    <a href="${pageContext.request.contextPath}/party/detail.do?party_num=${recent_list[3].party_num}">
				<img src="imageView.do?party_num=${recent_list[3].party_num}" width="800">
				<div class="txt">${recent_list[3].party_name}</div>
			</a>
	    </li>
	    <li class="item item5">
		    <a href="${pageContext.request.contextPath}/party/detail.do?party_num=${recent_list[4].party_num}">
				<img src="imageView.do?party_num=${recent_list[4].party_num}" width="800">
				<div class="txt">${recent_list[4].party_name}</div>
			</a>
	    </li>
	  </ul>
	  <img src="${pageContext.request.contextPath}/image_bundle/prev.png" width="50" class="prev_btn">
	  <img src="${pageContext.request.contextPath}/image_bundle/next.png" width="50" class="next_btn">
	</div>
	<script>
	$(function(){
		var pt = [];
		var maxZindex = 3; // 가장높은 인덱스
		var $item = $('.item');
		var $tbox = $('.text-box');
		
		var init = function(){
			valInp(function(i,e){
				var o = {};
				o.top = $(e).position().top;
				o.left = $(e).position().left;
				o.height = $(e).outerHeight();
				o['z-index'] = $(e).css('z-index');
				pt[i] = o;
			});
	
			for(var i=0;i<2;i++) {
		    	pt.push(pt.shift());
		  	}
	
		  	valInp(function(i,e){
		    	$(e).css(pt[i]);
		    	setZindex(e,function(e){
			      	$(e).find('.txt').css({'display':'block','bottom':0});
			      	$(e).find('.filter').css({'filter':'none'});
		    	});
		  	});
		}
	
		function valInp(fn){
			$item.each(function(i,e){
		    	fn(i,e);
		  	});
		}
	
		function setZindex(e,fn){
		  	if($(e).css('z-index') == maxZindex){
		    	fn(e);
		  	}
		}
	
		function anime(){
		  	valInp(function(i,e){
		    	$(e).css('z-index',pt[i]['z-index']).stop(true,true).animate(pt[i],200,function(){
		      		$(e).find('.txt').css({'display':'none','bottom':-40});
		      		setZindex(e,function(e){
		        		$(e).find('.txt').css('display','block').stop(true,true).animate({'bottom':0},100);
		      		});
		    	});
		  	});
		}
	
		// prev
		$('.prev_btn').on('click',function(e){
		  pt.push(pt.shift());
		  anime();
		});
		
		// next
		$('.next_btn').on('click',function(e){
		  pt.unshift(pt.pop());
		  anime();
		});
	
	 	setInterval(function() { 
			pt.unshift(pt.pop());
			anime();
		}, 5000);
		
		init();
	});
	</script>
	<div class="party-category">
		<ul>
			<li class="cate-bg"><a href="${pageContext.request.contextPath}/party/list.do?party_hobby=1"><img src="${pageContext.request.contextPath}/image_bundle/exercise.png"><p class="cate-title"># 운동</p></a></li>
			<li class="cate-bg"><a href="${pageContext.request.contextPath}/party/list.do?party_hobby=2"><img src="${pageContext.request.contextPath}/image_bundle/book.png"><p class="cate-title"># 독서</p></a></li>
			<li class="cate-bg"><a href="${pageContext.request.contextPath}/party/list.do?party_hobby=3"><img src="${pageContext.request.contextPath}/image_bundle/alcohol.png"><p class="cate-title"># 음주</p></a></li>
			<li class="cate-bg"><a href="${pageContext.request.contextPath}/party/list.do?party_hobby=4"><img src="${pageContext.request.contextPath}/image_bundle/culture.png"><p class="cate-title"># 문화</p></a></li>
		</ul>
	</div>
	<div class="party-list">
		<c:if test="${!empty user}">
			<p class="party-list-p"><a href="${pageContext.request.contextPath}/mypage/myPage.do">나의 파티 ></a></p>
			<c:forEach var="party" items="${myparty}">
				<div class="party-list-item">
					<a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party.party_num}">
						<div id="party_list_img"><img src="imageView.do?party_num=${party.party_num}"></div>
						<div class="party-list-pname">${party.party_name}</div>
						<c:if test="${party.party_hobby == 1}"><p># 운동</p></c:if>
						<c:if test="${party.party_hobby == 2}"><p># 독서</p></c:if>
						<c:if test="${party.party_hobby == 3}"><p># 음주</p></c:if>
						<c:if test="${party.party_hobby == 4}"><p># 문화</p></c:if>
					</a>
				</div> 
			</c:forEach>
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><hr class="party-list-hr">
			<p class="party-list-p"><a href="${pageContext.request.contextPath}/party/list.do">전체 파티 ></a></p>
		</c:if>
		
		<c:forEach var="party" items="${list}">
			<div class="party-list-item">
				<a href="${pageContext.request.contextPath}/party/detail.do?party_num=${party.party_num}">
					<div id="party_list_img"><img src="imageView.do?party_num=${party.party_num}"></div>
					<div class="party-list-pname">${party.party_name}</div>
					<c:if test="${party.party_hobby == 1}"><p># 운동</p></c:if>
					<c:if test="${party.party_hobby == 2}"><p># 독서</p></c:if>
					<c:if test="${party.party_hobby == 3}"><p># 음주</p></c:if>
					<c:if test="${party.party_hobby == 4}"><p># 문화</p></c:if>
				</a>
			</div>
		</c:forEach>
		<div class="float-clear"></div>
	</div>
</div>
<!-- 메인 끝 -->