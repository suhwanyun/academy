<%@ page language="java" contentType="text/html; charset=EUC-KR"   pageEncoding="EUC-KR"%>
<%@ page import ="java.util.Map" %>
<html>
<head>
<title>login form </title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/font-awesome.css" />

</head>

<form>   <!--  �α����ϱ��� ȭ�� -->
<div id="wrap">
<a href="<%=request.getContextPath() %>"><img 
						class="circular--logo"
						src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
 
  
   <h1 class="member">member login</h1>
   <div class="form">
    <div class="form2">
     <div class="form3 pad">
      <label for="user">���̵�</label><input type="text" id="userId">
      <label for="user">��й�ȣ</label><input type="password" id="userPass">
     </div>
     <div class="pad">
     <input type="button" value="�α����ϱ�"  id="loginBtn">
     </div>
     <div class="pad">
      <input type="button" value="ȸ������" id="joinBtn">
      <input type="button" value="ID/Pass ã��" id="searchBtn">
    </div>
   </div>
  </div>
  </div>
 </form>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	$("#loginBtn").click(function(){
		$.ajax({
			type: "post",
			url : "login",
			data : {
				userId : $("#userId").val(),
				userPass : $("#userPass").val()
			},
			success : function(res) {
				if (res == "true") {
					$(location).attr('href',"main");
				} else {
					alert("���̵�, ��й�ȣ�� Ȯ���ϼ���");
				}
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:"
						+ error);
			}
		});
	});
	
	$("#searchBtn").click(function(){
		$(location).attr('href',"searchidpw");
	});
	$("#joinBtn").click(function(){
		$(location).attr('href',"joinBtn");
	});
	
</script>
</html>