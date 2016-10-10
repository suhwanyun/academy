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
<title>오락게시판</title>
</head>
<body>
	
	 
<!-- 테이블 시작 -->
<div id="wrap">
<a href="<%=request.getContextPath() %>">
	<img class="circular--logo"
	src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
	<h1 class="member">오락 게시판</h1>
<div class="form9 pad">
<div class="boardcss_list_table">
	<table class="list_table">
			<colgroup>
			<col width="30%" />
			<col width="40%" />
			<col width="30%" />
		</colgroup>
		
		<thead>
		<tr>
				<td>
				<select id="array" title="select array">
				<option selected="selected">정렬 기준</option>
				<option>red</option>
        		<option>blue</option>
				<option>yellow</option>
       			<option>black</option>
    			</select></td>
				<td>
				<select id="select" title="select select">
				<option selected="selected">선택</option>
				<option>red</option>
        		<option>blue</option>
				<option>yellow</option>
       			<option>black</option>
    			</select></td>
    		
				<td><input type="search" id="searchBtn"></td>
				
			
		</tr>
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
				class="button big"><span>사진</span></a></th>
				<th>제목</th>
				<th>추천수</th>
			</tr>
				<tr>
					<th><a href="<%=request.getContextPath() %>/lectureInfo" 
				class="button big"><span>사진</span></a></th>
				<th>제목</th>
				<th>추천수</th>
			</tr>
				<tr>
					<th><a href="<%=request.getContextPath() %>/lectureInfo" 
				class="button big"><span>사진</span></a></th>
				<th>제목</th>
				<th>추천수</th>
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
			<th><a href="<%=request.getContextPath() %>/write/play"  class="button big alt"><span>글쓰기 이동</span></a></th>
			<th></th>
			</tr>
		</tbody>
		
		
	</table>
	
</div>
</div>
</div>

<!-- 테이블 종료 -->
	
</body>
</html>