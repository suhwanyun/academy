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


	<header id="header">
		<c:choose>
			<c:when test="${empty userdata }">
				<div class="inner">
					<div class="content">
						<h1>MCM</h1>
						<h2>
							여러 게시판이 사용가능합니다.<br /> 사용하시겠습니까?
						</h2>
						<a href="#" class="button big alt"><span>가입없이 이용하기</span></a><br />
						<a href="<%=request.getContextPath() %>/loginjsp"
							class="button alt"><span>Login</span></a>
							 <a href="<%=request.getContextPath() %>/joinjsp" class="button alt"><span>회원가입</span></a>
					</div>
					<a href="#" class="button hidden"><span>Home</span></a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="inner">
					<div class="content">
						<h1>MCM</h1>
						<h2>로그인후</h2>
						<a href="#" class="button big alt"><span>이용하기</span></a>
					</div>
					<a href="#" class="button hidden"><span>home</span></a>
				</div>
			</c:otherwise>
		</c:choose>
	</header>
</body>
</html>