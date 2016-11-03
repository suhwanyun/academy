<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>페이지 이동중 입니다.</title>
</head>
<body>

</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	<%
	 String browser = request.getHeader("User-Agent");
	 if (browser.indexOf("Android") > 0) {                                        // 안드로이드로 접속했다면 결과값 true
	 	session.setAttribute("isPhone", "true");
	 }else{
		 session.setAttribute("isPhone", "false");
	 }
	
	%>
	$(location).attr('href', "/main");
});
</script>
</html>