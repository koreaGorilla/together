<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.ck-editor__editable_inline{
   min-height:250px;
}
</style>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hyem.css">
<div class="notice-main">
   <form:form action="write.do" id="noticeWrite_form" modelAttribute="noticeVO" enctype="multipart/form-data">
      <form:errors element="div" cssClass="error-color"/>
      <ul>
         <li>
            <label for="notice_title" class="updateform-label">제목</label>
            <form:input path="notice_title" class="updateform-title"/>
            <form:errors path="notice_title" cssClass="error-color"/>
         </li>
         <li>
            <form:textarea path="notice_content"/>
            <form:errors path="notice_content" cssClass="error-color"/>
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
            <label id="vkdlfdjqfhem" for="upload" class="updateform-label">파일업로드</label>
            <input type="file" name="upload" id="notice_file">
         </li>
      </ul>
      <div class="align-center">
         <form:button class="update-btn">확인</form:button>
         <input class="update-cancel" type="button" value="취소" onclick="location.href='list.do'">
      </div>                      
   </form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->