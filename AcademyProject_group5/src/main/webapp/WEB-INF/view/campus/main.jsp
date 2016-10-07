<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <jsp:include page="../campus/lecture_list.jsp" />
<jsp:include page="../campus/lnoti_list.jsp" />
<jsp:include page="/lecture_list.jsp" />
<jsp:include page="../campus/schedule.jsp" /> --%>
<img src="/images/logo.png"/>
<div class="columnsBtn">

<a href="<%=request.getContextPath() %>/notiList" class="button big alt"><span>내 강의 목록</span></a>
<a href="<%=request.getContextPath() %>/notiInfo" class="button big alt"><span>공지 내역</span></a>
<a href="<%=request.getContextPath() %>/lectureList" class="button big alt"><span>강의 선택</span></a>
<a href="<%=request.getContextPath() %>/schedule" class="button big alt"><span>시간표</span></a>

	
</div>
</body>
</html>