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
			<th>강의 이름</th>
			<th>교수 이름</th>
			<th>분반</th>
		</tr>
		<tr>
			<td><input type="text" name="lectureName" id="lectureName"></td>
			<td><input type="text" name="professorName" id="professorName"></td>
			<td>
				<select name="lectureClass">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
				</select>
			</td>
		</tr>
	</table>
		<button type="submit" id="registerLectureBtn">등록</button>
		<button type="submit" id="cancelBtn">취소</button>
	</form>
</body>
<script type="text/javascript">
$("#cancelBtn").click(function(event){
	event.preventDefault();
	$(location).attr('href', "/lectureManage/main");
});
</script>
</html>