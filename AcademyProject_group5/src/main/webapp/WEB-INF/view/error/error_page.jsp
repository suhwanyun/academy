<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERROR OCCURRED</title>
<script>
	alert("잘못된 접근입니다.");
</script>
</head>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(location).attr('href', "<%=request.getContextPath()%>/main");
});
</script>
</html>