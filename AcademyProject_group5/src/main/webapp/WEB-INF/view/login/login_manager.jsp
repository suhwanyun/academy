<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>관리자 로그인 페이지</title>
<link rel="stylesheet" href="/css/bootstrap-theme.css" />
<link rel="stylesheet" href="/css/bootstrap.css" />
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<div class="container" >

		<div align="center">
			<a href="/managerLoginjsp"><img class="img-logoS" src="/images/logo.png"
				alt="" /></a>
		</div>


		<div class="text-center">
			<h1>Manager Login</h1>
		</div>

		<form method="post" class="form-horizontal" action="/managerLogin">
			
			<div style="margin-left:12%;">
				<label for="managerId" class="control-label">ID:</label>
				
					<input type="text" maxlength="10" id="managerId" name="managerId" class="form-fix">
			</div>
			
			<div style="margin-left:12%;">
				<label for="ManagerPass" class="control-label ">Password:</label>
				
					<input type="password" maxlength="20" name="ManagerPass" id="ManagerPass"
						class="form-fix">
			</div>
			<div style="margin-left:82%; margin-top:5%;"> 
					<input type="submit" value="로그인 하기" id="loginBtn"
						class="myButton">
			</div>
		</form>

	</div>
</body>
</html>