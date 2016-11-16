<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의 수정 페이지</title>
</head>
<body>

	<jsp:include page="/WEB-INF/view/manage/header.jsp" /><br>
		<div class="container">
	<form name="myForm" method="post">
		<table class="table table-bordered" >
			<tr>
				<th>강의 ID</th>
				<th>강의 이름</th>
				<th>교수 이름</th>
				<th>강의 분반</th>
			</tr>
			<tr align="center">
				<td><input type="number" name="lectureId" id="lectureId"
					readonly="readonly" value="${lectureData.lectureId }"></td>
				<td><input type="text" name="lectureName" id="lectureName"
					value="${lectureData.lectureName }"></td>
				<td><input type="text" name="professorName" id="professorName"
					value="${lectureData.professorName }"></td>
				<td><select name="lectureClass" id="lectureClass">
				</select></td>
			</tr>
		</table>
	</form>
	<div class="bRight">
	<input type="button" onclick="submitFun(1)" value="수정"  class="myButtonBig"/>
	<input type="button" onclick="submitFun(2)" value="삭제" class="myButtonBig"/>
	<input type="button" id="cancelBtn" value="취소" class="myButtonBig"/>
</div>
</div>
</body>
<script src="/js/manageVaildation.js"></script>
<script type="text/javascript">
	$("document")
			.ready(
					function() {
						$("#lectureClass")
								.append(
										"<option value='${lectureData.lectureClass}'>${lectureData.lectureClass}반</option>");
					});
	$("#cancelBtn").click(function() {
		$(location).attr('href', "/lectureManage/main");
	});
	function submitFun(index) {
		if (index==2||lectureIdCheck($("#lectureId").val())
				&& lectureNameCheck($("#lectureName").val())
				&& professorNameCheck($("#professorName").val())) {
			if (index == 1) {
				if (confirm("정말로 수정 하시겠습니까?")) {
					document.myForm.action = '/lectureManage/manage';
				} else {
					preventDefault();
				}
			}
			if (index == 2) {
				if (confirm("정말로 삭제 하시겠습니까?")) {
					document.myForm.action = '/lectureManage/drop';
				} else {
					preventDefault();
				}
			}
			document.myForm.submit();
		} else {
			alert("입력 값이 올바르지 않습니다.");
			preventDefault();
		}
	}
</script>
</html>