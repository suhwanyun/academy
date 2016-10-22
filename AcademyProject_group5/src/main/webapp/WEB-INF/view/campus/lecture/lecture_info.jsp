<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/main.css" />
<link rel="stylesheet"
	href="/css/font-awesome.css" />
<title>강의 정보 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/header/header.jsp" />
	<div id="wrap">
		<div class="upmargin">
			<div  class="form9 pad">
			<table class="list_table">
			<thead>
				<tr>
					<th>강의 이름</th>
					<th>강의 분반</th>
					<th>교수 이름</th>
				</tr>
			</thead> 
				<tr>
					<td>${lectureData.lectureName }</td>
					<td>${lectureData.lectureClass }반</td>
					<td>${lectureData.professorName }</td>
				</tr>
			</table>
			<table class="list_table">
				<c:forEach items="${lectureTime }" var="list">
					<tr>
						<th>강의 시작 시간</th>
						<th>강의 종료시간</th>
					</tr>
					<tr>
						<td>${list.lectureStart }교시</td>
						<td>${list.lectureEnd }교시</td>
					</tr>
					<tr>
						<th>강의 장소</th>
						<th>강의 요일</th>
					</tr>
					<tr>
						<td>${list.lecturePlace }</td>
						<td>${list.lectureWeek }요일</td>
					</tr>
				</c:forEach>
			</table>
			
			<table>
			
				<tr>
					<td colspan="2" align="center">
					<input type="checkbox" name="ban"  id="attention1">
					<label for="attention1">반장신청</label></td>
					<td><button class="fit">신청</button></td>
					<td><button class="fit">취소</button></td>
				</tr>
		
			</table>
				
		
			</div>
		</div>
	</div>
</body>
</html>