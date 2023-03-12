<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- party nav 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<div class="party-nav">
	<ul class="party-menu">
		<c:if test="${!empty party.party_num}">
			<li>
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party.party_num}">
							<div class="party-icon">
								<img src="${pageContext.request.contextPath}/image_bundle/party_home.png" width="50">
							</div>
							<p>메인</p>
						</a>
					</li>
					<li class="party-menu-hover">
						<a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party.party_num}">메인</a>
					</li>
				</ul>
			</li>
			<li>
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/calendar/calendar.do?party_num=${party.party_num}">
							<div class="party-icon">
								<img src="${pageContext.request.contextPath}/image_bundle/party_calendar.png" width="50">
							</div>
							<p>일정</p>
						</a>
					</li>
					<li class="party-menu-hover">
						<a href="${pageContext.request.contextPath}/calendar/calendar.do?party_num=${party.party_num}">일정</a>
					</li>
				</ul>
			</li>
			<li>
				<ul>
					<li>
						<a href="#" class="chatParty">
							<div class="party-icon">
								<img src="${pageContext.request.contextPath}/image_bundle/party_chat.png" width="50">
							</div>
							<p>채팅</p>
						</a>
					</li>
					<li class="party-menu-hover">
						<a href="#" class="chatParty">채팅</a>
					</li>
				</ul>			
				<!-- 윈도우창으로 채팅 오픈 -->
				<script type="text/javascript">
					$(".chatParty").on('click', function(e){
						event.preventDefault();
						window.open("${pageContext.request.contextPath}/chat/chatDetail.do?party_num=${party.party_num}", "chat", "width=600, height=700, top=200, left=200, resizable=no");
					});
				</script>
			</li>
			<c:if test="${nowMem.party_auth == 9}">
				<li>
					<ul>
						<li>
							<a href="${pageContext.request.contextPath}/partymember/partyMemberList.do?party_num=${party.party_num}">
								<div class="party-icon">
									<img src="${pageContext.request.contextPath}/image_bundle/party_board.png" width="50">
								</div>
								<p>가입 승인</p>
							</a>
						</li>
						<li class="party-menu-hover">
							<a href="${pageContext.request.contextPath}/partymember/partyMemberList.do?party_num=${party.party_num}">가입 승인</a>
						</li>
					</ul>
				</li>
			</c:if>
		</c:if>
		
		<c:if test="${empty party.party_num}">
			<li>
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party_num}">
							<div class="party-icon">
								<img src="${pageContext.request.contextPath}/image_bundle/party_home.png" width="50">
							</div>
							<p>메인</p>
						</a>
					</li>
					<li class="party-menu-hover">
						<a href="${pageContext.request.contextPath}/party/partyMain.do?party_num=${party_num}">메인</a>
					</li>
				</ul>
			</li>
			<li>
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/calendar/calendar.do?party_num=${party_num}">
							<div class="party-icon">
								<img src="${pageContext.request.contextPath}/image_bundle/party_calendar.png" width="50">
							</div>
							<p>일정</p>
						</a>
					</li>
					<li class="party-menu-hover">
						<a href="${pageContext.request.contextPath}/calendar/calendar.do?party_num=${party_num}">일정</a>
					</li>
				</ul>
			</li>
			<li>
				<ul>
					<li>
						<a href="#" class="chatParty">
							<div class="party-icon">
								<img src="${pageContext.request.contextPath}/image_bundle/party_chat.png" width="50">
							</div>
							<p>채팅</p>
						</a>
					</li>
					<li class="party-menu-hover">
						<a href="#" class="chatParty">채팅</a>
					</li>
				</ul>			
				<!-- 윈도우창으로 채팅 오픈 -->
				<script type="text/javascript">
					$(".chatParty").on('click', function(e){
						event.preventDefault();
						window.open("${pageContext.request.contextPath}/chat/chatDetail.do?party_num=${party_num}", "chat", "width=600, height=700, top=200, left=200, resizable=no");
					});
				</script>
			</li> 
			<c:if test="${nowMem.party_auth == 9}">
				<li>
					<ul>
						<li>
							<a href="${pageContext.request.contextPath}/partymember/partyMemberList.do?party_num=${party_num}">
								<div class="party-icon">
									<img src="${pageContext.request.contextPath}/image_bundle/party_board.png" width="50">
								</div>
								<p>가입 승인</p>
							</a>
						</li>
						<li class="party-menu-hover">
							<a href="${pageContext.request.contextPath}/partymember/partyMemberList.do?party_num=${party_num}">가입 승인</a>
						</li>
					</ul>
				</li>
			</c:if>
		</c:if>
	</ul>
</div>
<!-- party nav 끝 -->