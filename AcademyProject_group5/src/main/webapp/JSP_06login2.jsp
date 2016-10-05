<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String id = request.getParameter("id");
   String pass = request.getParameter("pass");

   // 입력한 정보가 설정해둔 정보와 맞는지 체크
   if(id.equals("java") && pass.equals("1234")){   
      session.setAttribute("loginId", "java");
      response.sendRedirect("JSP_06login1.jsp");
   
   // 입력한 정보가 틀릴경우
   }else{
      %>
         <script>
            alert('아이디나 암호가 틀림');
            history.go(-1);
         </script>
      <%
   }
   
%>