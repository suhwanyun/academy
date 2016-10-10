<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Map"%>
<html>
<head>
<title>login form</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />

</head>

<form>
	<!--  로그인하기전 화면 -->

	<div id="wrap">
		<a href="<%=request.getContextPath()%>"><img
			class="circular--logo"
			src="<%=request.getContextPath()%>/images/logo.png" alt="" /></a>
		
		<div class="form7">
			<div class="form6">
				<div class="form3 pad">
					
					<h1 class="member">ID 찾기</h1>
					<label for="user">이름</label><input type="text" id="userName">
					<label for="user">전화번호</label><input type="text" id="phoneNum"><br>
					<input type="button" value="확인" id="idCheck" style="margin-left: 45%"><br>
</div>
</div>
		</div>
		<div class="form8">
			<div class="form6">
				<div class="form3 pad">
					<h1 class="member">Password찾기</h1>
					<label for="user">아이디</label><input type="text" id="user">
					<label for="user">이름</label><input type="text" id="name"> <label
						for="user">이름</label><input type="text" id="name"> <label
						for="user">이름</label><input type="text" id="name"> <br>
						<input type="button" value="확인" id="idCheck"  style="margin-left: 45%">
				</div>
</div>
		</div>
			
	</div>
</form>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	$("#idCheck").click(function() {
		alert("성공");
	
	});
</script>
</html>