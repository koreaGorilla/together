<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 중앙 컨텐츠 시작 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hapal.css">
<style>
.ck-editor__editable_inline{
   min-height:250px;
}
</style>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
   <h2 id="rmfTmrl">글쓰기</h2>
   <form:form action="write.do" id="register_form" 
                      modelAttribute="noticeVO"
                      enctype="multipart/form-data">
      <form:errors element="div" cssClass="error-color"/>
      <ul>
         <li>
            <label for="notice_title">제목</label>
            <form:input path="notice_title"/>
            <form:errors path="notice_title" 
                              cssClass="error-color"/>
         </li>
         <li>
            <form:textarea path="notice_content"/>
            <form:errors path="notice_content"
                              cssClass="error-color"/>
            <script>
             function MyCustomUploadAdapterPlugin(editor) {
                   editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                       return new UploadAdapter(loader);
                   }
               }
             
             ClassicEditor
                  .create( document.querySelector( '#notice_content' ),{
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
            <label id="vkdlfdjqfhem" for="upload">파일업로드</label>
            <input type="file" name="upload" id="notice_file">
         </li>
      </ul>
      <div class="align-center">
         <form:button class="qjxms">완료</form:button>
         <input class="qjxms" type="button" value="목록" 
                      onclick="location.href='list.do'">
      </div>                      
   </form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->