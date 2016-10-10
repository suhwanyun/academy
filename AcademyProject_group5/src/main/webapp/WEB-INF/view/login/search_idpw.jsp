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
					<label for="userName">�̸�</label><input type="text" id="userName">
					<label for="phoneNum">��ȭ��ȣ</label><input type="text" id="phoneNum"><br>
					<input type="button" value="Ȯ��" id="idCheck" style="margin-left: 45%"><br>
</div>
</div>
		</div>
		<div class="form8">
			<div class="form6">
				<div class="form3 pad">
					<h1 class="member">Passwordã��</h1>
					<label for="userId">���̵�</label><input type="text" id="userId"><br>
					<label for="passQuestion">��й�ȣ ����</label><textarea id = passQuestion> </textarea> <br>
					<label for="passAnswer">���� ��</label><input type="text" id="passAnswer"><br>
						<input type="button" value="Ȯ��" id="passCheck"  style="margin-left: 45%">
				</div>
</div>
		</div>
			
	</div>
</form>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	$("#idCheck").click(function() {
		$.ajax({
			type : "get",
			url : "findId",
			data : {
				userName : $("#userName").val(),
				phoneNum : $("#phoneNum").val()
			},
			success : function(res) {

				if (res.length>2) {
					alert("������ ���̵�� : "+res);
					
				} else {
					alert("��ȸ�� ���̵� �����ϴ�.");
				}

			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:"
						+ error);
			}
		});
	
	});
	$("#passCheck").click(function(){
		$.ajax({
			type : "get",
			url : "findPass",
			data : {
				userName : $("#userName").val(),
				phoneNum : $("#phoneNum").val()
			},
			success : function(res) {

				if (res.length>2) {
					alert("�ӽ� �н������ : ["+res+"]�Դϴ�.");
					
				} else {
					alert("���̵� ��й�ȣ ���� Ȯ���ϼ���.");
				}

			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:"
						+ error);
			}
		});
	});
	$("#userId").change(function(){
		$.ajax({
			type : "get",
			url : "findQuestion",
			data : {
				userName : $("#userId").val()
			},
			success : function(res) {

				if (res!=null) {
					$("#passQuestion").val(res)
					
				} else {
					$("#passQuestion").val("��ȸ�� ������ �����ϴ�. �����ڿ��� ���� �ϼ���");
				}

			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:"
						+ error);
			}
		});
	});
</script>
</html>