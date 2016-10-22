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
	href="/css/main.css" />
<link rel="stylesheet"
	href="/css/font-awesome.css" />

</head>
<body>
<jsp:include page="../message.jsp" />
<form>   <!--  로그인하기전 화면 -->
<div id="wrap">
<a href=""><img 
						class="circular--logo"
						src="/images/logo.png" alt="" /></a>
  <h1 class="member">member login</h1>
  <div class="form10 pad">
  

     
      <label for="user">아이디</label><input type="text" maxlength="10" id="userId">
      <label for="user">비밀번호</label><input type="password" maxlength="20" id="userPass">
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
<script src="/js/login.js"></script>
</html>