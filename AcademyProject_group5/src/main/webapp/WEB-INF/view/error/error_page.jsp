<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERROR OCCURRED</title>
	<c:choose>
		<c:when test="${!empty msg}">
			<script>
				alert('${msg}');
			</script>
			<c:remove var="msg" />
		</c:when>
		<c:otherwise>
			<script>
				alert("잘못된 접근입니다.");
			</script>
		</c:otherwise>
	</c:choose>
	
	<script src="http://code.jquery.com/jquery.js"></script>
	<c:choose>
		<c:when test="${!empty errorGotoPage}">	
			<script type="text/javascript">
				location.href = "${errorGotoPage}";
			</script>
			<c:remove var="errorGotoPage" />
		</c:when>
		<c:when test="${!empty isManage}">
			<script type="text/javascript">
				$(location).attr('href', "/managerLoginjsp");
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				$(location).attr('href', "/main");
			</script>
		</c:otherwise>
	</c:choose>
</head>
<body>
</body>

</html>