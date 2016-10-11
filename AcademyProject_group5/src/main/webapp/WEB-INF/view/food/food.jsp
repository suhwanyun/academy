<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
<title>식사게시판</title>
</head>
<body>


	<!-- 테이블 시작 -->
	<div id="wrap">
		<a href="<%=request.getContextPath()%>"> <img
			class="circular--logo"
			src="<%=request.getContextPath()%>/images/logo.png" alt="" /></a>
		<h1 class="member">식사 게시판</h1>
		<div class="form9 pad">
			<table>
				<tr>
					<td><select id="array" title="select array"
						style="height: 2em; width: 100%; margin-bottom: 0.1em;">
							<option selected="selected">기본 정렬</option>
							<option>추천수 정렬</option>
							<option>날짜 정렬</option>
					</select></td>
					<td><select id="select" title="select select"
						style="height: 2em; width: 100%; margin-bottom: 0.1em;">
							<option selected="selected">작성자</option>
							<option>제목</option>
							<option>내용</option>
							<option>제목+내용</option>
					</select></td>
					<td><input type="search" id="search"
						style="height: 1.99em; width: 100%; margin-bottom: 0.1em;"></td>
					<td><input type="button" value="찾기"
						style="height: 1.99em; width: 50%; margin-bottom: 0.1em;"></td>
				</tr>
			</table>
			<table>
				<tr>
					<td rowspan="2">사진</td>
					<td colspan="3">제목</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>추천수</td>
					<td>날짜</td>
				</tr>
			</table>
		</div>
	</div>

	<!-- 테이블 종료 -->

</body>
</html>