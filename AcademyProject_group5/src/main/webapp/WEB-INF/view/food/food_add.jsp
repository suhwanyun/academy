<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
<title>식사 추가 게시판</title>

</head>
<body>

<div id="wrap">
<a href="<%=request.getContextPath() %>">
	<img class="circular--logo"
	src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
	<h1 class="member">식사 글쓰기</h1>
		<sform:form method="post" action="food" modelAttribute="posting">
			<table>
				<colgroup>
					<col width="10%">
					<col width="90%">
				</colgroup>
				
				<tr>

					<td><label for="postingTitle">제 목</label></td>
					<td><sform:input type="text" maxlength="30"
							path="postingTitle" /></td>

				</tr>

				<tr>

					<td><label for="postingContent">내 용</label></td>
					<td><sform:textarea rows="13" cols="40" maxlength="300"
							path="postingContent" /></td>

				</tr>
				
				<tr>
					<td colspan=2 align="center"><sform:button id="save" >글 쓰기</sform:button>
					<sform:button type="reset">다시 작성</sform:button></td>


				</tr>
				<sform:hidden path="postingId" value=""/>
				<sform:hidden path="postingType" value="food"/>
				<sform:hidden path="userId" value="${user.userId }" />
				<sform:hidden path="postingTime" value=""/>
				<sform:hidden path="postingHits" value="0"/>
				<sform:hidden path="postingRecommand" value="0"/>
				<sform:hidden path="postingPhoto" value=""/>
			</table>

		</sform:form>
	
		<form id="fileform">
			<label for="fileInput">사진 첨부</label>
			<input id="fileInput" type="file" accept="image/*"/>
		</form>
	</div>

</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
<c:url value="/uploadfile" var="uploadfile"/>
	$("#fileInput").change(function(event){
		var form = $("#fileform")[0];
		var formData = new FormData(form);

		console.log(formData);
		console.log(typeof formData);
			$.ajax({
			type : "post",
			url : "${uploadfile}",
			data :  formData,
			contentType: false,
		    processData: false,
			success : function(res) {
				if(res == ""){
					// 기본 이미지 경로 작성
				}
				else {
				$("#postingPhoto").val(res);
				}
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:"
						+ error);
			}
		});
	});
</script>
</html>