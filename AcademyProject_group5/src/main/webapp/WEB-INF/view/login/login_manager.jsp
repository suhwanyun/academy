<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.js"></script>
<title>관리자 로그인 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/message.jsp" />
	<!--  로그인하기전 화면 -->

	<div class="container">

		<div align="center">
			<a href="/main"><img class="img-logo"
				src="/images/logo.png" alt="" /></a>
		</div>


		<div class="text-center">
			<h1>Manager login</h1>
		</div>
		
<form class="form-horizontal" action="/managerLogin">
			<div class="form-group">

				<label for="user" class="control-label col-sm-2">ID:</label>
			<div class="col-sm-10">
					<input type="text" maxlength="10" id="userId" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label for="user" class="control-label col-sm-2">Password:</label>
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
		</form>

	</div>
</body>
</html>