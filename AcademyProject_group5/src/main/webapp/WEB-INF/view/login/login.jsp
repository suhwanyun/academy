<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>login form </title>
<%
   //  �α����� �ȵ� ���¿��� ���̵�� ��ȣ�� �ְ� java/1234�� ���
   // session�� loginId �� �α����� ������ ���̵� �����ϰ� 
   // �α��� �������� ���ƿ��� �α��� ������ ȭ���� �������� 
   // �α��� ���� ȭ�鿡�� �α׾ƿ� ��ư�� ������ session.invalidate()
   // �� �̿��ؼ� ���� ������ ��� ��ȿȭ ��Ű�� �α��� ��������
   // ���ƿ��� �α��� ���� ȭ���� �������� ó�� 
   // JSP_06login1.jsp : �α��� ����ȭ��� �α��� ���� ȭ���� if�� �̿��ؼ� ����
   // JSP_06login2.jsp : �α��� ȭ�鿡�� �Ѿ�� ���̵�� ��ȣ�ηα��� ������ �˷��ش�.
   // JSP_06login3.jsp : �α׾ƿ� : ���� ������ ��� ��ȿȭ
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
   if(loginId == null){      // ���ǿ� ��������(loginId)�� ���� ��쿡�� �α���â ���
%>

<form name="f1" action="JSP_06login2.jsp" >   <!--  �α����ϱ��� ȭ�� -->
   <div id="tableContainer">
   <table width="604" height="220" cellspacing="4" 
         style="border: 2px dotted rgb(199,185,194); FILTER: alpha(opacity=100);">
         
      <tr>
         <td style="background: url('http://cfile264.uf.daum.net/image/1267D6254A39EAB24FEE1E') no-repeat 0% 50%;
         border: 1px solid rgb(199,185,194); text-align:center; ">���̵�</td><td><input type="text" name="id" size="8"></td>
      </tr>
      
      <tr>
         <td style="background: url('http://cfile264.uf.daum.net/image/1267D6254A39EAB24FEE1E') no-repeat 0% 50%; 
         border: 1px solid rgb(199,185,194);text-align:center;">
         ��ȣ</td><td><input type="password" name="pass" size="8"></td>
      </tr>
      
      <tr align="center"><td colspan="2"><input type="submit" value="�α���"></td></tr>
      
      <tr><td><input type="button" value="ȸ������"></td>
      
      <td><input type="button" value="ID/Passã��"></td></tr>
   </table>
   </div>
</form>
<% }else { %>
<hr>

<form name="f1" action="index.jsp" >   <!--  �α��ε� ȭ�� -->
   <table width="200" height="100">
      <tr>
         <td><input type="submit" value="�α׾ƿ�" ></td>
      </tr>
      <tr>
         <td>(<%=loginId %>)�� </td>
      </tr>
      <tr>
         <td>ȸ����������</td>
      </tr>   
      <tr>
         <td>ȸ������</td>
   </table>
</form>
<% } %>


</body>
</html>