<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<html>
<head>
<title>ID, Pass 찾기</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />

</head>

<form>
	<!--  로그인하기전 화면 -->

	<div id="wrap">
		<a href="<%=request.getContextPath()%>"><img
			class="circular--logo"
			src="<%=request.getContextPath()%>/images/logo.png" alt="" /></a>

		<div class="form7">
			<div class="form6">
				<div class="form3 pad">

					<h1 class="member">ID 찾기</h1>
					<label for="userName">이름</label><input placeholder="한글 2~5자 " type="text" id="userName">
					<label for="phoneNum">전화번호</label><input placeholder="-없이 입력 "
						type="text" id="phoneNum"><br> <input type="button"
						value="확인" id="idCheck" style="margin-left: 45%"><br>
				</div>
			</div>
		</div>
		<div class="form8">
			<div class="form6">
				<div class="form3 pad">
					<h1 class="member">Password찾기</h1>
					<label for="userId">아이디</label><input placeholder="영문/숫자 4~10자" type="text" id="userId"><br>
					<label for="passQuestion">비밀번호 질문</label>
					<textarea id="passQuestion" readonly="readonly"> </textarea>
					<br> <label for="passAnswer">질문 답</label><input placeholder="1자 이상 20 이하 " type="text"
						id="passAnswer"><br>
						<input id ="res" readonly="readonly" type="text" value="임시비밀번호 :"
						id="passAnswer"><br> <input type="button" value="확인"
						id="passCheck" style="margin-left: 45%">
				</div>
			</div>
		</div>

	</div>
</form>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
function phoneCheck(x){
	var PHONE_PATTERN = /^[0-9]{10,11}$/;
	if(PHONE_PATTERN.test(x)){
		return true;
	}else{
		return false;
	}
}
function nameCheck(x){
	var NAME_PATTERN = /^[가-힣]{2,5}$/;
	if(NAME_PATTERN.test(x)){
		return true;
	}else{
		return false;
	}
}
function idCheck(x){
	var ID_PATTERN = /^[a-z][a-z0-9_$@#]{3,11}$/i;
	if(ID_PATTERN.test(x)){
		return true;
	}else{
		return false;
	}
}
function anserCheck(x){
	if(x.length>0&&x.length<=20){
		return true;
	}else{
		return false;
	}
}
	
	$("#idCheck").click(function() {
		if(phoneCheck($("#phoneNum").val())&&nameCheck($("#userName").val())){
			$.ajax({
				type : "get",
				url : "findId",
				data : {
					userName : $("#userName").val(),
					phoneNum : $("#phoneNum").val()
				},
				success : function(res) {
					if (res.length>2) {
						alert("귀하의 아이디는 : "+res);
						
					} else {
						alert("조회된 아이디가 없습니다.");
					}

				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:"
							+ error);
				}
			});
		}else{
			alert("이름, 전화번호 양식이 틀렸습니다.");
		}
		
	});
	$("#passCheck").click(function(){
		if(anserCheck($("#passAnswer").val())){
			$.ajax({
				type : "get",
				url : "findPass",
				data : {
					userId : $("#userId").val(),
					passAnswer : $("#passAnswer").val()
				},
				success : function(res) {
					if (res.length>2) {
						$("#res").val("임시 비밀번호 : [ "+res+" ]");
					} else {
						alert("아이디나 비밀번호 답을 확인하세요.");
					}

				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:"
							+ error);
				}
			});
		}else{
			alert("질문 답 양식이 틀렸습니다.");
		}
		
	});
	$("#userId").change(function(){
		if(idCheck($("#userId").val())){
			$.ajax({
				type : "get",
				url : "findQuestion",
				data : {
					userId : $("#userId").val()
				},
				success : function(res) {
					if (res.length>1) {
						$("#passQuestion").val(res)
						
					} else {
						$("#passQuestion").val("조회된 질문이 없습니다. 관리자에게 문의 하세요");
					}

				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:"
							+ error);
				}
			});
		}else{
			alert("ID 양식이 틀렸습니다.");
		}
	});
</script>
</html>