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
	<!--  �α����ϱ��� ȭ�� -->

	<div id="wrap">
		<a href="<%=request.getContextPath()%>"><img
			class="circular--logo"
			src="<%=request.getContextPath()%>/images/logo.png" alt="" /></a>
		
		<div class="form7">
			<div class="form6">
				<div class="form3 pad">
					
					<h1 class="member">ID ã��</h1>
					<label for="user">�̸�</label><input type="text" id="userName">
					<label for="user">��ȭ��ȣ</label><input type="text" id="phoneNum"><br>
					<input type="button" value="Ȯ��" id="idCheck" style="margin-left: 45%"><br>
</div>
</div>
		</div>
		<div class="form8">
			<div class="form6">
				<div class="form3 pad">
					<h1 class="member">Passwordã��</h1>
					<label for="user">���̵�</label><input type="text" id="user">
					<label for="user">�̸�</label><input type="text" id="name"> <label
						for="user">�̸�</label><input type="text" id="name"> <label
						for="user">�̸�</label><input type="text" id="name"> <br>
						<input type="button" value="Ȯ��" id="idCheck"  style="margin-left: 45%">
				</div>
</div>
		</div>
			
	</div>
</form>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	$("#idCheck").click(function() {
		alert("����");
	
	});
</script>
</html>