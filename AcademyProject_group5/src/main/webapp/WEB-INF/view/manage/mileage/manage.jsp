<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
<title>물품 관리</title>
</head>
<body>
	<div class="bMargin" align="center">
		<form method="post" action="/mileageManage/manage" enctype="multipart/form-data"
				class="form-horizontal">
				
		<input type="hidden" name="productId" value="${productData.productId}"/>		
		<input type="hidden" id="deletePhoto" name="deletePhoto" value="false"> 
		
		<input type="text" name="productName" placeholder="물품명" value="${productData.productName }"/>
		<br>
		<textarea rows="13" cols="40" maxlength="1300"
				  name="productContent" placeholder="물품설명">${productData.productContent}</textarea>
		<br>
		<input type="number" name="productCost" placeholder="가격(마일리지)" value="${productData.productCost}"/>
		<br>
		<br>
		<input id="fileText" class="fileText" style="float:none; margin:0px;" type="text" disabled="disabled"/>
		<label for="fileInput" class="myButton">파일 선택</label>
		<input id="fileInput" type="file" name="uploadPhoto" accept="image/*" />
		<button id="imgDelete" class="myButton">삭제</button>
		<button id="imgCancel" class="myButton">기존 이미지</button>
		<br>
		<br>	
		<input type="submit" class="myButton" value="등록"/>
		</form>
	</div>
</body>

<script type="text/javascript">

var defaultCheckStr;
<c:choose>
	<c:when test="${productData.productImgfile != 'default.png'}">
		defaultCheckStr = "기존 이미지";
	</c:when>
	<c:otherwise>
		defaultCheckStr = "이미지 없음";
	</c:otherwise>
</c:choose>

// 기존 이미지
$("#imgCancel").click(function(event) {
	$("#fileInput").val("");
	$("#deletePhoto").val("false");
	$("#fileText").val(defaultCheckStr);
	event.preventDefault();
});

// 이미지 삭제
$("#imgDelete").click(function(event){
	event.preventDefault();
	$("#fileInput").val("");
	$("#deletePhoto").val("${postingData.postingPhoto}");
	$("#fileText").val("이미지 없음")	;
});

$(document).ready(function(){
	$("#fileText").val(defaultCheckStr);
	
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