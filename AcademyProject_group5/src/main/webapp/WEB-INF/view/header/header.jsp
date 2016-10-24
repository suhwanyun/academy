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


</head>
<body>
	<jsp:include page="../message.jsp" />
	
	<header id="header" class="hide">
		<c:choose>
			<c:when test="${!empty user }">
				<div class="inner">
					<div class="content">
						<h1>MCM</h1>
						<h2>${user.userName}님어서오세요</h2>
						<a href="/noti/notiSettingList" class="button"><span>알림</span></a> 
						<a href="/info/myinfo" class="button"><span>내 정보관리</span></a>
						<a href="#" class="button"><span>${user.userMileage} m</span></a>
						<a href="/logout" class="button"><span>로그아웃</span></a>
						<a href="#" class="button"><span>이용하기</span></a>
					</div>
					<a href="#" class="button hidden"><span>home</span></a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="inner">
					<div class="content">
						<h1>MCM</h1>
						<a href="#" class="button big alt"><span>비회원으로 사용</span></a> <a
							href="/loginjsp"
							class="button big alt"><span>로그인</span></a> <a
							href="/joinjsp"
							class="button big alt"><span>회원가입</span></a>
					</div>
					<a href="#" class="button hidden"><span>Home</span></a>
				</div>
			</c:otherwise>
		</c:choose>
	</header>
</body>
<script src="/js/jquery.min.js"></script>
<script src="/js/skel.min.js"></script>
<script src="/js/util.js"></script>
<script src="/js/main.js"></script>
<script type="text/javascript">
/* 	$("document").ready(function(){
		alert("준비완료")
	}); */
	$("h1").click(function(){
		$(location).attr('href', "/main");
	})
</script>
</html>