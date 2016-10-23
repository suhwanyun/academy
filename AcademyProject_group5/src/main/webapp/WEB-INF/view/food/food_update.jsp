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
			<h1 class="member">글 수정</h1>
			<form method="post" action="/write/postingUpdate" enctype="multipart/form-data">
				<table class="list_table">
					<colgroup>
						<col width="10%">
						<col width="90%">
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
						<td id="imgShow" colspan="2" align="left">
							<button id="imgUpdate">이미지 수정 </button>
							<button id="imgdelete">이미지 삭제 </button>
							<span id="checkImg">기존 이미지</span>
						</td>
						<td class="imgSetting" align="left" hidden="true" colspan="2">
							<input id="fileInput" type="file" name="uploadPhoto" accept="image/*" />
							<button id="imgCancel">기존 이미지로</button>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="hidden" id="deletePhoto" name="deletePhoto" value="false">
							<input type="hidden" name="postingId" value="${postingData.postingId}">
							<button type="submit" id="save">글 수정</button>
							<button id="cancel">취소</button>
						</td>
					</tr>
				</table>

			</form>
		</div>
	</div>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
//기존 이미지
	$("#imgCancel").click(function(event){
		event.preventDefault();
		$("#fileInput").val("");
		$(".imgSetting").attr("hidden","true");
		$("#imgShow").removeAttr("hidden");
		$("#deletePhoto").val("false");
		$("#checkImg").html("기존 이미지")	
	});
	//이미지 수정
	$("#imgUpdate").click(function(event){
		event.preventDefault();
		$("#imgShow").attr("hidden","true");
		$(".imgSetting").removeAttr("hidden");
		$("#deletePhoto").val("false");
	});
	$("#imgdelete").click(function(event){
		event.preventDefault();
		$("#fileInput").val("");
		$("#deletePhoto").val("${postingData.postingPhoto}");
		$("#checkImg").html("이미지 없음")	
		});
</script>
</html>