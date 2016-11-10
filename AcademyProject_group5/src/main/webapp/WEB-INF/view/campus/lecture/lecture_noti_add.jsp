<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/datepicker3.css"/>
<title>공지 작성</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container cMargin">
		<form method="post" action="/lecture/lectureNotiAdd"
			class="form-horizontal">
			<table class="table">
				<tr id="targetTR">
					<td >
						<select id="noticeType" name="noticeType">
							<option value="notice">일반공지</option>
							<option value="test">시험</option>
							<option value="report">과제</option>
							<option value="changeDate">시간변경</option>
						</select>
					</td>
				</tr>
				<tr class='dateTR' hidden="hidden">
					<td><input class="form-control input-sm" type="text" id="wrtDtReg"readonly="readonly"></td>
					<td>
						<input type="button" id="dateCancel" value="선택 취소">
						<input type="date" hidden="hidden" id="isTempDate" name="noticeDay">
					</td>
				<tr>
				<tr class='dateTR' hidden="hidden">
					<td><input class="form-control input-sm" type="text" id="wrtDtReg"readonly="readonly"></td>
					<td>
						<input type="button" id="dateCancel" value="선택 취소">
						<input type="date" hidden="hidden" id="isTempDate" name="noticeDay">
					</td>
				<tr>
					<td>
						<input type="text" maxlength="30" id="noticeTitle" name="noticeTitle">
					</td>
				</tr>
				<tr>
					<td>
						<textarea rows="13" cols="40" maxlength="1300"
							name="noticeContent" id="noticeContent" class="form-control"></textarea>
					</td>
				</tr>
				<tr align="right">
					<td>
						<input type="submit" class="myButton" id="submitBtn" value="등록">
						<input type="button" class="myButton" id="cancel" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/bootstrap-datepicker.kr.js"></script>
<script type="text/javascript">
$("document").ready(function(){
	$('#wrtDtReg').datepicker({
		format: "yyyy-mm-dd",
		startView: 1,
		todayBtn:"linked",
		language: "kr",
		orientation: "top auto",
		keyboardNavigation: false,
		forceParse: false,
		autoclose: true,
		todayHighlight: true
		}); 
});
$("#dateCancel").click(function(){
	$("#wrtDtReg").val("");
	$("#isTempDate").val($("#wrtDtReg").val());
});
$("#wrtDtReg").change(function(){
	$("#isTempDate").val($("#wrtDtReg").val());
}); 
$("#noticeType").change(function(){
	if($("#noticeType").val()=='changeDate'){
		$(".dateTR").removeAttr("hidden");
	}else{
		$(".dateTR").attr("hidden", "hidden");
	}
});
</script>

</html>