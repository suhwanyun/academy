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
	<sform:form method="post" action="join" modelAttribute="userData">
		<fieldset>
			<sform:label path="id">아이디</sform:label>
			<sform:input path="id"/>
		</fieldset>
	</sform:form>
</body>
</html>