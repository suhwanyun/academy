<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lecture_Main</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container">
		<div id="incluedjsp">
			<jsp:include page="/WEB-INF/view/campus/lecture/lecture_noti_list.jsp" />
			<hr>
			<jsp:include page="/WEB-INF/view/campus/lecture/lecture_board.jsp" />
		</div>
	</div>
</body>
</html>