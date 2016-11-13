<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>강의 정보 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />

	<div class="container text-center tMargin">
		<form action="/lecture/lectureApply" method="post">
			<table class="table table-bordered">
				<tr class="gray">
					<th>강의 이름</th>
					<th>강의 분반</th>
					<th>교수 이름</th>
				</tr>
				<tr>
					<td>${lectureData.lectureName }</td>
					<td>${lectureData.lectureClass }반</td>
					<td>${lectureData.professorName }</td>
				</tr>
			</table>
			<c:forEach items="${lectureTime }" var="list">
				<table class="table table-bordered">
					<tr>
						<th class="gray">시작/종료 시간</th>
						<td colspan="3">
						${list.lectureStart }교시
							&nbsp;&nbsp;&nbsp;&nbsp; ~
							&nbsp;&nbsp;&nbsp;&nbsp;
							${list.lectureEnd }교시</td>
					</tr>
					<tr>
						<th class="gray">강의 장소</th>
						<td>${list.lecturePlace }</td>
						<th class="gray">강의 요일 </th>
						<td>
						<c:choose>
						<c:when test="${list.lectureWeek == 1}">
							일요일
						</c:when>
						<c:when test="${list.lectureWeek == 2}">
							월요일
						</c:when>
						<c:when test="${list.lectureWeek == 3}">
							화요일
						</c:when>
						<c:when test="${list.lectureWeek == 4}">
							수요일
						</c:when>
						<c:when test="${list.lectureWeek == 5}">
							목요일
						</c:when>
						<c:when test="${list.lectureWeek == 6}">
							금요일
						</c:when>
						<c:when test="${list.lectureWeek == 7}">
							토요일
						</c:when>
					</c:choose>
					</td>
					</tr>
				</table>
			</c:forEach>
			<div>
				<input type="checkbox" id="PresidentCheckBox"> 
				<label for="PresidentCheckBox">반장신청</label>
				<button type="submit" id="applyBtn" class="myButton">신청</button>
				<button id="cancelBtn" class="myButton">취소</button>
			</div>
			<!-- 폼을 통해 전송되는 히든 요소들 -->
			<input type="hidden" id="isPresident" name="isPresident"
				value="false"> <input type="hidden" name="lectureId"
				value="${lectureData.lectureId }"> <input type="hidden"
				name="lectureClass" value="${lectureData.lectureClass }">
		</form>
	</div>
</body>
<script type="text/javascript">
	<c:if test="${isTermStarted == true}">
	$(document).ready(function(){
		$("#PresidentCheckBox").attr("type", "hidden");
	});
	</if>
	$("#cancelBtn").click(function(event) {
		event.preventDefault();
		$(location).attr('href', "/campus/campusMain");
	});
	$("#PresidentCheckBox").change(function() {
		if ($("#PresidentCheckBox").prop("checked") == true) {
			$("#isPresident").val('Y');
		} else {
			$("#isPresident").val('N');
		}

	});
</script>
</html>