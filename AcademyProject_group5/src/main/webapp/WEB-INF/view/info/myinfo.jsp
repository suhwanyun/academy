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
<title>회원가입</title>
</head>
<body>

	<sform:form method="post" action="join" modelAttribute="userData">
		<sform:label path="userId">아이디</sform:label>
		<sform:input readonly="true" type="text" path="userId" value="${user.userId}"/>
		<sform:label path="userPass">비밀번호</sform:label>
		<sform:input type="password" path="userPass" />
		<br>
		<label for="passCheck">비밀번호 확인</label>
		<input type="password" id="passCheck" />
		<br>
		<sform:label path="userName">이름</sform:label>
		<sform:input type="text" path="userName" value="${user.userName}"/>
		<br>
		<sform:label path="phoneNum">전화번호</sform:label>
		<sform:input type="number" path="phoneNum" value="${user.userName}"/>
		<br>
		<sform:label path="passQuestion">비밀번호 질문</sform:label>
		<sform:textarea path="passQuestion" value="${user.passQuestion}"/>
		<br>
		<sform:label path="passAnswer">질문 답</sform:label>
		<sform:input type="text" path="passAnswer" value="${user.passAnswer}"/>
		<br>
		<sform:button id="join" type="submit">수정 하기</sform:button>
		</div>
		</div>
		</div>
		</div>
	</sform:form>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

</script>
</html>