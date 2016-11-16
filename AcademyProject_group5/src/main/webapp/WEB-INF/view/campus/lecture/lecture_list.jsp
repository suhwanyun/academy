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
	<div  style="width: 600px;">
		<table class="table table-bordered">
			<tr>
				<th>강의 명</th>
				<th>담당 교수</th>
				<th>강의 장소</th>
				<th>강의 시간</th>
				<th></th>
			</tr>
			<c:set var="spanCount" value="0"/>
			<c:set var="doneLoop" value="false"/>
			
			<c:forEach items="${lectureList }" var="list"  varStatus="index">
				<tr align="center" onclick="movePage(this, ${list.lectureId}, ${list.lectureClass })">
				
					<c:if test="${spanCount lt 1}">	
								
						<c:forEach items="${lectureList }" var="subList" varStatus="subIndex">
							<c:if test="${(subIndex.index - index.index) gt -1 && not doneLoop }">
								<c:set var="spanCount" value="${subIndex.index - index.index + 1}"/>
								
								<c:if test="${index.last}">
									<c:set var="spanCount" value="1"/>
								</c:if>
								
								<c:if test="${subIndex.last || list.lectureId != lectureList[subIndex.index].lectureId}">
									<c:set var="doneLoop" value="true"/>
									<td rowspan="${spanCount }">${list.lectureName }&nbsp;(${list.lectureClass })</td>
									<td rowspan="${spanCount }">${list.professorName }</td>
								</c:if>
							</c:if>
						</c:forEach>
						
					</c:if>
				
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
						</c:choose> &nbsp;${list.lectureStart }교시~${list.lectureEnd }교시
					</td>
						
					<c:if test="${doneLoop }">
						<c:set var="doneLoop" value="false"/>
			
						<td rowspan="${spanCount }">
						<c:choose>
							<c:when test="${list.isPresident =='Y'}">
								<button class="myButton lectureChangeBtn" onclick="movePage2(this)" value="${list.lectureTimeId}">강의 공지</button>
							</c:when>
							<c:otherwise>
								<button class="myButton lectureCancelBtn" onclick="cancel(this)" value="${list.lectureId}">강의 취소</button>
							</c:otherwise>
						</c:choose>
						</td>
				
					</c:if>
								
					<c:set var="spanCount" value="${spanCount - 1}"/>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<script type="text/javascript">
var targetVal;
function movePage(el,id,Lclass){
	$(location).attr("href", "/lecture/lectureMain?lectureId="+id+"&lectureClass="+Lclass);
}
function movePage2(el){
	targetVal=$(el).val();
}
function cancel(el){
	targetVal=$(el).val();
}
$(".lectureChangeBtn").click(function(event){
	event.stopPropagation();
	$(location).attr("href","/write/lectureTimeNotiAddjsp?lectureTimeId="+targetVal);
});
$(".lectureCancelBtn").click(function(event){
	event.stopPropagation();
	if(confirm("정말 삭제 하시겠습니까?")){
		$(location).attr("href","/lecture/lectureApplyCancel?lectureId="+targetVal);
	}
})
</script>
</html>