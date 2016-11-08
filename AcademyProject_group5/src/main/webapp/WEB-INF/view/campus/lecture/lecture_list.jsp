<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>내 강의 목록</title>
</head>
<body>
	<div class="container">
		<table class="table">
			<tr>
				<th>강의 명</th>
				<th>담당 교수</th>
				<th>강의 장소</th>
				<th>강의 시간</th>
				<th>반장 여부</th>
			</tr>
			<c:forEach items="${lectureList }" var="list">
				<tr align="center">
					<td><a href="#"> ${list.lectureName }&nbsp;${list.lectureClass }반
					</a></td>
					<td>${list.professorName }</td>
					<td>${list.lecturePlace }</td>
					<td><c:choose>
							<c:when test="${list.lectureWeek == 1}">
									일요일
								</c:when>
							<c:when test="${list.lectureWeek == 2 }">
									월요일
								</c:when>
							<c:when test="${list.lectureWeek == 3 }">
									화요일
								</c:when>
							<c:when test="${list.lectureWeek == 4 }">
									수요일
								</c:when>
							<c:when test="${list.lectureWeek == 5 }">
									목요일
								</c:when>
							<c:when test="${list.lectureWeek == 6 }">
									금요일
								</c:when>
							<c:when test="${list.lectureWeek == 7 }">
									토요일
								</c:when>
							<c:otherwise>
									error
								</c:otherwise>
						</c:choose> &nbsp;${list.lectureStart }교시~${list.lectureEnd }교시</td>
					<td>${list.isPresident }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>