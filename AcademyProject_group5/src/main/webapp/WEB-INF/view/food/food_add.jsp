<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/css/main.css" />
<link rel="stylesheet" href="/css/font-awesome.css" />
<title>식사 추가 게시판</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div id="wrap">

		<div class="upmargin">
			<h1 class="member">식사 글쓰기</h1>
			<form method="post" action="food" enctype="multipart/form-data">
				<table class="list_table">
					<colgroup>
						<col width="15%">
						<col width="85%">
					</colgroup>

					<tr>

						<td><label for="postingTitle">제 목</label></td>
						<td><input type="text" maxlength="30" name="postingTitle" value="${postingData.postingTitle }"/></td>

					</tr>

					<tr>

						<td><label for="postingContent">내 용</label></td>
						<td><textarea rows="13" cols="40" maxlength="1300"
								name="postingContent">${postingData.postingContent }</textarea></td>

					</tr>
					<tr>
						<td align="left"><label for="fileInput">사진
								첨부</label> <input id="fileInput" type="file" name="uploadPhoto"
							accept="image/*" /></td>
							<td align="left"><button id="imgCancel">이미지 취소</button></td>
					</tr>
					<tr>
						<td colspan=2 align="center"><button type="submit" id="save">글쓰기</button>
							<button type="reset">다시 작성</button></td>
					</tr>

				</table>

			</form>
		</div>
	</div>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$("#imgCancel").click(function(event){
		$("#fileInput").val("");
		event.preventDefault();
	});
</script>
</html>