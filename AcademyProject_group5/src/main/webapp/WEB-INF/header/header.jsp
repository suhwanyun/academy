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
	href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
</head>
<body>
	<header id="header" class="hide">
		<c:choose>
			<c:when test="${!empty user }">
				<div class="inner">
					<div class="content">
						<h1>MCM</h1>
						<h2>${user.userName}님어서오세요</h2>
						<a href="#" class="button big alt"><span>알림</span></a> 
						<a href="<%=request.getContextPath()%>/info/myinfo" class="button big alt"><span>내 정보관리</span></a>
						<a href="#" class="button big alt"><span>${user.userMileage} m</span></a>
						<a href="<%=request.getContextPath()%>/logout" class="button big alt"><span>로그아웃</span></a>
						<a href="#" class="button big alt"><span>이용하기</span></a>
					</div>
					<a href="#" class="button hidden"><span>home</span></a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="inner">
					<div class="content">
						<h1>MCM</h1>
						<a href="#" class="button big alt"><span>비회원으로 사용</span></a> <a
							href="<%=request.getContextPath()%>/loginjsp"
							class="button big alt"><span>로그인</span></a> <a
							href="<%=request.getContextPath()%>/joinjsp"
							class="button big alt"><span>회원가입</span></a>
					</div>
					<a href="#" class="button hidden"><span>Home</span></a>
				</div>
			</c:otherwise>
		</c:choose>
	</header>
</body>
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/skel.min.js"></script>
<script src="<%=request.getContextPath() %>/js/util.js"></script>
<script src="<%=request.getContextPath() %>/js/main.js"></script>
<script type="text/javascript">
	
	$("h1").click(function(){
		$(location).attr('href', "<%=request.getContextPath() %>/main");
	})
</script>
</html>