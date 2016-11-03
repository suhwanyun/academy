<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/manage/header.jsp" />
	<sform:form id="myForm" modelAttribute="timeData" method="post">
		<table>
			<tr>
				<th>강의 ID</th>
				<th>시작 교시</th>
				<th>종료 교시</th>
				<th>장소</th>
				<th>강의 요일</th>
			</tr>
			<tr>
				<td><sform:hidden path="lectureTimeId"
						value="${timeData.lectureTimeId  }" /> <sform:hidden
						path="lectureClass" value="${timeData.lectureClass }" /> <sform:hidden
						path="lectureWeek" value="${timeData.lectureWeek  }" /> <sform:input
						readonly="true" path="lectureId" value="${timeData.lectureId }" />
				<td><sform:input type="number" path="lectureStart"
						value="${timeData.lectureStart  }" /></td>
				<td><sform:input type="number" path="lectureEnd"
						value="${timeData.lectureEnd  }" /></td>
				<td><sform:input type="text" path="lecturePlace"
						value="${timeData.lecturePlace  }" /></td>
				<td><select id="selWeek">
						<option value="1">일</option>
						<option value="2">월</option>
						<option value="3">화</option>
						<option value="4">수</option>
						<option value="5">목</option>
						<option value="6">금</option>
						<option value="7">토</option>
				</select></td>
			</tr>
		</table>
	</sform:form>
	<input type="button" onclick="submitFun(1)" value="수정">
	<input type="button" onclick="submitFun(2)" value="삭제">
	<input type="button" id="cancelBtn" value="취소">
</body>
<script src="/js/manageVaildation.js"></script>
<script type="text/javascript">
	$("document").ready(
			function() {
				$("#selWeek option:eq(${timeData.lectureWeek - 1  })").attr(
						"selected", "selected");
			});
	$("#selWeek").change(function() {
		$("#lectureWeek").val($("#selWeek").val());
	});
	$("#cancelBtn").click(function() {
		$(location).attr('href', "/lectureManage/main");
	});
	function submitFun(index) {
		if (index == 2 || lectureStartEndCheck($("#lectureStart").val(), $("#lectureEnd")
				.val())
				&& lecutrePlaceCheck($("#lecturePlace").val())
				&& weekCheck($("#lectureWeek").val())) {
			if (index == 1) {
				if (confirm("정말로 수정 하시겠습니까?")) {
					$("#myForm").attr("action", "/lectureManage/timeManage");
				} else {
					preventDefault();
				}
			}
			if (index == 2) {
				if (confirm("정말로 삭제 하시겠습니까?")) {
					$("#myForm").attr("action", "/lectureManage/timeDrop");
				} else {
					preventDefault();
				}

			}
			$("#myForm").submit();

		} else {
			alert("입력 값을 확인해주세요");
		}

	}
</script>
</html>