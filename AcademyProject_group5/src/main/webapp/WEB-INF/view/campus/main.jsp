<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/css/font-awesome.css" />
<title>Insert title here</title>
</head>
<body>
<%-- <jsp:include page="../campus/lecture_list.jsp" />
<jsp:include page="../campus/lnoti_list.jsp" />
<jsp:include page="/lecture_list.jsp" />
<jsp:include page="../campus/schedule.jsp" /> --%>

	<a href="<%=request.getContextPath() %>">
	<img class="circular--logo"
	src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
<table>
<colgroup>
			<col width="25%"/>
			<col width="25%"/>
			<col width="25%"/>			
			<col width="25%"/>
		</colgroup>

	<tr>
	<td><a href="<%=request.getContextPath() %>/lecture/selectedLectureList" 
	class="button altButton"><span>내 강의 목록</span></a></td>
	<td><a href="<%=request.getContextPath() %>/lecture/lectureNotiList" 
	class="button altButton"><span>공지 내역</span></a></td>
	<td><a href="<%=request.getContextPath() %>/lectureList" 
	class="button altButton"><span>강의 선택</span></a></td>
	<td><a href="<%=request.getContextPath() %>/schedule"
	class="button altButton"><span>시간표</span></a></td>
</tr>

	</table>

</body>
</html>