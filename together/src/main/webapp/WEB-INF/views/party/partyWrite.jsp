<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 중앙 컨텐츠 시작 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>글쓰기</h2>
	<form:form action="write.do" id="register_form" 
	                   modelAttribute="partyVO"
	                   enctype="multipart/form-data">              
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="name">파티 이름</label>
				<form:input path="party_name"/>
				<form:errors path="party_name" 
				                  cssClass="error-color"/>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="party_content"/>
				<form:errors path="party_content"
				                  cssClass="error-color"/>
				          
			</li>
			<li>
				<label for="upload">사진</label>
				<input type="file" name="upload" id="upload">
			</li>
			<li>
			    <label for="hobby">취미 카테고리</label>
				<select name="party_hobby" id="party_hobby">
					<option value="1">운동</option>
					<option value="2">독서</option>
					<option value="3">음주</option>
					<option value="4">문화</option>
				</select>
			</li>
			
			
			<li>
			    <label for="reg_type">회원가입방식</label>
				<select name="party_reg_type" id="party_reg_type">
					<option value="0">즉시가입</option>
					<option value="1">승인 후 가입</option>
					
				</select>
			</li>
		</ul>
		<div class="align-center">
			<form:button>생성하기</form:button>
			<input type="button" value="취소하기" 
			             onclick="location.href='list.do'">
		</div>	                   
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->