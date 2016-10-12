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
	
	<table class="list_table">
		<caption>전체 강의 목록입니다. 원하는 강의를 선택하세요.</caption>
		<colgroup>
			<col width="40%" />
			<col width="30%" />
			<col width="30%" />
		</colgroup>
		<thead>
			<tr id="gomchi2">
				<th>강의 이름</th>
				<th>교수 이름</th>
				<th>강의 분반</th>
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
<!-- 테이블 종료 -->
	
</body>
</html>