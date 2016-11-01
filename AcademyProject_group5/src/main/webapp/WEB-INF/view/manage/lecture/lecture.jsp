<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>강의 관리자 메인화면</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/manage/header.jsp" />
	<div class="container">
		<button id="lectureAddBtn">강의 등록</button>
		<table>
			<c:forEach items="${lectureList }" var="list" >
			
			<tr>
				<td><a href="/lectureManage/managejsp?lectureId=${list.lectureId }&lectureClass=${list.lectureClass }">${list.lectureId }</a></td>
				<td>${list.lectureName }</td>
				<td>${list.professorName }</td>
				<td>${list.lectureClass }반</td>
				<td>
					<ul>
						<c:forEach items="${list.lecturetimeList }" var="timeList" >
							<li>
								<span>${timeList.lectureStart }교시</span>
								<span>${timeList.lectureEnd }교시</span>
								<span>${timeList.lecturePlace }</span>
								<span>${timeList.lectureWeek }</span>
								<c:choose>
									<c:when test="${!empty timeList.isTempDate }">
										<span>${timeList.isTempDate }</span>
									</c:when>
									<c:otherwise>
										<span>-</span>
									</c:otherwise>
								</c:choose>
							</li>
						</c:forEach>
						<li>
							<a href="/lectureManage/timeAdd?lectureId=${list.lectureId }&lectureClass=${list.lectureClass}" >+</a>
						</li>
					</ul>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
<script type="text/javascript">
$("#lectureAddBtn").click(function(){
	$(location).attr('href', "/lectureManage/addjsp");
});
</script>
</html>