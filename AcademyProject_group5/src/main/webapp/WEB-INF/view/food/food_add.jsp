<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="/css/main.css" />
<link rel="stylesheet"
	href="/css/font-awesome.css" />
<title>식사 추가 게시판</title>

</head>
<body>

<div id="wrap">
<a href="">
	<img class="circular--logo"
	src="/images/logo.png" alt="" /></a>
	<h1 class="member">식사 글쓰기</h1>
		<form method="post" action="food" enctype="multipart/form-data">
			<table>
				<colgroup>
					<col width="10%">
					<col width="90%">
				</colgroup>
				
				<tr>

					<td><label for="postingTitle">제 목</label></td>
					<td><input type="text" maxlength="30"
							name="postingTitle" /></td>

				</tr>

				<tr>

					<td><label for="postingContent">내 용</label></td>
					<td><textarea rows="13" cols="40" maxlength="1300" name="postingContent"></textarea></td>

				</tr>
				<tr>
					<td colspan="2" align="left"><label for="fileInput">사진 첨부</label>
					<input id="fileInput" type="file" name="uploadPhoto" accept="image/*"/></td>
				</tr>
				<tr>
					<td colspan=2 align="center"><button type="submit" id="save">글쓰기</button>
					<button type="reset">다시 작성</button></td>
				</tr>
				
			</table>

		</form>
	</div>

</body>
</html>