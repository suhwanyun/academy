<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<title>식사 추가 게시판</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container cMargin">
		<div class="text-center">
			<h1>식사 글쓰기</h1>
		</div>

		<form method="post" action="food" enctype="multipart/form-data"
			class="form-horizontal">
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label for="postingTitle">제 목</label>
					</div>
					<div class="col-xs-9">
						<input type="text" maxlength="30" name="postingTitle"
							value="${postingData.postingTitle }" class="form-control" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label for="postingContent">내 용</label>
					</div>
					<div class="col-xs-9">
						<textarea rows="13" cols="40" maxlength="1300"
							name="postingContent" class="form-control">${postingData.postingContent }</textarea>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
						<div class="col-xs-4">
						<label for="fileInput">사진 첨부</label>
						</div>
						<div class="col-xs-8">
						<input id="fileInput"
								type="file" name="uploadPhoto" accept="image/*" />
						
				</div>
			</div>
			</div>		
						<button id="imgCancel" class="btn white">선택 취소</button>
			<div class="form-group bRight">
			
						<button type="submit" id="save" class="btn white vertical">글쓰기</button>
						<button type="reset" class="btn white vertical">다시 작성</button>
			</div>
			
		

</form>
</div>
</body>

<script type="text/javascript">
	$("#imgCancel").click(function(event) {
		$("#fileInput").val("");
		event.preventDefault();
	});
</script>
</html>