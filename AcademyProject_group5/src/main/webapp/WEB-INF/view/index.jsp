<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>My Campus Manager</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />

</head>

<body>
	<c:if test="${!empty msg}">
		<script>
			alert('${msg}');
		</script>
		<c:remove var="msg" />
	</c:if>
	<jsp:include page="../header/header.jsp" />

	<!-- Main -->
	<div id="main">
		<div class="inner">

			<div class="columns">
				<a href="<%=request.getContextPath()%>/campus/campusMain"><img
					class="circular--square"
					src="<%=request.getContextPath()%>/images/pic01.png" alt="" /></a> <a
					href="<%=request.getContextPath()%>/foodMain"><img
					class="circular--square"
					src="<%=request.getContextPath()%>/images/pic02.png" alt="" /></a>

			</div>
			<div class="columns">
				<a href="<%=request.getContextPath()%>/mileageMain"><img
					class="circular--square"
					src="<%=request.getContextPath()%>/images/pic05.png" alt=""
					style="margin-left: 30%;" /></a>
			</div>

			<div class="columns">
				<a href="<%=request.getContextPath()%>/playMain"><img
					class="circular--square"
					src="<%=request.getContextPath()%>/images/pic03.png" alt="" /></a> <a
					href="<%=request.getContextPath()%>/placeMain"><img
					class="circular--square"
					src="<%=request.getContextPath()%>/images/pic04.png" alt="" /></a>
			</div>

		</div>

	</div>
	<jsp:include page="../footer/footer.jsp" />

</body>

</html>