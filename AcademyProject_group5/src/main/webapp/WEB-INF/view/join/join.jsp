<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sform"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<sform:form>
	<table>
		<tr>
			<td>ID:</td>
			<td><sform:input path="/userId"/></td>
			<td><sform:button id="duplicationBtn" value="check"></sform:button></td>
		</tr>
	</table>
	</sform:form>
</body>
</html>