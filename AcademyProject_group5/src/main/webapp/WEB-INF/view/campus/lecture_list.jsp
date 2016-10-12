<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>전체 강의 목록</title>
</head>
<body>
	<table>
		<tr>
			<td><select>
					<option selected="selected">선택</option>
					<option>강의 이름</option>
					<option>교수 이름</option>
			</select></td>
			<td><input type="search" /></td>
			<td><button>검색</button></td>
		</tr>
	</table>
	<p>${lectureList }</p>
	<table>
		<tr>
			<th>강의 이름</th>
			<th>교수 이름</th>
			<th>강의 분반</th>
		</tr>
		<c:forEach items="${lectureList }" var="list">
		
		<tr>
			<td>${list.lectureName}</td>
			<td>${list.professorName}</td>
			<td>${list.lectureClass}</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="2" align="right"><button>더 보기</button></td>
			<td align="right"><button>맨 위로</button></td>
		</tr>
	</table>

</body>
</html>