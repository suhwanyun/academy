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
<title>명소게시판</title>
</head>
<body>
<!-- 테이블 시작 -->
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div id="wrap">
		<div class="upmargin">
			<h1 class="member">명소 게시판</h1>
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
						<td><a href="<%=request.getContextPath()%>/write/placejsp">글쓰기</a></td>
					</tr>
					<tr>
						<td><select id="serchType">
								<option selected="selected" value="user">작성자</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="all">제목+내용</option>
						</select></td>
						<td colspan="2"><input type="search" id="search"></td>
						<td><input type="button" id="searchBtn" class="boardBtn" value="찾기"></td>
					</tr>

				</table>
			
				<table style="text-align: center;" class="imgTable">
					<colgroup>
						<col width="20%">
						<col width="20%">
						<col width="10%">
						<col width="50%">
					</colgroup>
					
					<c:forEach items="${postingDataList }" var="list">
						<tr>

							<td rowspan="2"><img class="imgBoard" src="<%=request.getContextPath()%>/upload/${list.postingPhoto}"  /></td>
							<td colspan="3">${list.postingTitle }</td>

						</tr>
						<tr> 
							<td>${list.userId }</td>
							<td>${list.postingRecommand }</td>
							<td>${list.postingTime }</td>
						</tr>
						</c:forEach>
						<tr id="beforeLocation">
							<td colspan="3"><button  id="moreBtn" class="myButton">더보기</button></td>
							<td><button class="myButton">맨 위로</button></td></tr>
					
				</table>
				
			</div>
		</div>
	</div>
</body>
</html>