<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/font-awesome.css" />
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath() %>/foodMain">
	<img class="circular--logo"
	src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
	 
<!-- 테이블 시작 -->
<div class="form5">
<div class="boardcss_list_table">
	<table class="list_table">
		<caption>식사게시판입니다.</caption>
		
		<thead>
			<tr>
				<td><select >정렬 기준</select></td>
				<td><select  >선택</select></td>
				<td><input type="text" id="searchText"></td>
				<td><button id="searchBtn"></button></td>
			</tr>
			<colgroup>
			<col width="30%" />
			<col width="50%" />
			<col width="20%" />
		</colgroup>
			<tr id="gomchi2">
				<th>사진</th>
				<th>제목</th>
				<th>추천수</th>
			</tr>
		</thead>
		<!--  데이터 넣기 -->
		<tbody>
				<tr>
				<th><a href="<%=request.getContextPath() %>/lectureInfo" 
				class="button big"><span>강의 이름</span></a></th>
				<th>교수 이름</th>
				<th>강의 분반</th>
			</tr>
				<tr>
				<th><a href="<%=request.getContextPath() %>/lectureInfo" 
				class="button big"><span>강의 이름</span></a></th>
				<th>교수 이름</th>
				<th>강의 분반</th>
			</tr>
				<tr>
				<th><a href="<%=request.getContextPath() %>/lectureInfo" 
				class="button big"><span>강의 이름</span></a></th>
				<th>교수 이름</th>
				<th>강의 분반</th>
			</tr>
				<tr>
				<th><a href="<%=request.getContextPath() %>/lectureInfo" 
				class="button big"><span>강의 이름</span></a></th>
				<th>교수 이름</th>
				<th>강의 분반</th>
			</tr>
			<tr>
			<th></th>
			<th><a href="#"  class="button big alt"><span>더보기</span></a></th>
			<th></th>
			</tr>
		</tbody>
		
		
	</table>
</div>
</div>
<!-- 테이블 종료 -->
	
</body>
</html>