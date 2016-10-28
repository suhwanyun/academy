<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<c:if test="${!empty msg}">
		<script>
			alert('${msg}');
		</script>
		<c:remove var="msg" />
	</c:if>
	<c:if test="${!empty nextJsp}">
		<script>
			location.href = "${nextJsp}"; 
		</script>
		<c:remove var="nextJsp" />
	</c:if>

</html>
