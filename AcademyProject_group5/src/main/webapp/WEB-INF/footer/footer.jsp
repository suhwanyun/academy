<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
</head>
<body>
	<footer id="footer">
		<a href="#" class="info fa fa-info-circle"><span>about</span></a>
		<div class="inner">
			<div class="content">
				<h3>Welcome to MCM</h3>
				<p>학업, 식사, 오락, 명소에 대한 게시판 확인 및 작성 추천이 가능한 홈페이지입니다.</p>
			</div>
			<div class="copyright">
				<h3>Follow me</h3>
				<ul class="icons">
					<li><a href="#" class="icon fa-twitter"><span
							class="label">Twitter</span></a></li>
					<li><a href="#" class="icon fa-facebook"><span
							class="label">Facebook</span></a></li>
					<li><a href="#" class="icon fa-instagram"><span
							class="label">Instagram</span></a></li>
					<li><a href="#" class="icon fa-dribbble"><span
							class="label">Dribbble</span></a></li>
				</ul>
				&copy; Untitled. Design: <a href="https://templated.co">MCM</a>.
				Images: <a href="https://unsplash.com/">DHOK</a>.
			</div>
		</div>
	</footer>
	<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/skel.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/util.js"></script>
	<script src="<%=request.getContextPath()%>/js/main.js"></script>

</body>

</html>