<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>관리자 전용 페이지</title>
<link rel="stylesheet" href="/css/bootstrap-theme.css"/>
<link rel="stylesheet" href="/css/bootstrap.css"/>
<link rel="stylesheet" href="/css/datepicker3.css"/>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/bootstrap-datepicker.kr.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<div class="container-fix  text-center" style="width:100%">
	<table class="table">
		<colgroup>
			<col width="33%">
			<col width="33%">
			<col width="33%">
		</colgroup>
		<tr>
			<td class="text-center">
			<a href="/managerLoginjsp"><img class="img-logo" src="/images/logo.png" alt="" />
			</a></td>
			<td><h1 class="bigFont">관리자 전용 페이지</h1></td>
			
			<td><button id="logoutBtn" class="myButtonBig" >로그아웃</button></td>
		</tr>
		
	</table>	
	</div>
</body>
<script type="text/javascript">
$("#logoutBtn").click(function(){
	$(location).attr('href', "/managerLogout");
});
</script>
</html>