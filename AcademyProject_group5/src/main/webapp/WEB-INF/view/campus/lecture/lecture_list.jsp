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
	<div  style="width: 500px;">
		<table class="table table-bordered">
			<tr>
				<th>강의 명</th>
				<th>담당 교수</th>
				<th>강의 장소</th>
				<th>강의 시간</th>
				<th></th>
			</tr>
			<c:forEach items="${lectureList }" var="list"  varStatus="index">
				<tr align="center" onclick="movePage(this, ${list.lectureId}, ${list.lectureClass })">
					<c:choose>
						<c:when test="${list.lectureId == lectureList[index.index +1].lectureId}">
							<td rowspan="2">${list.lectureName }&nbsp;(${list.lectureClass })</td>
							<td rowspan="2">${list.professorName }</td>
							<td rowspan="2">${list.lecturePlace }</td>
						</c:when>
						<c:when test="${list.lectureId == lectureList[index.index -1].lectureId}">
						</c:when>
						<c:otherwise>
							<td>${list.lectureName }&nbsp;(${list.lectureClass })</td>
							<td>${list.professorName }</td>
							<td>${list.lecturePlace }</td>
						</c:otherwise>
					</c:choose>
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
					<c:if test="${list.isPresident =='Y'}">
						<td><button class="myButton writeNoticeBtn">공지 쓰기</button></td>
					</c:if>
					
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<script type="text/javascript">
$("document").ready(function(){
	console.log("${lectureList }");
})
function movePage(el,id,Lclass){
	$(location).attr("href", "/lecture/lectureMain?lectureId="+id+"&lectureClass="+Lclass);
}
$(".writeNoticeBtn").click(function(event){
	event.stopPropagation();
	$(location).attr("href","/write/lectureNotiAddjsp")
});
</script>
</html>