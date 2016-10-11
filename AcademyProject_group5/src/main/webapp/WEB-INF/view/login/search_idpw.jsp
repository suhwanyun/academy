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
<script src="<%=request.getContextPath() %>/js/search_idpw.js"></script>
</html>