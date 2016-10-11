<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
<title>회원정보 수정</title>
</head>
<body>

<a href="<%=request.getContextPath()%>"><img
				class="circular--logo"
				src="<%=request.getContextPath()%>/images/logo.png" alt="" /></a>
			<h1 class="member">내 정보 수정</h1>
	<sform:form method="post" action="update" modelAttribute="userData">
		<sform:label path="userId">아이디</sform:label>
		<sform:input readonly="true" type="text" path="userId"
			value="${user.userId}" />
		<sform:label path="userPass">비밀번호 변경</sform:label>
		<sform:input placeholder="변경할 경우에만 입력하시오" maxlength="20" type="password" path="userPass" />
		<br>
		<label for="passCheck">비밀번호 변경 확인</label>
		<input type="password" maxlength="20" id="passCheck" />
		<br>
		<sform:label path="userName">이름</sform:label>
		<sform:input readonly="true" type="text" path="userName" value="${user.userName}" />
		<br>
		<sform:label path="phoneNum">전화번호</sform:label>
		<sform:input type="text" path="phoneNum" maxlength="11" value="${user.phoneNum}" />
		<br>
		<sform:label path="passQuestion">비밀번호 질문</sform:label>
		<sform:textarea path="passQuestion" maxlength="100" value="${user.passQuestion}" />
		<br>
		<sform:label path="passAnswer">질문 답</sform:label>
		<sform:input type="text" path="passAnswer" maxlength="20" value="${user.passAnswer}" />
		<br>
		<sform:button id="infoUpdate" type="submit">수정 하기</sform:button>

	</sform:form>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/validationcheck.js"></script>
<script src="<%=request.getContextPath() %>/js/myinfo.js"></script>

</html>