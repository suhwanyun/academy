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

<form >   <!--  로그인하기전 화면 -->
ID찾기
<a href="<%=request.getContextPath() %>"><img 
						class="circular--logo"
						src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
 <label for="user">아이디</label><input type="text" id="user">
 <label for="user">전화번호</label><input type="text" id="phone">
 <input type="button" value="확인" id="idCheck">

 </form>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	<%session.setAttribute("userdata", "value");%>
	$("#idCheck").click(function(){
		alert("성공");
		$(location).attr('href',"index.jsp");	
	});
	
	
</script>
</html>