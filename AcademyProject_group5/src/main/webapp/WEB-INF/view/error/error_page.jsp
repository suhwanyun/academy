<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERROR OCCURRED</title>
<script>
	<c:choose>
		<c:when test="${!empty exceptionMsg}">
			alert("${exceptionMsg }");
		</c:when>
		
		<c:otherwise>
			alert("잘못된 접근입니다.");
		</c:otherwise>
	</c:choose>
</script>
</head>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(location).attr('href', "/main");
});
</script>
</html>