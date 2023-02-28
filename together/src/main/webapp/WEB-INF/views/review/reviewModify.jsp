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
	<h2>리뷰수정</h2>
	<form:form action="update.do" id="update_form" modelAttribute="reviewVO" enctype="multipart/form-data">
		<form:hidden path="r_num"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		    <li>
				<label for="party_name">파티명</label>
				<label>${reviewVO.party_name}</label>
				<form:errors path="party_name" cssClss="error-color"/>
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
				<c:if test="${!empty reviewVO.r_photoname}">
				<div id="photo_detail">
					(${reviewVO.r_photoname})이미지가 등록되어 있습니다.
					<input type="button" value="이미지 삭제" id="photo_del">
					
				</div>
				<script type="text/javascript">
					$(function(){
						$('#photo_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							//deletefile.do에 board_num을 post방식으로 보내서 json 방식으로 받음?
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{r_num:${reviewVO.r_num}},
									type:'post',
									dataType:'json',
									success:function(param){
										if(param.result=='logout'){
											alert('로그인 후 사용하세요');
										}else if(param.result =='success'){
											$('#photo_detail').hide(); //파일명 가려줌
										}else{
											alert('파일 삭제 오류 발생');
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
			</li>
		</ul>
		<div class="align-center">
			<form:button>수정</form:button>
         	<input type="button" value="목록" onclick="location.href='list.do'">
      	</div>
      	
	</form:form>
</div>