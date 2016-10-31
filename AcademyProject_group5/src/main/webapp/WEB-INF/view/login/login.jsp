<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>login form</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/css/bootstrap-theme.css" />
<link rel="stylesheet" href="/css/bootstrap.css" />
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.js"></script>

</head>
<body>
	<jsp:include page="../message.jsp" />
	<!--  로그인하기전 화면 -->

	<div class="container-fluid">

		<div align="center">
			<a href="/main"><img class="img-logo" src="/images/logo.png"
				alt="" /></a>
		</div>


		<div class="text-center">
			<h1>Member login</h1>
		</div>

		<form class="form-horizontal">
			<div class="form-group">

				<label for="userId" class="control-label col-sm-2">ID:</label>
				<div class="col-sm-10">
					<input type="text" maxlength="10" id="userId" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="userPass" class="control-label col-sm-2">Password:</label>
				<div class="col-sm-10">
					<input type="password" maxlength="20" id="userPass"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" value="로그인 하기" id="loginBtn"
						class="btn btn-default btn-lg">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" value="회원가입" id="joinBtn"
						class="btn btn-default btn-lg"> <input type="button"
						value="ID/Pass 찾기" id="searchBtn" class="btn btn-default btn-lg">
				</div>
			</div>
		</form>

	</div>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/login.js"></script>
</html>