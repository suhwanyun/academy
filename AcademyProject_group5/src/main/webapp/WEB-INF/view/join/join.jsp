<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/css/bootstrap-theme.css" />
<link rel="stylesheet" href="/css/bootstrap.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.7/js/bootstrap.min.js"></script>

<title>회원가입</title>
</head>
<body>


	<div class="container cMargin">

		<div align="center">
			<a href="/main"><img class="img-circle img-responsive"
				src="/images/logo.png" alt="" width="100" /></a>
		</div>
		<div class="text-center">
			<h1>회원가입</h1>

		</div>
		<sform:form method="post" action="join" modelAttribute="userData"
			class="form-horizontal">

			<div class="form-group">
				<sform:label path="userId">아이디</sform:label>
				<span id="trueorfalse" class="glyphicon glyphicon-ok"></span>

				<div class="row">
					<div class="col-xs-8">
						<sform:input placeholder="영문/숫자 4~10자" type="text" maxlength="10"
							path="userId" class="form-control" />
					</div>
					<div class="col-xs-4">
						<sform:button id="duplicationCheckBtn" class="btn active btn-info">중복 확인</sform:button>
					</div>
				</div>

			</div>
			<div class="form-group">
				<sform:label path="userPass">비밀번호</sform:label>


				<sform:input placeholder="영문/숫자/특문 6~20자 " maxlength="20"
					type="password" path="userPass" id="userPass"
					class="form-control size8" />

			</div>
			<div class="form-group">
				<label for="passCheck">비밀번호 확인</label> <input type="password"
					maxlength="20" id="passCheck" class="form-control size8" />


			</div>
			<div class="form-group">
				<sform:label path="userName">이름</sform:label>


				<sform:input placeholder="한글 2~5자 " maxlength="5" type="text"
					path="userName" class="form-control size8" />


			</div>
			<div class="form-group">
				<sform:label path="phoneNum">핸드폰 번호</sform:label>


				<sform:input placeholder="-없이 입력 " maxlength="11" type="text"
					path="phoneNum" class="form-control size8" />


			</div>
			<div class="form-group">
				<sform:label path="passQuestion">비밀번호 질문</sform:label>


				<sform:textarea placeholder="1자 이상 100자 이하 " maxlength="100"
					path="passQuestion" class="form-control size8" />


			</div>
			<div class="form-group">
				<sform:label path="passAnswer">질문 답</sform:label>


				<sform:input placeholder="1자 이상 20자 이하 " maxlength="20" type="text"
					path="passAnswer" class="form-control size8" />

			</div>

			<sform:button id="joinBtn" type="submit" class="btn active btn-info size8mar">가입 하기</sform:button>
			<sform:button type="reset" class="btn active btn-info size4">다시 작성</sform:button>




 

		</sform:form>
	</div>
</body>


<script src="/js/validationcheck.js"></script>
<script src="/js/join.js"></script>

</html>