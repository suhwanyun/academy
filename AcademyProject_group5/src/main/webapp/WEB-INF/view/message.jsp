<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<title>MESSAGE</title>

	<c:if test="${!empty msg}">
		<script>
			alert('${msg}');
		</script>
		<c:remove var="msg" />
	</c:if>
	
	<script src="http://code.jquery.com/jquery.js"></script>
	<c:choose>
		<c:when test="${!empty gotoPage}">
			<script type="text/javascript">
				$(location).attr('href', "${gotoPage}");
			</script>
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

</html>
