<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<html>
<head>
<title>ID, Pass 찾기</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/css/bootstrap-theme.css" />
<link rel="stylesheet" href="/css/bootstrap.css" />
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.js"></script>

</head>

<!--  로그인하기전 화면 -->

<div id="container">
	<div align="center">
		<a href="/main"><img class="img-logo" src="/images/logo.png"
			alt="" /></a>
	</div>

	<div class="text-center">
		<h1>ID/Pass 찾기</h1>
	</div>

	<h1 class="member">ID 찾기</h1>
	<div class="form-group">
		<label for="userName">이름</label>
		<div class="col-sm-10">
			<input placeholder="한글 2~5자 " type="text" id="userName" class="form-control">
		</div>
	</div>
	<div class="form-group">
		<label for="email">이메일</label>
		<div class="col-sm-10">
			<input type="text" id="email" class="form-control">
		</div>
	</div>
	<input type="button" value="확인" id="idCheck" class="myButton" >



	<h1 class="member">Password찾기</h1>
	<div class="form-group">
		<label for="userId">아이디</label>
		<div class="col-sm-10">
			<input placeholder="영문/숫자 4~10자" type="text" id="userId" class="form-control"><br>
		</div>
	</div>
	<div class="form-group">
		<label for="passQuestion">비밀번호 질문</label>
		<div class="col-sm-10">
			<output id="passQuestion" class="form-control"></output>
		</div>
	</div>
	<div class="form-group">
		<label for="passAnswer">질문 답</label>
		<div class="col-sm-10">
			<input placeholder="1자 이상 20 이하 " type="text" id="passAnswer" class="form-control">
		</div>
	</div>
	<input id="res" readonly="readonly" type="text" value="임시비밀번호 :"
		id="passAnswer"> <input type="button" value="확인"
		id="passCheck" class="myButton">


</div>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/search_idpw.js"></script>
<script src="/js/validationcheck.js"></script>
</html>