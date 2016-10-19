<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
<title>식사 게시판 글 내용</title>
<script type="text/javascript">
function errorFun(e){
	e.src="<%=request.getContextPath()%>/upload/notFoundImg.png";
}
</script>
</head>
<body>
	<div id="wrap">
		<a href="<%=request.getContextPath()%>"> <img
			class="circular--logo"
			src="<%=request.getContextPath()%>/images/logo.png" alt="" /></a>
		<h1 class="member">식사 게시판 글 내용</h1>
	<div class="form10 pad">
		<table style="text-align: center">
			<tr>
				<td colspan="4">${postingData.postingTitle }</td>
			</tr>
			<c:if test="${postingData.postingPhoto ne 'default.png'}">
			<tr>
				<td colspan="4"><img onerror="errorFun(this)" src="<%=request.getContextPath()%>/upload/${postingData.postingPhoto}" /></td>
			</tr>
			</c:if>
			<tr>
				<td colspan="4">${postingData.postingContent } </td>
			</tr>
			<tr>
				<td colspan="3">이미지</td>
				<td>추천버튼</td>
			</tr>
		</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	alert("${postingData.postingPhoto}");
</script>
</html>