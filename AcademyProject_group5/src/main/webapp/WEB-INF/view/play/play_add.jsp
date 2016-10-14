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
<title>오락 추가 게시판</title>

</head>
<body>
<jsp:include page="../../header/header.jsp" />
	<div id="wrap">
		<div class="upmargin">
	<h1 class="member">오락 글쓰기</h1>
	<sform:form method="post" action="#" modelAttribute="postingData"
			enctype="multipart/form-data">
			<table class="add">
				<colgroup>
					<col width="40%">
					<col width="60%">
				</colgroup>

				<tr>

					<td><label>제 목</label></td>
					<td><sform:input type="text" maxlength="30"
							path="postingTitle" /></td>

				</tr>

				<tr>

					<td><label>내 용</label></td>
					<td><sform:textarea rows="13" cols="40" maxlength="300"
							path="postingContent" /></td>

				</tr>
				<tr>

					<td><label>파일 첨부</label></td>
					<td><sform:input type="file" path="postingPhoto" input
							accept="image/*" /></td>

				</tr>
				<tr>
					<td colspan=2 align="center"><sform:button id="save"
							value="글쓰기" /> <sform:button type="reset" value="다시작성" /></td>


				</tr>
			</table>

		</sform:form>
	</div>
</div>
</body>
</html>