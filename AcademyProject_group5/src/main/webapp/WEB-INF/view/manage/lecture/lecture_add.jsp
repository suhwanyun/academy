<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>강의 등록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/manage/header.jsp" />
	<form action="/lectureManage/add" method="post">
	<div class="containerM center text-center Lmargin">
	<table class="table bigFont">
		<tr>
			<th>강의 ID</th>
			<th>강의 이름</th>
			<th>교수 이름</th>
			<th>분반</th>
		</tr>
		<tr>
			<td><input type="number" name="lectureId" id="lectureId"></td>
			<td><input type="text" name="lectureName" id="lectureName"></td>
			<td><input type="text" name="professorName" id="professorName"></td>
			<td>
				<select name="lectureClass">
					<option value="1">1반</option>
					<option value="2">2반</option>
					<option value="3">3반</option>
				</select>
			</td>
		</tr>
	</table>
		<button type="submit" id="registerLectureBtn" class="myButtonBig">등록</button>
		<button type="button" id="cancelBtn" class="myButtonBig">취소</button>
		</div>
	</form>
</body>
<script src="/js/manageVaildation.js"></script>
<script type="text/javascript">

$("#cancelBtn").click(function(event){
	event.preventDefault();
	$(location).attr('href', "/lectureManage/main");
});
$("#registerLectureBtn").click(function(event){
	if(lectureIdCheck($("#lectureId").val())&&
		lectureNameCheck($("#lectureName").val())&&	
		professorNameCheck($("#professorName").val())
	){
		
	}else{
		alert("입력 값이 올바르지 않습니다.");
		event.preventDefault();
		
	}
	
});
</script>
</html>