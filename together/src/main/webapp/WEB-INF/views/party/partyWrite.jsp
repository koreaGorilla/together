<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!-- 중앙 컨텐츠 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hapalpal.css">
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
	<h2 id="rmfTmrl">파티생성</h2>
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
            <form:textarea path="party_content"/>
            <form:errors path="party_content" cssClass="error-color"/>
            <script>
             function MyCustomUploadAdapterPlugin(editor) {
                   editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                       return new UploadAdapter(loader);
                   }
               }
             
             ClassicEditor
                  .create( document.querySelector( '#party_content' ),{
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
				<label for="upload">프로필 사진</label>
				<img src="${pageContext.request.contextPath}/images/blank.png" id="party_photo" width="350" height="350">
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
			<form:button id="vkxltodtjd">파티생성</form:button>
			<input id="cnlth" type="button" value="취소" 
			             onclick="location.href='list.do'">
		</div>	                   
	</form:form>
</div>
<script>
$(function(){
    // 이미지 미리보기
    let photo_path = $('#party_photo').attr('src'); // 처음 화면에 보여지는 이미지 읽기
    let my_photo;
    $('#upload').change(function(){
       my_photo = this.files[0];
       if(!my_photo){
          $('#party_photo').attr('src',photo_path);
          return; // 선택한 이미지가 없으니 다시 선택하게끔
       }
       
       // 파일 용량 체크
       if(my_photo.size>1024*1024){
          alert(Math.round(my_photo.size/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
          $('#party_photo').attr('src',photo_path); // 용량이 너무 크면 처음 이미지를 보여준다.
          $(this).val(''); // 선택한 파일 정보 지우기
       }
       
       let reader = new FileReader();
       reader.readAsDataURL(my_photo);
       
       reader.onload = function(){
          $('#party_photo').attr('src',reader.result);
       };
    }); // end of change
});    
</script>
<!-- 중앙 컨텐츠 끝 -->