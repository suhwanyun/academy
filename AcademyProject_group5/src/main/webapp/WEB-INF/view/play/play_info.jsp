<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<div id="wrap">
<a href="<%=request.getContextPath() %>">
	<img class="circular--logo"
	src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
	<h1 class="member">오락 글쓰기</h1>
	<form name="writeForm" method="post" action="writePro.do">
	<table>
		<c:set var="num" value="${requestScope.num }"/>
		<c:set var="ref" value="${requestScope.ref }"/>
		<c:set var="re_step" value="${requestScope.re_step }"/>
		<c:set var="re_level" value="${requestScope.re_level }"/>
		
		
	<%-- <input type="hidden" name="num" value="${num }"/>
	<input type="hidden" name="ref" value="${ref }"/>
	<input type="hidden" name="re_step" value="${re_step }"/>
	<input type="hidden"	name="re_level" value="${re_level }"/>
	 --%>
	<tr>
		<td width="70" align="center">제 목</td>
		<td align="left" width="330">
			<input type="text" size="10" maxlength="10" name="writer"></td>
	</tr>
	<tr>
		<td><img class="circular--square"
						src="<%=request.getContextPath() %>/images/pic02.png" alt="" /></td>
	</tr>
	<tr>
		<td width="70" align="center">내 용</td>
		<td align="left" width="330">
			<textarea name="content" rows="13" cols="40"></textarea></td>
	</tr>

	<tr>
	<td>
		<a href="#" class="button big alt"><span>추천</span></a> 
		</td>
	</tr>
	<tr>
		<td width="70%"><input type="text" value="댓글 내용"></td>
		<td width="30%"><input type="button" value="등록"></td>
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
		<tr>
		<td width="20%"><input type="date"></td>
		<td width="40%"><input type="text" value="댓글 내용"></td>
		<td width="20%"><input type="text" value="작성자"></td>
		<td width="20%"><input type="button" value="댓글 달기"></td>
	</tr>
	</table>
	
	</form>
</div>
</body>
</html>