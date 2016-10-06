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
			<sform:button id="duplicationCheckBtn">중복 확인</sform:button><img class="circular--square" src="<%=request.getContextPath() %>/images/btn1.png" alt="" /><br>
			<sform:label  path="userPass">비밀번호</sform:label>
			<sform:input type="password" path="userPass"/><br>
			<label for="passCheck">비밀번호 확인</label>
			<input type="password" id="passCheck"/><br>
			<sform:label path="userName">이름</sform:label>
			<sform:input type="text" path="userName"/><br>
			<sform:label path="phoneNum">전화번호</sform:label>
			<sform:input type="number" path="phoneNum"/><br>
			<sform:label  path="passQuestion">비밀번호 질문</sform:label>
			<sform:textarea path="passQuestion"/><br>
			<sform:label path="passAnswer">질문 답</sform:label>
			<sform:input type="text" path="passAnswer"/><br>
			<sform:button id="submit" type="submit">가입 하기</sform:button>
		</fieldset>
	</sform:form>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js" ></script>
<script type="text/javascript">
	var result = "false";
	$("#submit").click(function(){
		event.preventDefault();
		$("form").vaildate();
	});
	$("#duplicationCheckBtn").click(function(event){
		event.preventDefault();
		$.ajax({
			type :"get",
			url : "findUser",
			data : {
				userId: $("#userId").val()
			},
			success : function(res){
				
				if(res=="true"){
					alert("사용가능한 ID 입니다.");
					result = res;
				}else{
					alert("이미 있는 ID 입니다.");
					result = res;
				}
				
				
			},
			error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
		});
		
	});
</script>
</html>