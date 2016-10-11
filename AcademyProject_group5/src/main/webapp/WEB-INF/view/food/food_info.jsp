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
				<td colspan="4">제목</td>
			</tr>
			<tr>
				<td colspan="4">사진</td>
			</tr>
			<tr>
				<td colspan="4">게시글</td>
			</tr>
			<tr>
				<td colspan="3">이미지</td>
				<td>추천버튼</td>
			</tr>
			<tr>
				<td colspan="3">댓글 달기</td>
				<td>등록버튼</td>
			</tr>
			<tr>
				<td>날짜</td>
				<td>댓글내용</td>
				<td>작성자</td>
				<td>댓글달기버튼</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>