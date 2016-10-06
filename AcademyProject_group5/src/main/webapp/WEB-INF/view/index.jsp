<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
	<c:if test="${!empty msg}">
		<script>alert('${msg}');</script>
		<c:remove var="msg"/>
	</c:if>
	
	<jsp:include page="../header/header.jsp" />

	<!-- Main -->
	<div id="main">
		<div class="inner">
			<div class="columns">
					<a href="<%=request.getContextPath() %>/html/detail1.html"><img 
						class="circular--square"
						src="<%=request.getContextPath() %>/images/pic01.png" alt="" /></a>
				
					<a href="detail1.html"><img class="circular--square"
						src="<%=request.getContextPath() %>/images/pic02.png" alt="" /></a>
				
					<a href="detail1.html"><img class="circular--square"
						src="<%=request.getContextPath() %>/images/pic03.png" alt="" /></a>
			
					<a href="detail1.html"><img class="circular--square"
						src="<%=request.getContextPath() %>/images/pic04.png" alt="" /></a>
				
					<a href="detail1.html"><img class="circular--square"
						src="<%=request.getContextPath() %>/images/pic05.png" alt="" /></a>

				
			</div>
		</div>

</div>		
		<jsp:include page="../footer/footer.jsp" />

</body>
<!-- Scripts -->
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/skel.min.js"></script>
<script src="<%=request.getContextPath() %>/js/util.js"></script>
<script src="<%=request.getContextPath() %>/js/main.js"></script>
</html>