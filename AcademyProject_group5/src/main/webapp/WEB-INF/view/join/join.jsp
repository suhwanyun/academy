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
				<sform:input placeholder="-없이 입력 " type="number" path="phoneNum"/>
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
<script type="text/javascript">

	
	var isChanged = true;
	function idCheck(x){
		var ID_PATTERN = /^[a-z][a-z0-9_$@#]{3,11}$/i;
		if(ID_PATTERN.test(x)){
			$("#userId").attr('class', 'true');
			return true;
		}else{
			$("#userId").attr('class', 'false');
			return false;
		}
			
	}
	function passCheck(x){
		var PASS_PATTERN = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/;
		if(PASS_PATTERN.test(x)){
			$("#userPass").attr('class', 'true');
			return true;
		}else
			$("#userPass").attr('class', 'false');
			return false;
	}
	function nameCheck(x){
		var NAME_PATTERN = /^[가-힣]{2,5}$/;
		if(NAME_PATTERN.test(x)){
			$("#userName").attr('class', 'true');
			return true;
		}else
			$("#userName").attr('class', 'false');
			return false;
	}
	function phoneCheck(x){
		var PHONE_PATTERN = /^[0-9]{10,11}$/;
		if(PHONE_PATTERN.test(x)){
			$("#phoneNum").attr('class', 'true');
			return true;
		}else
			$("#phoneNum").attr('class', 'false');
			return false;
	}
	function questionCheck(x){
		if(x.length>0&&x.length<=100){
			$("#passQuestion").attr('class', 'true');
			return true;
		}else
			$("#passQuestion").attr('class', 'false');
			return false;
	} 
	function anserCheck(x){
		if(x.length>0&&x.length<=20){
			$("#passAnswer").attr('class', 'true');
			return true;
		}else
			$("#passAnswer").attr('class', 'false');
			return false;
	}
		$("#joinBtn").click(function(event){
			
			
				if (isChanged&&idCheck($("#userId").val()) && passCheck($("#userPass").val()) 
						&& nameCheck($("#userName").val()) && phoneCheck($("#phoneNum").val())
						&& questionCheck($("#passQuestion").val()) && anserCheck($("#passAnswer").val()) == true) {
					alert("가입 성공");
				} else {
					event.preventDefault();
					alert("가입 실패");
				}

			});
		
	$("#userId").change(function(){
		isChanged = false;
		$("#trueorfalse").attr('class', 'button alt falseButton');
		if(idCheck($("#userId").val())){
			$("#userId").attr('class', 'true');
		}else{
			$("#userId").attr('class', 'false');
		}
	});
	$("#userPass").change(function(){
		isChanged = false;
		if(passCheck($("#userPass").val())){
			if($("#passCheck").val()==$("#userPass").val()){
				$("#userPass").attr('class', 'true');
				isChanged = true;
				}else{
					$("#passCheck").attr('class', 'false');
				}
		}else{
			$("#userPass").attr('class', 'false');
		}	
	});
	$("#passCheck").change(function(){
		isChanged = false;
		if(passCheck($("#userPass").val())&&$("#passCheck").val()==$("#userPass").val()){
			$("#passCheck").attr('class', 'true');
			isChanged = true;
		}else{
			$("#passCheck").attr('class', 'false');
		}
			
	});
	$("#userName").change(function(){
		isChanged = false;
		if(nameCheck($("#userName").val())){
			$("#userName").attr('class', 'true');
			isChanged = true;
		}else{
			$("#userName").attr('class', 'false');	
		}
			
	});
	$("#phoneNum").change(function(){
		isChanged = false;
		if(phoneCheck($("#phoneNum").val())){
			$("#phoneNum").attr('class', 'true');
			isChanged = true;
		}else{
			$("#phoneNum").attr('class', 'false');
		}
			
	});
	$("#passQuestion").change(function(){
		isChanged = false;
		if(questionCheck($("#passQuestion").val())){
			$("#passQuestion").attr('class', 'true');
			isChanged = true;
		}else{
			$("#passQuestion").attr('class', 'false');
		}
	});
	$("#passAnswer").change(function(){
		isChanged = false;
		if(anserCheck($("#passAnswer").val())){
			$("#passAnswer").attr('class', 'true');
			isChanged = true;
		}else{
			$("#passAnswer").attr('class', 'false');
		}
	});
	//아이디 중복확인
	$("#duplicationCheckBtn").click(
			function(event) {
				event.preventDefault();
				if(idCheck($("#userId").val())){
					$.ajax({
						type : "get",
						url : "findUser",
						data : {
							userId : $("#userId").val()
						},
						success : function(res) {

							if (res == "true") {
								alert("사용가능한 ID 입니다.");
								isChanged = true;
								$("#trueorfalse").attr('class', 'button alt trueButton');
								
							} else {
								alert("이미 있는 ID 입니다.");
								$("#trueorfalse").attr('class', 'button alt falseButton');
							}

						},
						error : function(request, status, error) {
							alert("code:" + request.status + "\n" + "message:"
									+ request.responseText + "\n" + "error:"
									+ error);
						}
					});
				}else{
					alert("아이디를 확인하세요");
				}
				

			});
</script>
</html>