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
<form name="myForm" method="post">
<table>
  <tr>
    <th>강의 ID</th>
    <th>강의 이름</th>
    <th>교수 이름</th>
    <th>강의 분반</th>
  </tr>
  <tr>
    <td><input type="number" name="lectureId" id="lectureId" readonly="readonly" value="${lectureData.lectureId }"></td>
    <td><input type="text" name="lectureName" id="lectureName" value="${lectureData.lectureName }"></td>
    <td><input type="text" name="professorName" id="professorName" value="${lectureData.professorName }"></td>
    <td>
    	<select name="lectureClass" id="lectureClass" >
    	</select>
    	</td>
  </tr>
</table>
</form>
<input type="button" onclick="submitFun(1)" value="수정"/>
<input type="button" onclick="submitFun(2)" value="삭제"/>
<input type="button" id="cancelBtn" value="취소"/>


</body>
<script type="text/javascript">
 $("document").ready(function(){
	 $("#lectureClass").append("<option value='${lectureData.lectureClass}'>${lectureData.lectureClass}반</option>");
 });
 $("#cancelBtn").click(function(){
	 $(location).attr('href', "/lectureManage/main");
 });
 function submitFun(index){
	 if(index==1){
		 document.myForm.action='/lectureManage/manage';
	 }
	 if(index==2){
		 document.myForm.action='/lectureManage/drop';
	 }
	 document.myForm.submit();
 }
</script>
</html>