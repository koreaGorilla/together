<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${count == 0}">
	<div class="result-display">표시할 회원정보가 없습니다.</div>
</c:if>
<c:if test="${count > 0}">
	<table class="striped-table">
		<h2>파티원 목록</h2>
		<tr>
			<th>이름</th>
			<th>파티 가입일</th>
			<th>회원분류</th>
		</tr>
		<c:forEach var="member" items="${list}">
			<c:if test="${member.party_auth == 0}">
				<tr>
					<td>${member.mem_name}</td>
					<td>${member.partymem_reg_date}</td>
					<td><c:if test="${member.party_auth==0}">일반회원</c:if> 
					    <c:if test="${member.party_auth==9}">파티장</c:if>
					    </td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
</c:if>


<c:if test="${count > 0}">
	<table class="striped-table">
		<h2>대기회원 목록</h2>
		<tr>
			<th>이름</th>
			<th>신청일자</th>
			<th>회원분류</th>
		</tr>
		<c:forEach var="member" items="${list}">
			<c:if test="${member.party_auth == 1}">
				<tr>
					<td>${member.mem_name}</td>
					<td>${member.partymem_reg_date}</td>
					<td><c:if test="${member.party_auth==1}">대기회원</c:if>
						<c:if test="${!empty member}">
						<input type="hidden" id="partymem_num" name="partymem_num" value="${member.partymem_num}">
						<input type="hidden" id="party_num" name="party_num" value="${member.party_num}">
							<input type="button" value="승인" id="apply_btn">
							<input type="button" value="삭제" id="delete_btn">
							<script type="text/javascript">
								$('#apply_btn').click(function() {
									
									let party_auth = 0;
									let partymem_num = $('#partymem_num').val();
									$.ajax ({
										url: '/partymember/modifyAuth',
										type: 'POST',
										data: {
											"partymem_num" : partymem_num,
											"party_auth" : party_auth
										},
										success: function(param) {
											if(param.result == 'logout'){
												alert('로그인 후 사용하세요');
												location.href='../member/login.do';
											}else if(param.result == 'success'){
												alert('승인되었습니다.');
												location.reload();
											}
										},
										error:function(){
											alert('네트워크 오류 발생');
										}
									});
									
								});
								
								$(function(){
									$('#delete_btn').click(function(){
										let choice = confirm('삭제하시겠습니까?');
										let partymem_num = $('#partymem_num').val();
										let party_num = $('#party_num').val();
										if(choice){
											$.ajax ({
												url: '/partymember/delete.do',
												type: 'POST',
												data: {
													"partymem_num" : partymem_num,
													"party_num" : party_num
												},
												success: function(param) {
													if(param.result == 'logout'){
														alert('로그인 후 사용하세요');
														location.href='../member/login.do';
													}else if(param.result == 'success'){
														alert('삭제 완료되었습니다.');
														location.reload();
													}
												},
												error:function(){
													alert('네트워크 오류 발생');
												}
											});
												
										}
									});
								});
							</script>
						</c:if>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
</c:if>
</body>
</html>