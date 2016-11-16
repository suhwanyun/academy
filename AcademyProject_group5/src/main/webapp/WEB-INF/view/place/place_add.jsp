<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 작성</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container cMargin">
		<div class="text-center">
			<img width="100%" alt="데이트코스 게시판" src="/images/placePosting.png">
		</div>

		<form method="post" action="place" enctype="multipart/form-data"
			class="form-horizontal">
			<div class="form-group">
				<div class="row">
					<div class="col-xs-2">
						<label for="postingTitle">제 목</label>
					</div>
					<div class="col-xs-10">
						<input type="text" maxlength="30" name="postingTitle"
							value="${postingData.postingTitle }" class="form-control" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-2">
						<label for="postingContent">내 용</label>
					</div>
					<div class="col-xs-10">
						<textarea rows="13" cols="40" maxlength="1300"
							name="postingContent" class="form-control">${postingData.postingContent }</textarea>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
						<div class="col-xs-2">
						</div>
						<div class="col-xs-10" align="right">
						<input id="fileText" class="fileText" type="text" value="파일 선택" disabled="disabled"/>
						<label for="fileInput" class="myButton">사진업로드</label>
						<input id="fileInput" type="file" name="uploadPhoto" accept="image/*" />
						<button id="imgCancel" class="btn white">취소</button>
				</div>
			</div>
			</div>		
			<div class="form-group bRight">
			
						<button type="submit" id="save" class="btn white vertical">글쓰기</button>
			</div>
			
		

</form>
</div>
</body>

<script type="text/javascript">
	$("#imgCancel").click(function(event) {
		$("#fileInput").val("");
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