<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/manage/header.jsp" />
<style>
	input[type=number] {
		width:100%;
	}
	
	input, textarea {
		margin-bottom: 15px;
		text-align: center;
	}
	

</style>
<title>물품 등록</title>
</head>
<body>
	<div class="bMargin" align="center">
		<form method="post" action="/mileageManage/add" enctype="multipart/form-data"
				class="form-horizontal">
				
		<input type="text" name="productName" placeholder="물품명"/>
		<br>
		<textarea rows="13" cols="40" maxlength="1300"
				  name="productContent" placeholder="물품설명"></textarea>
		<br>
		<input type="number" name="productCost" placeholder="가격(마일리지)"/>
		<br>
		<br>
		<input id="fileText" class="fileText" style="float:none; margin:0px;" type="text" value="파일 선택" disabled="disabled"/>
		<label for="fileInput" class="myButton">사진업로드</label>
		<input id="fileInput" type="file" name="uploadPhoto" accept="image/*" />
		<button id="imgCancel" class="myButton">취소</button>
		<br>
		<br>
		<input type="submit" class="myButton" value="등록"/>
		</form>
	</div>
</body>

<script type="text/javascript">
$("#imgCancel").click(function(event) {
	$("#fileInput").val("");
	$("#fileText").val("파일 선택");
	event.preventDefault();
});

$(document).ready(function(){
	 var fileTarget = $('#fileInput');
	 
	  fileTarget.on('change', function(){  // 값이 변경되면
		// modern browser
	    if(window.FileReader){  
	      var filename = $(this)[0].files[0].name;
	    } 
	 	// old IE
	    else {  
	      var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
	    }    
	    // 추출한 파일명 삽입
	    $(this).siblings('#fileText').val(filename);
	  });
});
</script>

</html>