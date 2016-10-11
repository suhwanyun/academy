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
	
		<a href="<%=request.getContextPath()%>"> <img
			class="circular--logo"
			src="<%=request.getContextPath()%>/images/logo.png" alt="" /></a>
		<h1 class="member">식사 게시판</h1>
					<div style="width: 100%; column-count: 4; height:2.1em; margin-left:1em;">
						<select id="array" title="select array" style="height: 2em; width:100%; margin-bottom:0.1em;">
								<option selected="selected">기본 정렬</option>
								<option>추천수 정렬</option>
								<option>날짜 정렬</option>
						</select>
						<select id="select" title="select select" style="height: 2em; width:100%; margin-bottom:0.2em;">
								<option selected="selected">작성자</option>
								<option>제목</option>
								<option>내용</option>
								<option>제목+내용</option>
						</select>

						<input type="search" id="search" ><span> </span><input
							 type="button" value="찾기" >
</div>	
	<div class="form9 pad">
		<table>
			<thead>
					<tr>
						<th>사진</th>
						<th>제목</th>
						<th>작성자</th>
						<th>추천수</th>
						<th>날짜</th>
					</tr>
				</thead>
				<!--  데이터 넣기 -->
				<tbody>
					<tr>
						<td><a href="<%=request.getContextPath()%>/food_info"
							class="button big"><span>사진</span></a></td>
						<td>제목</td>
						<td>작성자</td>
						<td>추천수</td>
						<td>날짜</td>
					</tr>
					<tr>
						<td><a href="<%=request.getContextPath()%>/food_info"
							class="button big"><span>사진</span></a></td>
						<td>제목</td>
						<td>작성자</td>
						<td>추천수</td>
						<td>날짜</td>
					</tr>
					<tr>
						<td><a href="<%=request.getContextPath()%>/food_info"
							class="button big"><span>사진</span></a></td>
						<td>제목</td>
						<td>작성자</td>
						<td>추천수</td>
						<td>날짜</td>
					</tr>
				</tbody>


			</table>

		</div>
	

	<!-- 테이블 종료 -->

</body>
</html>