<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<title>Radius by TEMPLATED</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/font-awesome.css" />
	</head>
	<body>

		Header
	 		<header id="header">
				<div class="inner">
					<div class="content">
						<h1>MCM</h1>
						<h2>여러 게시판이 사용가능합니다.<br />
						사용하시겠습니까?</h2>
						<a href="#" class="button big alt"><span>가입없이 이용하기</span></a><br><br>
						<a href="#" class="button big alt"><span>Login</span></a>
						<a href="#" class="button big alt"><span>회원가입</span></a>
					</div>
					<a href="#" class="button hidden"><span>Let's Go</span></a>
				</div>
			</header>
			
			
			<!-- <header id="header2">
				<div class="inner">
					<div class="content">
						<h1>MCM</h1>
						<h2>000회원님 환영합니다.</h2>
						<h3>마일리지 : </h3>
						<h3>내 정보 확인 : </h3>
						<h3>로그아웃 : </h3>
						<a href="#" class="button big alt"><span>접어두기</span></a>
					</div>
					<a href="#" class="button hidden"><span>Let's Go</span></a>
				</div>
			</header> -->
  
		 <!-- Main -->
	 		<div id="main">
				<div class="inner">
					<div class="columns">

						
							<div class="image fit">
								<a href="<%=request.getContextPath() %>/html/detail1.html"><img src="<%=request.getContextPath() %>/images/pic01.jpg" alt="" /></a>
							</div>
							<div class="image fit">
								<a href="detail1.html"><img src="<%=request.getContextPath() %>/images/pic02.jpg" alt="" /></a>
							</div>
							<div class="image fit">
								<a href="detail1.html"><img src="<%=request.getContextPath() %>/images/pic03.jpg" alt="" /></a>
							</div>
						
							

						
							<div class="image fit">
								<a href="detail1.html"><img src="<%=request.getContextPath() %>/images/pic04.jpg" alt="" /></a>
							</div>
						
							<div class="image fit">
								<a href="detail1.html"><img src="<%=request.getContextPath() %>/images/pic05.jpg" alt="" /></a>
							</div>
							<!--
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic08.jpg" alt="" /></a>
							</div>
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic07.jpg" alt="" /></a>
							</div>

						Column 3 (horizontal, vertical, horizontal, vertical)
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic09.jpg" alt="" /></a>
							</div>
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic12.jpg" alt="" /></a>
							</div>
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic11.jpg" alt="" /></a>
							</div>
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic10.jpg" alt="" /></a>
							</div>

						Column 4 (vertical, horizontal, vertical, horizontal)
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic13.jpg" alt="" /></a>
							</div>
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic14.jpg" alt="" /></a>
							</div>
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic15.jpg" alt="" /></a>
							</div>
							<div class="image fit">
								<a href="detail1.html"><img src="images/pic16.jpg" alt="" /></a>
							</div>
-->
					</div>
				</div>
			</div>
 
		
			 <footer id="footer">
				<a href="#" class="info fa fa-info-circle"><span>About</span></a>
				<div class="inner">
					<div class="content">
						<h3>Welcome to MCM</h3>
						<p>학업, 식사, 오락, 명소에 대한 게시판 확인 및 작성 추천이 가능한 홈페이지입니다.</p>
					</div>
					<div class="copyright">
						<h3>Follow me</h3>
						<ul class="icons">
							<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
							<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						</ul>
						&copy; Untitled. Design: <a href="https://templated.co">MCM</a>. Images: <a href="https://unsplash.com/">DHOK</a>.
					</div>
				</div>
			</footer>
       
		<!-- Scripts -->
			<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
			<script src="<%=request.getContextPath() %>/js/skel.min.js"></script>
			<script src="<%=request.getContextPath() %>/js/util.js"></script>
			<script src="<%=request.getContextPath() %>/js/main.js"></script>

	</body>
</html>