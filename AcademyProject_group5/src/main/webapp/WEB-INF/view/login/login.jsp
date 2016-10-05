<%@ page language="java" contentType="text/html; charset=EUC-KR"   pageEncoding="EUC-KR"%>
<%@ page import ="java.util.Map" %>
<html>
<head>
<title>login form </title>

<style>


body { margin: 0px; height: 100%; }

#tableContainer{
position: absolute;
top: 50%;
margin-top: -110px;
width: 100%;
text-align: center;
margin-left: 300px;
}

</style>
</head>
<body topmargin="0" leftmargin="0">

<form name="f1" >   <!--  로그인하기전 화면 -->
   <div id="tableContainer">
   <table width="604" height="220" cellspacing="4">
         
      <tr>
         <td style="border: 1px solid rgb(199,185,194); text-align:center; ">아이디</td><td><input type="text" id="id" name="id" size="8"></td>
      </tr>
      
      <tr>
         <td style="border: 1px solid rgb(199,185,194);text-align:center;">
         암호</td><td><input type="password" name="pass" size="8"></td>
      </tr>
      
      <tr align="center"><td colspan="2"><input type="button" id="loginBtn"value="로그인"></td></tr>

   </table>
   </div>
</form>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	<%session.setAttribute("userdata", "value");%>
	$("#loginBtn").click(function(){
		alert("성공");
		$(location).attr('href',"index.jsp");	
	});
	
	
</script>
</html>