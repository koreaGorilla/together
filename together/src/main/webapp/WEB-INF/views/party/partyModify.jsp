<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 중앙 컨텐츠 시작 -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<script src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>글수정</h2>
	<form:form action="partyUpdate.do" id="partyUpdate_form" 
	                   modelAttribute="partyVO"
	                   enctype="multipart/form-data">
	    <form:hidden path="party_num"/>               
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		<li>
				<label for="upload">프로필 사진</label>
				<c:if test="${empty partyVO.party_photo_name }">
				<img src="${pageContext.request.contextPath}/images/blank.png" id="party_photo" width="200" height="200" class="party-photo">
				</c:if>
				<c:if test="${!empty partyVO.party_photo_name }">
				<img src="imageView.do?party_num=${partyVO.party_num}&party_type=2" id="party_photo" width="200" height="200" class="party-photo">
				</c:if>
				<input type="file" name="upload" id="upload">
				<c:if test="${!empty partyVO.party_photo_name}">
				<div id="file_detail">
					(${partyVO.party_photo_name})파일이 등록되어 있습니다.
					<input type="button" value="파일삭제"
					                     id="file_delete">
				</div>
				<script type="text/javascript">
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
						
						$('#file_delete').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{party_num:${partyVO.party_num}},
									type:'post',
									dataType:'json',
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요');
										}else if(param.result == 'success'){
											$('#file_detail').hide();
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
			<li>
				<label for="name">파티 이름</label>
				<form:input path="party_name"/>
				<form:errors path="party_name" 
				                  cssClass="error-color"/>
			</li>
			<li>
            <label for="content">내용</label>
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
			    <label for="reg_type">회원가입방식</label>
				<select name="party_reg_type" id="party_reg_type">
					<option value="0">즉시가입</option>
					<option value="1">승인 후 가입</option>
					
				</select>
			</li>
		</ul>
		<div class="align-center">
			<form:button>수정완료</form:button>
			<input type="button" value="수정취소" 
			   onclick="location.href='detail.do?party_num=${partyVO.party_num}'">
		</div>	                   
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->