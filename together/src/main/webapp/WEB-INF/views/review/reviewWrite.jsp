<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	<h2>리뷰</h2>
	<form:form action="write.do" id="write_form" modelAttribute="reviewVO" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		    <li>
				<label for="party_name">파티명</label>
					
						<%--<c:if test="${!empty party_name}"> --%>
							<form:select path="party_name">
								<c:forEach var="party_name" items="${list}">
									<option value="${party_name}">${party_name}</option>
								</c:forEach>
							</form:select>
						<%--</c:if> --%>
						
					
					<%-- 
					<c:if test="${empty party_name}">
						<span>가입하신 파티가 없습니다!</span>
					</c:if>
					--%>
				
			</li>	
			<li>
            <label for="r_content">내용</label>
            <form:textarea path="r_content"/>
            <form:errors path="r_content" cssClass="error-color"/>
            <script>
             function MyCustomUploadAdapterPlugin(editor) {
                   editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                       return new UploadAdapter(loader);
                   }
               }
             
             ClassicEditor
                  .create( document.querySelector( '#r_content' ),{
                     extraPlugins: [MyCustomUploadAdapterPlugin]
                  })
                  .then( editor => {
                  window.editor = editor;
               } )
                  .catch( error => {
                      console.error( error );
                  } );
             </script> 
         </li>
         <li>
				<label for="upload">이미지 업로드</label>
				<input type="file" name="upload" id="upload" accept="image/gif,image/jpeg,image/png">
			</li>
		</ul>
		<div class="align-center">
			<form:button>등록</form:button>
         	<input type="button" value="목록" onclick="location.href='list.do'">
      	</div>
	</form:form>
</div>