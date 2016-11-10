<%@ page language="java" import="java.sql.*,java.io.*" contentType="text/html;charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>�о� �Խ���</title>
</head>

<body>

<div class="container text-center">
      	<h1>�Ļ� �Խ���</h1>
            <table class="table-condensed">
           
               <tr>
                  <td><select id="searchType">
                        <option selected="selected" value="user">�ۼ���</option>
                        <option value="title">����</option>
                        <option value="content">����</option>
                        <option value="all">����+����</option>

                  </select></td>
                  <td colspan="2"><input type="search" id="searchInput" ></td>
                  <td class="text-center"><input type="button" id="searchBtn" class="bRight myButton"
                     value="ã��"></td>
               </tr>
               
               <tr>

                  <td colspan="3">
                     <input type="radio" name="sortVal" checked="checked" value="time">��¥
                     <input type="radio" name="sortVal" value="recommend">��õ
                  </td>
                  <td>
                     <button id="writeBtn" class="myButton bRight">�۾���</button>
                  </td>
               </tr>
              
            </table>
		</div>
		<div class="container text-center">
            <table class="list_table">
               <colgroup>
                  <col width="20%">
                  <col width="20%">
                  <col width="10%">
                  <col width="50%">
               </colgroup>
               <c:forEach items="${postingDataList }" var="list">
                  <tr class="tableData" onclick="movePage(${list.postingId})">
                     <td rowspan="2"><img class="imgBoard"
                        onerror="errorFun(this);"
                        src="/upload/preview_${list.postingPhoto}" /></td>
                     <td colspan="3">${list.postingTitle }</td>
                  </tr>
                  <tr class="tableData" onclick="movePage(${list.postingId})">
                     <td>${list.userId }</td>
                     <td>${list.postingTime }</td>
                  </tr>
               </c:forEach>
             <tr id="beforeLocation" >
               <td colspan=4><button id="moreBtn" class="myButton foodBtn">������</button>
               	<span id="moveToStartBtn">
               		<img src="/images/arrow.PNG" alt="ȭ��ǥ�̹���"/>
               	</span>
               </td>
            </tr>
             </table>
         </div>

</body>
</html>