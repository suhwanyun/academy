<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/datepicker3.css" />
<title>공지 작성</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container cMargin">
		<form method="post" action="/lecture/lectureNotiAdd">
			<table class="table">
				<tr id="targetTR">
					<td><select id="noticeType" name="noticeType">
							<option value="notice">일반공지</option>
							<option value="test">시험 공지</option>
							<option value="report">과제 공지</option>
					</select></td>
				</tr>
				<tr>
					<td><input type="text" maxlength="30" id="noticeTitle"
						name="noticeTitle"></td>
				</tr>
				<tr>
					<td><textarea rows="13" cols="40" maxlength="1300"
							name="noticeContent" id="noticeContent" class="form-control"></textarea>
					</td>
				</tr>
				<tr align="right">
					<td><input type="submit" class="myButton" id="submitBtn"
						value="등록"> <input type="button" class="myButton"
						id="cancel" value="취소"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
<script type="text/javascript">
$("#submitBtn").click(function(event){
	if($("#noticeTitle").val()==""||$("#noticeContent").val()==""){
		event.preventDefault();
		alert("공지 제목과 내용을 확인해 주세요.");
	}else{
		if(confirm("공지를 등록 하시겠습니까?")){
		}else{
			event.preventDefault();
		}
	}
})

$("#cancel").click(function(event){
		$(location).attr('href', "/campus/campusMain");
	});
</script>
</html>