<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
<title>Radius by TEMPLATED</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/font-awesome.css" />
</head>
<body>
	<jsp:include page="WEB-INF/header/header.jsp" />

	<!-- Main -->
	<div id="main">
		<div class="inner">
			<div class="columns">

				<div class="image fit">
					<a href="<%=request.getContextPath() %>/html/detail1.html"><img
						src="<%=request.getContextPath() %>/images/pic01.jpg" alt="" /></a>
				</div>
				<div class="image fit">
					<a href="detail1.html"><img
						src="<%=request.getContextPath() %>/images/pic02.jpg" alt="" /></a>
				</div>
				<div class="image fit">
					<a href="detail1.html"><img
						src="<%=request.getContextPath() %>/images/pic03.jpg" alt="" /></a>
				</div>




				<div class="image fit">
					<a href="detail1.html"><img
						src="<%=request.getContextPath() %>/images/pic04.jpg" alt="" /></a>
				</div>

				<div class="image fit">
					<a href="detail1.html"><img
						src="<%=request.getContextPath() %>/images/pic05.jpg" alt="" /></a>

				</div>
			</div>
		</div>
		<jsp:include page="WEB-INF/footer/footer.jsp" />
</body>
<!-- Scripts -->
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/skel.min.js"></script>
<script src="<%=request.getContextPath() %>/js/util.js"></script>
<script src="<%=request.getContextPath() %>/js/main.js"></script>
</html>