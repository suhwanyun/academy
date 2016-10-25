<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERROR OCCURRED</title>
<script>
	<%
	String exceptionMsg = exception.getMessage();
	
	if(exceptionMsg == null){
	%>
		alert("잘못된 접근입니다.");
	<%
	} else {
	%>
		alert("<%=exceptionMsg%>");
	<%
	}
	%>
</script>
</head>
<body>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(location).attr('href', "/main");
});
</script>
</html>