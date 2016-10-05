<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>login form </title>
<%
   //  로그인이 안된 상태에서 아이디와 암호를 넣고 java/1234일 경우
   // session에 loginId 에 로그인한 유저의 아이디를 저장하고 
   // 로그인 페이지로 돌아오면 로그인 이후의 화명이 보여지고 
   // 로그인 이후 화면에서 로그아웃 버튼을 누르면 session.invalidate()
   // 를 이용해서 세션 정보를 모두 무효화 시키고 로그인 페이지로
   // 돌아오면 로그인 이전 화면이 보여지게 처리 
   // JSP_06login1.jsp : 로그인 이전화면과 로그인 이후 화면을 if을 이용해서 구성
   // JSP_06login2.jsp : 로그인 화면에서 넘어온 아이디와 암호로로그인 정보를 알려준다.
   // JSP_06login3.jsp : 로그아웃 : 세션 정보를 모두 무효화
%>
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
<%
   String loginId = (String)session.getAttribute("loginId");
%>
<% 
   if(loginId == null){      // 세션에 유저정보(loginId)가 없을 경우에만 로그인창 출력
%>

<form name="f1" action="JSP_06login2.jsp" >   <!--  로그인하기전 화면 -->
   <div id="tableContainer">
   <table width="604" height="220" cellspacing="4" 
         style="border: 2px dotted rgb(199,185,194); FILTER: alpha(opacity=100);">
         
      <tr>
         <td style="background: url('http://cfile264.uf.daum.net/image/1267D6254A39EAB24FEE1E') no-repeat 0% 50%;
         border: 1px solid rgb(199,185,194); text-align:center; ">아이디</td><td><input type="text" name="id" size="8"></td>
      </tr>
      
      <tr>
         <td style="background: url('http://cfile264.uf.daum.net/image/1267D6254A39EAB24FEE1E') no-repeat 0% 50%; 
         border: 1px solid rgb(199,185,194);text-align:center;">
         암호</td><td><input type="password" name="pass" size="8"></td>
      </tr>
      
      <tr align="center"><td colspan="2"><input type="submit" value="로그인"></td></tr>
      
      <tr><td><input type="button" value="회원가입"></td>
      
      <td><input type="button" value="ID/Pass찾기"></td></tr>
   </table>
   </div>
</form>
<% }else { %>
<hr>

<form name="f1" action="index.jsp" >   <!--  로그인된 화면 -->
   <table width="200" height="100">
      <tr>
         <td><input type="submit" value="로그아웃" ></td>
      </tr>
      <tr>
         <td>(<%=loginId %>)님 </td>
      </tr>
      <tr>
         <td>회원정보수정</td>
      </tr>   
      <tr>
         <td>회원가입</td>
   </table>
</form>
<% } %>


</body>
</html>