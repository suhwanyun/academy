<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:if test="${!empty msg}">
		<script>alert('${msg}');</script>
		<c:remove var="msg"/>
	</c:if>
<form>   <!--  로그인하기전 화면 -->
<div id="wrap">
<a href="<%=request.getContextPath() %>"><img 
						class="circular--logo"
						src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
 
  <div class="form10 pad">
   <h1 class="member">member login</h1>

     
      <label for="user">아이디</label><input type="text" id="userId">
      <label for="user">비밀번호</label><input type="password" id="userPass">
     <div class="pad">
     <input type="button"  value="로그인 하기"  id="loginBtn" class="bigbig">
     </div>
     <div class="pad">
      <input type="button" value="회원가입" id="joinBtn" class="bigbig">
      <input type="button" value="ID/Pass 찾기" id="searchBtn" class="bigbig">
    </div>
   </div>
 </div>
 </form>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/login.js"></script>
</html>