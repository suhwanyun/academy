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
		<div id="wrap">
			<a href="<%=request.getContextPath()%>"><img
				class="circular--logo"
				src="<%=request.getContextPath()%>/images/logo.png" alt="" /></a>
			<h1 class="member">회원가입</h1>
			<div class="form9 pad">
				<sform:label path="userId">아이디</sform:label>
				<table>
					<tr>
						<td><sform:input placeholder="영문/숫자 4~10자" type="text"
								path="userId" /></td>
						<td><a id="trueorfalse" href="#"
							class="button alt falseButton"><span></span></a></td>
					</tr>
					<tr>
						<td></td>
						<td><sform:button id="duplicationCheckBtn">중복 확인</sform:button></td>
					</tr>
				</table>

				<sform:label path="userPass">비밀번호</sform:label>
				<sform:input placeholder="영문/숫자/특문 6~20자 "  type="password" path="userPass"/>
				<br> <label for="passCheck">비밀번호 확인</label> <input
					type="password"  id="passCheck" /><br>
				<sform:label path="userName">이름</sform:label>
				<sform:input placeholder="한글 2~5자 " type="text" path="userName"/>
				<br>
				<sform:label path="phoneNum">핸드폰 번호</sform:label>
				<sform:input placeholder="-없이 입력 " type="text" path="phoneNum"/>
				<br>
				<sform:label path="passQuestion">비밀번호 질문</sform:label>
				<sform:textarea placeholder="1자 이상 100자 이하 " path="passQuestion"/>
				<br>
				<sform:label path="passAnswer">질문 답</sform:label>
				<sform:input type="text" path="passAnswer"/>
				<br>
				<sform:button id="joinBtn" type="submit">가입 하기</sform:button>
			</div>
		</div>

	</sform:form>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/join.js"></script>
</html>