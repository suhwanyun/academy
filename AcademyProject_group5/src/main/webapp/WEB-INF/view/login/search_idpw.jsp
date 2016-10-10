<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.Map"%>
<html>
<head>
<title>login form</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/main.css" />
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
					<label for="userName">이름</label><input type="text" id="userName">
					<label for="phoneNum">전화번호</label><input type="text" id="phoneNum"><br>
					<input type="button" value="확인" id="idCheck" style="margin-left: 45%"><br>
</div>
</div>
		</div>
		<div class="form8">
			<div class="form6">
				<div class="form3 pad">
					<h1 class="member">Password찾기</h1>
					<label for="userId">아이디</label><input type="text" id="userId"><br>
					<label for="passQuestion">비밀번호 질문</label><textarea id = passQuestion> </textarea> <br>
					<label for="passAnswer">질문 답</label><input type="text" id="passAnswer"><br>
						<input type="button" value="확인" id="passCheck"  style="margin-left: 45%">
				</div>
</div>
		</div>
			
	</div>
</form>
</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	$("#idCheck").click(function() {
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
	
	});
	$("#passCheck").click(function(){
		$.ajax({
			type : "get",
			url : "findPass",
			data : {
				userName : $("#userName").val(),
				phoneNum : $("#phoneNum").val()
			},
			success : function(res) {

				if (res.length>2) {
					alert("임시 패스워드는 : ["+res+"]입니다.");
					
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
	});
	$("#userId").change(function(){
		$.ajax({
			type : "get",
			url : "findQuestion",
			data : {
				userName : $("#userId").val()
			},
			success : function(res) {

				if (res!=null) {
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
	});
</script>
</html>