<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/main.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<sform:form method="post" action="/notiSetting"
		modelAttribute="settingData">
		<h1 class="member">알림 설정</h1>
		<table>
			<colgroup>
				<col width="50">
				<col width="25">
				<col width="25">
			</colgroup>
			<tr>
				<td></td>
				<td><button id="all_ONBtn">전체 켜기</button></td>
				<td><button id="all_OFFBtn">전체 끄기</button></td>
			</tr>
		</table>
		<c:forEach items="${settingData.settingList}" var="list" varStatus="status">
			<table>
				<tr>
					<sform:hidden path="list[${status.index }].notiType" value="${list.notiType }"></sform:hidden>
					<sform:hidden path="list[${status.index }].userId" value="${list.userId }"></sform:hidden>
					<sform:hidden path="list[${status.index }].weekCode" value="${list.weekCode }"></sform:hidden>
					<sform:hidden path="list[${status.index }].notiOn" value="${list.notiOn }"></sform:hidden>
					<sform:hidden path="list[${status.index }].notiHour" value="${list.notiHour }"></sform:hidden>
					<sform:hidden path="list[${status.index }].notiMin" value="${list.notiMin }"></sform:hidden>
					<c:choose>
						<c:when test="${list.notiType == 'lecture'}">
							<td>강의 시간</td>
						</c:when>
						<c:when test="${list.notiType == 'place'}">
							<td>명소 알림</td>
						</c:when>
						<c:when test="${list.notiType == 'play'}">
							<td>오락 알림</td>
						</c:when>
						<c:when test="${list.notiType == 'food'}">
							<td>음식 알림</td>
						</c:when>
						</c:choose>
						<c:if test="${list.notiType == 'lecture'}">
							<td><output id="${list.notiType}_time">${list.notiMin }분전</output></td>
						</c:if>
						<c:if test="${list.notiType != 'lecture'}">
							<td><output id="${list.notiType}_time">${list.notiHour }:${list.notiMin }</output></td>
						</c:if>
						<td><output id="${notiType }_weekSetting">요일 선택</output></td>
						<td></td>
						<td>
							<c:if test="${list.notiOn == 1}">
								<span id="${notiType }_onoffBtn">ON</span>
							</c:if>
							<c:if test="${list.notiOn == 0}">
								<span id="${notiType }_onoffBtn">OFF</span>
							</c:if>
						</td>
				</tr>
			</table>
		</c:forEach>

		<sform:button id="sendSetting" type="submit">설정 저장</sform:button>
	</sform:form>
</body>
</html>