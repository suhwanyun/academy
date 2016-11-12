<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 목록</title>
</head>
<body>
	<div class="container">
		<h1>공지 목록</h1>
		<table class="list_table">
			<c:forEach items="${noticeList }" var="list">
				<tr onclick="movePage2(${list.lectureNoticeId })">
					<td>
					<c:choose>
						<c:when test="${list.noticeType == 'notice'}">
							일반 공지
						</c:when>
						<c:when test="${list.noticeType == 'test'}">
							시험 공지
						</c:when>
						<c:when test="${list.noticeType == 'report'}">
							과제 공지
						</c:when>
					</c:choose>
					</td>
					<td>${list.noticeTitle }</td>
					<td>${list.noticeTime }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<script type="text/javascript">
function movePage2(el){
	 $(location).attr("href", "/campus/notiInfo?lectureNoticeId="+el);
}
</script>
</html>