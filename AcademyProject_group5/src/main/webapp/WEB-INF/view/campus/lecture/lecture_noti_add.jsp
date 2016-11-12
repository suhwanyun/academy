<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/datepicker3.css" />
<title>공지 작성</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container cMargin">
		<sform:form method="post" action="/lecture/lectureNotiAdd" modelAttribute="nextLectureTime">
			<table class="table">
				<tr id="targetTR">
					<td><select id="noticeType" name="noticeType">
							<option value="notice">일반공지</option>
							<option value="test">시험 공지</option>
							<option value="report">과제 공지</option>
					</select></td>
				</tr>
				<!-- <tr class='dateTR' hidden="hidden">
					<td><input width="60%" class="form-control input-sm"
						type="text" id="wrtDtReg" readonly="readonly"> <input
						type="button" id="dateCancel" value="선택 취소"> <input
						type="date" hidden="hidden" id="isTempDate" name="noticeDay">
					</td></tr>
					 -->
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
		</sform:form>
	</div>

</body>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/bootstrap-datepicker.kr.js"></script>
<script type="text/javascript">
/* $("document").ready(function(){
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
}); */
/* $("#dateCancel").click(function(){
	$("#wrtDtReg").val("");
	$("#isTempDate").val($("#wrtDtReg").val());
});
$("#wrtDtReg").change(function(){
	$("#isTempDate").val($("#wrtDtReg").val());
}); 
$("#noticeType").change(function(){
	$("#wrtDtReg").val("");
	$("#isTempDate").val("");
	if($("#noticeType").val()!='notice'){
		$(".dateTR").removeAttr("hidden");
	}else{
		$(".dateTR").attr("hidden", "hidden");
	}
}); */
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
</script>

</html>