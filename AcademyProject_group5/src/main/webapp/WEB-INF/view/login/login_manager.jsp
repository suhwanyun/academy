<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" href="/css/bootstrap-theme.css"/>
<link rel="stylesheet" href="/css/bootstrap.css"/>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 로그인 페이지</title>
</head>
<body>
	<!--  로그인하기전 화면 -->

	<div class="container">

		<div align="center">
			<img class="img-logo" src="/images/logo.png" alt="" />
		</div>


		<div class="text-center">
			<h1>Manager login</h1>
		</div>
		
<form method="post" class="form-horizontal" action="/managerLogin">
			<div class="form-group">

				<label for="managerId" class="control-label col-sm-2">ID:</label>
			<div class="col-sm-10">
					<input type="text" maxlength="10" name="managerId" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="managerPass" class="control-label col-sm-2">Password:</label>
			<div class="col-sm-10">
					<input type="password" maxlength="20" name="managerPass" class="form-control">
			</div>		
			</div>
			<div class="form-group">
				 <div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="로그인 하기" id="loginBtn"
					class="myButton bRight">
			</div>
			</div>
		</form>

	</div>
</body>
</html>