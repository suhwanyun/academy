<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<jsp:include page="../../header/header.jsp" />
	<div id="wrap">
		<div class="upmargin">
			<h1 class="member">식사 게시판</h1>
			<div class="form9 pad">
				<table>
					<colgroup>
						<col width="30%">
						<col width="20%">
						<col width="25%">
						<col width="25%">
					</colgroup>
					<tr>
						<td><select id="array" title="select array">
								<option selected="selected">정렬</option>
								<option>추천수 정렬</option>
								<option>날짜 정렬</option>
						</select></td>
						<td></td>
						<td></td>
						<td><a href="<%=request.getContextPath()%>/write/foodjsp">글쓰기</a></td>
					</tr>
					<tr>
						<td><select id="select" title="select select">
								<option selected="selected">작성자</option>
								<option>제목</option>
								<option>내용</option>
								<option>제목+내용</option>
						</select></td>
						<td colspan="2"><input type="search" id="search"></td>
						<td><input type="button" class="boardBtn" value="찾기"></td>
					</tr>

				</table>
				<table style="text-align: center">
					<colgroup>
						<col width="20%">
						<col width="80%">
						
					</colgroup>
					<c:forEach items="${postingDataList }" var="list">
						<tr>
							<td rowspan="2"><img class="imgboard" style="position:relative;  width:20%;"
								src="<%=request.getContextPath()%>/upload/${list.postingPhoto}"  /></td>
							<td colspan="3">${list.postingTitle }</td>
						</tr>
						<tr>
							<td>${list.userId }</td>
							<td>${list.postingRecommand }</td>
							<td>${list.postingTime }</td>
						</tr>
						</c:forEach>
						<tr>
							<td colspan="4" align="center"><button>더 보기</button></td>
						</tr>
					
				</table>
			</div>
		</div>
	</div>
	<!-- 테이블 종료 -->
</body>

</html>