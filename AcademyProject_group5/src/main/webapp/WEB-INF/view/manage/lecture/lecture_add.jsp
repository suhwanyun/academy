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
	<table>
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
		<button type="submit" id="registerLectureBtn">등록</button>
		<button type="button" id="cancelBtn">취소</button>
	</form>
</body>
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
		event.preventDefault();
		alert("입력 값이 올바르지 않습니다.");
		alert("1"+lectureIdCheck($("lectureId").val()));
		alert("2"+lectureIdCheck($("lectureName").val()));
		alert("3"+lectureIdCheck($("professorName").val()));
	}
	
});
function lectureIdCheck(x){
	var lectureId_PATTERN= /^[0-9]{1,15}$/;
	if (lectureId_PATTERN.test(x)) {
		return true;
	} else {
		return false;
	}
}
function lectureNameCheck(x){
	if (x.length>0 && x.length <=30) {
		return true;
	} else {
		return false;
	}
}
function professorNameCheck(x){
	var ko_NAME_PATTERN = /^[가-힣]{2,5}$/;
	var en_NAME_PATTERN = /^[a-zA-Z]{1,15}$/;
	if (ko_NAME_PATTERN.test(x) || en_NAME_PATTERN.test(x)) {
		return true;
	} else {
		return false;
	}
}
</script>
</html>