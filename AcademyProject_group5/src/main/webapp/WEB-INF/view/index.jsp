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
	href="/css/main.css" />
<link rel="stylesheet"
	href="/css/font-awesome.css" />
</head>

<body>
	<c:if test="${!empty msg}">
		<script>
			alert('${msg}');
		</script>
		<c:remove var="msg" />
	</c:if>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />

	<!-- Main -->
	<div id="main">
		<div class="inner">
			
			<div>
			
			<a href="/campus/campusMain"><img
					class="circular--square"
					src="/images/pic01.png" alt=""  style="display: "/></a>
					<a href="/foodMain"><img
					class="circular--square" 
					src="/images/pic02.png" alt="" /></a>
			
			</div>
			
			<div align="center">
			<a href="/mileageMain"><img
					class="circular--square" 
					src="/images/pic05.png" alt=""
					 /></a>
			</div>

		
			
			<div>
			<a href="/playMain"><img
					class="circular--square"
					src="/images/pic03.png" alt="" /></a> 
			<a href="/placeMain"><img
					class="circular--square" 
					src="/images/pic04.png" alt="" /></a>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/footer/footer.jsp" />

</body>

</html>