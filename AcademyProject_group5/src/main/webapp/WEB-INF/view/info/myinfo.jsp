<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/css/bootstrap-theme.css" />
<link rel="stylesheet" href="/css/bootstrap.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.7/js/bootstrap.min.js"></script>

<title>회원정보 수정</title>
</head>
<body>

	<jsp:include page="/WEB-INF/view/header/header.jsp" />
		<div class="container cMargin">
		<h1 style="text-align: center;">내 정보 수정</h1>
		<sform:form method="post" action="update" modelAttribute="userData"
			class="form-horizontal">
			<div class="form-group">
				<sform:label path="userId">아이디</sform:label>
				<div class="row">
					<div class="col-xs-12">
						<sform:input readonly="true" type="text" path="userId"
							value="${user.userId}" class="form-control" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<sform:label path="userPass">비밀번호 변경</sform:label>
				<div class="row">
					<div class="col-xs-12">
						<sform:input placeholder="변경할 경우에만 입력하시오" maxlength="20"
							type="password" path="userPass" class="form-control"  />
						</div>
				</div>
			</div>
			<div class="form-group">
				<label for="passCheck">비밀번호 변경 확인</label>
				<div class="row">
					<div class="col-xs-12">
						<input type="password" maxlength="20" id="passCheck"  class="form-control" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<sform:label path="userName">이름</sform:label>
				<div class="row">
					<div class="col-xs-12">
						<sform:input readonly="true" type="text" path="userName"
							value="${user.userName}"  class="form-control" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<sform:label path="email">이메일</sform:label>
				<div class="row">
					<div class="col-xs-12">
						<sform:input readonly="true" type="text" path="email"
							maxlength="11" value="${user.email}"  class="form-control" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<sform:label path="passQuestion">비밀번호 질문</sform:label>
				<div class="row">
					<div class="col-xs-12">
						<sform:textarea path="passQuestion" maxlength="100"
							value="${user.passQuestion}"  class="form-control" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<sform:label path="passAnswer">질문 답</sform:label>
				<div class="row">
					<div class="col-xs-12">
						<sform:input type="text" path="passAnswer" maxlength="20"
							value="${user.passAnswer}" class="form-control" />
					</div>
				</div>
			</div>
			<div align="center" style="margin-bottom:5%;">
			<sform:button id="infoUpdate" type="submit" class="myButton size8">수정 하기</sform:button>
			</div>
		</sform:form>
</div>
</body>
<script src="/js/validationcheck.js"></script>
<script src="/js/myinfo.js"></script>

</html>