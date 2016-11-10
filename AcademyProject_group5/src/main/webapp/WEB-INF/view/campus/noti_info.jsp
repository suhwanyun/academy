<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 내용보기</title>

</head>
<body>
<jsp:include page="/WEB-INF/view/header/header.jsp" />

	<div class="container text-center tMargin">
	<table class="table">
		<colgroup>
			<col width="80%">
			<col width="20%">
		</colgroup>
		<tr>
			<th>${lectureNotice.noticeTitle }</th>
			<td>${lectureNotice.noticeTime }</td>
		</tr>
		<tr>
			<td colspan="2">${lectureNotice.noticeContent }</td>
		</tr>
		<tr align="right">
			<td colspan="2"><button id="moveToList" class="myButton">목록으로</button></td>
		</tr>
	</table>
	
	</div>
</body>
<script type="text/javascript">
$("#moveToList").click(function(){
	$(location).attr("href", "/campus/campusMain");
});
</script>
</html>