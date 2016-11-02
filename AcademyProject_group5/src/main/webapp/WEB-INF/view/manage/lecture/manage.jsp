<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의 수정 페이지</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/manage/header.jsp" />
<form action="/lectureManage/manage" method="post">
<table>
  <tr>
    <th>강의 ID</th>
    <th>강의 이름</th>
    <th>교수 이름</th>
    <th>강의 분반</th>
  </tr>
  <tr>
    <td><input type="number" id="lectureId" readonly="readonly" value="${lectureData.lectureId }"></td>
    <td><input type="text" id="lectureName" value="${lectureData.lectureName }"></td>
    <td><input type="text" id="professorName" value="${lectureData.professorName }"></td>
    <td>
    	<select id="lectureClass" >
    	</select>
    	</td>
  </tr>
</table>
<button type="submit">수정</button>
<button id="deleteBtn" >삭제</button>
<button id="cancelBtn">취소</button>
</form>


</body>
<script type="text/javascript">
 $("document").ready(function(){
	 $("#lectureClass").append("<option value='${lectureData.lectureClass}'>${lectureData.lectureClass}반</option>");
 });
 $("#cancelBtn").click(function(){
	 $(location).attr('href', "/lectureManage/main");
 });
</script>
</html>