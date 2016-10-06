<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<sform:form method="post" action="join" modelAttribute="UserData">
		<fieldset>
			<sform:label path="userId">아이디</sform:label>
			<sform:input path="userId"/>
			<sform:button>확인</sform:button>
		</fieldset>
	</sform:form>
</body>
</html>