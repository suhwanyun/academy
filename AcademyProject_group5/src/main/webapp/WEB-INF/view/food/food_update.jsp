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
	<div class="container text-center center">


		<h1>글 수정</h1>
		<form method="post" action="/write/postingUpdate"
			enctype="multipart/form-data">
			<table class="table">
				<colgroup>
					<col width="15%">
					<col width="15%">
					<col width="70%">
				</colgroup>

				<tr>
					<td><label for="postingTitle">제 목</label></td>
					<td colspan="2"><input type="text" maxlength="30"
						name="postingTitle" value="${postingData.postingTitle }"
						class="sizeInput" /></td>
				</tr>
				<tr>
					<td><label for="postingContent">내 용</label></td>
					<td colspan="2"><textarea style="" rows="13" maxlength="1300"
							name="postingContent">${postingData.postingContent }</textarea></td>
				</tr>
				<tr id="imgShow" align="left">
					<td></td>
					<td><button id="imgUpdate" class="myButton">이미지 수정</button></td>
					<td>
						<button id="imgdelete" class="myButton">이미지 삭제</button>
						<span id="checkImg"></span>
					</td>
				</tr>
				<tr class="imgSetting" align="left" hidden="true">
					<td></td>
					<td><button class="myButton" id="imgCancel">기존 이미지</button></td>
					<td><input id="fileInput" type="file" name="uploadPhoto" style="width:100%" accept="image/*" /></td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="hidden" id="deletePhoto" name="deletePhoto" value="false"> 
						<input type="hidden" name="postingId" value="${postingData.postingId}">
						<button type="submit" id="save" class="myButton">글 수정</button>
						<button id="cancel" class="myButton">취소</button>
					</td>
				</tr>
			</table>

		</form>
	</div>

</body>

<script type="text/javascript">
	var defaultCheckStr;
	<c:choose>
		<c:when test="${postingData.postingPhoto != 'default.png'}">
			defaultCheckStr = "기존 이미지";
		</c:when>
		<c:otherwise>
			defaultCheckStr = "이미지 없음";
		</c:otherwise>
	</c:choose>
	
	$(document).ready(function(){
		$("#checkImg").html(defaultCheckStr);
	});
	
	//기존 이미지
	$("#imgCancel").click(function(event){
		event.preventDefault();
		$("#fileInput").val("");
		$(".imgSetting").attr("hidden","true");
		$("#imgShow").removeAttr("hidden");
		$("#deletePhoto").val("false");
		$("#checkImg").html(defaultCheckStr);
	});
	//이미지 수정
	$("#imgUpdate").click(function(event){
		event.preventDefault();
		$("#imgShow").attr("hidden","true");
		$(".imgSetting").removeAttr("hidden");
		$("#deletePhoto").val("${postingData.postingPhoto}");
	});
	// 이미지 삭제
	$("#imgdelete").click(function(event){
		event.preventDefault();
		$("#fileInput").val("");
		$("#deletePhoto").val("${postingData.postingPhoto}");
		$("#checkImg").html("이미지 없음")	;
	});
	// 취소
	$("cancel").click(function(event){
		event.preventDefault();
		$(location).attr('href', "/foodMain");
	});
</script>
</html>