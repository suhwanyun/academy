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
						<c:forEach items="${list.lectureTimeList }" var="timeList" >
							<li><a href="/lectureManage/timeManagejsp?lectureTimeId=${timeList.lectureTimeId }">
								<span>${timeList.lectureStart }교시</span></a>
								<span>~</span>
								<span>${timeList.lectureEnd }교시</span>
								<span>${timeList.lecturePlace }</span>
								<c:choose>
									<c:when test="${timeList.lectureWeek == 1} ">
										<span>일요일</span>
									</c:when>
									<c:when test="${timeList.lectureWeek == 2 }">
										<span>월요일</span>
									</c:when>
									<c:when test="${timeList.lectureWeek == 3 }">
										<span>화요일</span>
									</c:when>
									<c:when test="${timeList.lectureWeek == 4 }">
										<span>수요일</span>
									</c:when>
									<c:when test="${timeList.lectureWeek == 5 }">
										<span>목요일</span>
									</c:when>
									<c:when test="${timeList.lectureWeek == 6 }">
										<span>금요일</span>
									</c:when>
									<c:when test="${timeList.lectureWeek == 7 }">
										<span>토요일</span>
									</c:when>
									<c:otherwise>
										<span>error</span>
									</c:otherwise>
								</c:choose>
							</li>
						</c:forEach>
						<li>
							<a href="/lectureManage/timeAddjsp?lectureId=${list.lectureId }&lectureClass=${list.lectureClass}" >+</a>
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