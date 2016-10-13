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
<title>학업페이지</title>
</head>
<body>

	<jsp:include page="../../header/header.jsp" />
	<div class="upmargin">
		<table>
			<tr>
				<td><input type="button" id="mylectureBtn" value="내 강의목록" /></td>
				<td><input type="button" id="notilistBtn" value="전체 공지목록" /></td>
				<td><input type="button" id="lecturelistBtn" value="강의 선택" /></td>
				<td><input type="button" id="schedule" value="내 시간표" /></td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="incluedjsp" class="form9 pad"></div>
				</td>
			</tr>
		</table>
	</div>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	<c:url value="/campus/notiList" var="notiList"/>
	<c:url value="/lecture/selectedLectureList" var="selectedLectureList"/>
	<c:url value="/campus/lectureListJsp" var="lectureList"/>
	<c:url value="/campus/schedule" var="schedule"/>
	
	$("#mylectureBtn").click(function() {
		$.ajax({
			type : "get",
			url : "${selectedLectureList}",
			success : function(result) {
				$("#incluedjsp").html(result);
			}
		});

	});
	$("#notilistBtn").click(function() {
		$.ajax({
			type : "get",
			url : "${notiList}",
			success : function(result) {
				$("#incluedjsp").html(result);
			}
		});

	});
	$("#lecturelistBtn").click(function() {
		$.ajax({
			type : "get",
			url : "${lectureList}",
			success : function(result) {
				$("#incluedjsp").html(result);
			}
		});

	});
	$("#schedule").click(function() {
		$.ajax({
			type : "get",
			url : "${schedule}",
			success : function(result) {
				$("#incluedjsp").html(result);
			}
		});

	});
</script>
</html>