<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
<title>강의 정보 페이지</title>
</head>
<body>
	<table>
		<tr>
			<th>강의 이름</th>
			<th>강의 분반</th>
			<th>교수 이름</th>
		</tr>
		<c:forEach items="${lectureData }" var="lecture">
		<tr>
			<td>${lecture.lectureName }</td>
			<td>${lecture.lectureClass }</td>
			<td>${lecture.professorName }</td>
		</tr>
		</c:forEach>
	</table>
	<table>
		<c:forEach items="${lectureTime }" var="list">
		<tr>
			<th>강의 시작 시간</th>
			<th>강의 종료시간</th>
		</tr>
		<tr>
			<td>${list.lectureStart }</td>
			<td>${list.lectureEnd }</td>
		</tr>
		<tr>
			<td>강의 장소</td>
			<td>강의 요일</td>
		</tr>
		<tr>
			<td>${list.lecturePlace }</td>
			<td>${list.lectureWeek }</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>