<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/font-awesome.css" />
<title>식사 추가 게시판</title>

</head>
<body>
<div id="wrap">
<a href="<%=request.getContextPath() %>">
	<img class="circular--logo"
	src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
	<h1 class="member">식사 글쓰기</h1>
	<sform:form method="post" action="#" modelAttribute="postingData" enctype="multipart/form-data">
	<table>
 	<tr>
		<td width="70" align="center">제 목</td>
		<td align="left" width="330">
			<sform:input type="text" maxlength="30" path="postingTitle"/></td>
	</tr>

	<tr>
		<td width="70" align="center">내 용</td>
		<td align="left" width="330">
			<sform:textarea rows="13" cols="40" maxlength="300" path="postingContent"/></td>
	</tr>
	<tr>
		<td width="70" align="center">사진 첨부</td>
		<td align="left" width="330">
			<sform:input type="file" path="postingPhoto" input accept="image/*"/></td>
	</tr>
	<tr>
	<td colspan=2 align="center">
		<sform:button id="save" value="글쓰기" />
		<sform:button type="reset" value="다시작성"/>
		</td>
	</tr>
	</table>
	
	</sform:form>
</div>
</body>
</html>