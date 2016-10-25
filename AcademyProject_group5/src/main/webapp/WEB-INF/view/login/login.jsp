<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>login form</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
   <link rel="stylesheet" href="/css/bootstrap-theme.css"/>
   <link rel="stylesheet" href="/css/bootstrap.css"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<jsp:include page="../message.jsp" />
	<!--  로그인하기전 화면 -->

	<div class="container">

		<div align="center">
			<a href="/main"><img class="img-circle img-responsive"
				src="/images/logo.png" alt="" width="100" /></a>
		</div>


		<div class="text-center">
			<h1>member login</h1>
		</div>
		<form class="form-horizontal">

			<div class="form-group">

				<label for="user" class="control-label col-sm-2">아이디</label>
				
					<input type="text" maxlength="10" id="userId" class="form-control col-sm-10">
				
			</div>
			<div class="form-group">
				<label for="user" class="control-label col-sm-2">비밀번호</label>
				
					<input type="password" maxlength="20" id="userPass"
						class="form-control col-sm-10">
				
			</div>
			<div class="container">
				<input type="button" value="로그인 하기" id="loginBtn"
					class="btn btn-default btn-lg">
			</div>
			<div class="container" style="margin-top: 1em;">
				<input type="button" value="회원가입" id="joinBtn"
					class="btn btn-default btn-lg"> <input type="button"
					value="ID/Pass 찾기" id="searchBtn" class="btn btn-default btn-lg">
			</div>

		</form>

	</div>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/login.js"></script>
</html>