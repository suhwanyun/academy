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
                     <td><sform:input type="text" path="userId" /></td>
                     <td><a id="trueorfalse" href="#" class="button alt trueButton"><span></span></a>
                     </td></tr>
                     <tr>
                     <td></td>
                     <td><sform:button id="duplicationCheckBtn">중복 확인</sform:button></td>
                     </tr>
                  </table>
                     
                     <sform:label path="userPass">비밀번호</sform:label>
                     <sform:input type="password" path="userPass" /><br>
                     <label for="passCheck">비밀번호 확인</label> 
                     <input type="password" id="passCheck" /><br>
                     <sform:label path="userName">이름</sform:label>
                     <sform:input type="text" path="userName" />
                     <br>
                     <sform:label path="phoneNum">전화번호</sform:label>
                     <sform:input type="number" path="phoneNum" />
                     <br>
                     <sform:label path="passQuestion">비밀번호 질문</sform:label>
                     <sform:textarea path="passQuestion" />
                     <br>
                     <sform:label path="passAnswer">질문 답</sform:label>
                     <sform:input type="text" path="passAnswer" />
                     <br>
                     <sform:button id="join" type="submit">가입 하기</sform:button>
                  </div>
               </div>
    
	</sform:form>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	var result = "false";
	var idcheck=passcheck=namecheck=phonecheck = true;
		$("#joinBtn").click(function(){
				if (idcheck && passcheck && namecheck && phonecheck
						&& questioncheck && ansercheck == true) {
					alert("cc");
				} else {
					alert(idcheck, passcheck, namecheck, phonecheck,
							questioncheck, ansercheck);
					event.preventDefault();
				}

			});
	$("#duplicationCheckBtn").click(
			function(event) {
				event.preventDefault();
				$.ajax({
					type : "get",
					url : "findUser",
					data : {
						userId : $("#userId").val()
					},
					success : function(res) {

						if (res == "true") {
							alert("사용가능한 ID 입니다.");
							result = res;
						} else {
							alert("이미 있는 ID 입니다.");
							result = res;
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