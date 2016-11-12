<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/datepicker3.css"/>
<title>강의 정보 알림등록</title>
</head>
<body><jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container cMargin">
		<sform:form method="post" action="/lecture/lectureTimeNotiAdd" modelAttribute="lectureTimeSetting">
			<table class="table">
				<tr id="targetTR">
					<td><sform:select path="noticeType">
							<option value="cancelDate" selected="selected">휴강</option>
							<option value="addDate">보강</option>
							<option value="changeDate" >강의 시간/장소 임시변경</option>
						</sform:select>
					</td>
				</tr>
				<tr>
					<td><input width="60%" class="form-control input-sm" type="text" id="wrtDtReg" readonly="readonly"> 
						<sform:input type="date" hidden="hidden" path="isTempDate" name="noticeDay"></sform:input>
					</td>
				</tr>
				<tr class='dateTR' hidden="hidden">
					<td>
						<sform:input type="text" path="lecturePlace" value="${lectureTimeSetting.lecturePlace }"></sform:input>
						<span>시작 교시</span>
						<sform:select path="lectureStart">
							<option value="1">1교시</option>
							<option value="2">2교시</option>
							<option value="3">3교시</option>
							<option value="4">4교시</option>
							<option value="5">5교시</option>
							<option value="6">6교시</option>
							<option value="7">7교시</option>
							<option value="8">8교시</option>
							<option value="9">9교시</option>
					</sform:select>
					<span>종료 교시</span>
					<sform:select path="lectureEnd">
							<option value="1">1교시</option>
							<option value="2">2교시</option>
							<option value="3">3교시</option>
							<option value="4">4교시</option>
							<option value="5">5교시</option>
							<option value="6">6교시</option>
							<option value="7">7교시</option>
							<option value="8">8교시</option>
							<option value="9">9교시</option>
					</sform:select>
					</td>
				</tr>
				<tr>
					<td>
						<sform:input type="text" maxlength="30" path="noticeTitle"></sform:input>
					</td>
				</tr>
				<tr>
					<td>
						<sform:textarea rows="13" cols="40" maxlength="1300" path="noticeContent" class="form-control"></sform:textarea>
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
$("#wrtDtReg").change(function(){
	$("#isTempDate").val($("#wrtDtReg").val());
}); 
$("#noticeType").change(function(){
	if($("#noticeType").val()=='changeDate'){
		$("#lectureStart option:eq(${lectureTimeSetting.lectureStart - 1})").prop("selected", true);
		$("#lectureEnd option:eq(${lectureTimeSetting.lectureEnd - 1})").prop("selected", true);
		$(".dateTR").removeAttr("hidden");
	}else{
		$(".dateTR").attr("hidden", "hidden");
	}
});
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