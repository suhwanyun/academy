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

<form>   <!--  로그인하기전 화면 -->
<div id="wrap">
<a href="<%=request.getContextPath() %>"><img 
						class="circular--logo"
						src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
 
  
   <h1 class="member">member login</h1>
   <div class="form">
    <div class="form2">
     <div class="form3 pad">
      <label for="user">아이디</label><input type="text" id="user">
      <label for="user">비밀번호</label><input type="password" id="user">
     </div>
     <div class="pad">
     <input type="submit" value="로그인하기"  id="loginBtn">
     </div>
     <div class="pad">
      <input type="button" value="회원가입" >
      <input type="button" value="ID/Pass 찾기" id="searchBtn">
    </div>
   </div>
  </div>
  </div>
 </form>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	<%session.setAttribute("userdata", "value");%>
	$("#loginBtn").click(function(){
		alert("성공");
		$(location).attr('href',"view/index.jsp");	
	});
	
	<%session.setAttribute("userdata", "value");%>
	$("#searchBtn").click(function(){
		$(location).attr('href',"search_idpw.jsp");	
	});
	
</script>
</html>