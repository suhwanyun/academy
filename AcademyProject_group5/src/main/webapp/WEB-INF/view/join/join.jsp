<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<sform:form method="post" action="join" modelAttribute="UserData">
		<fieldset>
			<sform:label path="userId">아이디</sform:label>
			<sform:input type="text" path="userId"/>
			<sform:button id="duplicationCheckBtn">중복 확인</sform:button><br>
			<sform:label  path="userPass">비밀번호</sform:label>
			<sform:input type="text" path="userPass"/><br>
			<label for="passCheck">비밀번호 확인</label>
			<input type="text" id="passCheck"/><br>
			<sform:label path="userName">이름</sform:label>
			<sform:input type="text" path="userName"/><br>
			<sform:label path="phoneNum">전화번호</sform:label>
			<sform:input type="number" path="phoneNum"/><br>
			<sform:label  path="passQuestion">비밀번호 질문</sform:label>
			<sform:input type="text" path="passQuestion"/><br>
			<sform:label path="passAnswer">질문 답</sform:label>
			<sform:input type="text" path="passAnswer"/><br>
			<sform:button type="submit">가입 하기</sform:button>
		</fieldset>
	</sform:form>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	var result = false;
	$("#duplicationCheckBtn").click(function(event){
		event.preventDefault();
		$.ajax({
			type :"get",
			url : "findUser",
			data : {
				userId: $("#userId").val(),
			},
			success : function(res){
				result = $(res).val();
				alert("성공"+result);
			},
			error : function(xhr, status, error){
				alert(error);
			}
		});
		
	});
</script>
</html>