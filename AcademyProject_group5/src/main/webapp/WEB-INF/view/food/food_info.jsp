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
<title>식사 게시판 글 쓰기</title>

</head>
<body>
	<div id="wrap">
		<a href="<%=request.getContextPath()%>"> <img
			class="circular--logo"
			src="<%=request.getContextPath()%>/images/logo.png" alt="" /></a>
		<h1 class="member">식사 게시판 글 쓰기</h1>
		<form name="writeForm" method="post" action="writePro.do">
			<table class="list_table">
				<colgroup>
					<col width="20%" />
					<col width="80%" />
				</colgroup>
				
				<tr>
					<td align="center">제 목</td>
					<td align="left"><input type="text" size="10" maxlength="10"
						name="writer"></td>
				</tr>
				<tbody>
					<tr>
						<td><img class="circular--small"
							src="<%=request.getContextPath()%>/images/pic02.png" alt="" /></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea name="content" rows="13" cols="40"></textarea></td>
					</tr>

					<tr>
						<td><a href="#" class="button big alt"><span>추천</span></a></td>
					</tr>
				</tbody>

				<tr>
					<td width="70%"><input type="text" value="댓글 내용"></td>
					<td width="30%"><input type="button" value="등록"></td>
				</tr>
				<tfoot>
					<tr>
						<td width="20%"><input type="date"></td>
						<td width="40%"><input type="text" value="댓글 내용"></td>
						<td width="20%"><input type="text" value="작성자"></td>
						<td width="20%"><input type="button" value="댓글 달기"></td>
					</tr>
					<tr>
						<td width="20%"><input type="date"></td>
						<td width="40%"><input type="text" value="댓글 내용"></td>
						<td width="20%"><input type="text" value="작성자"></td>
						<td width="20%"><input type="button" value="댓글 달기"></td>
					</tr>
					<tr>
						<td width="20%"><input type="date"></td>
						<td width="40%"><input type="text" value="댓글 내용"></td>
						<td width="20%"><input type="text" value="작성자"></td>
						<td width="20%"><input type="button" value="댓글 달기"></td>
					</tr>
				</tfoot>
			</table>

		</form>
	</div>
</body>
</html>