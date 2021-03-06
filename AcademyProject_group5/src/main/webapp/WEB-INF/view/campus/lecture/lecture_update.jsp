<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/datepicker3.css" />
<title>${lectureName }알림등록</title>
</head>
<body><jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container cMargin">
		<sform:form method="get" action="/lecture/lectureTimeNotiAdd"
			modelAttribute="lectureTimeSetting">
			<table class="table">
				<tr id="targetTR" align="right">
					<td colspan="4">
						<sform:select path="noticeType" style="direction:rtl;">
							<option value="cancelDate">휴강</option>
							<option value="addDate">보강</option>
							<option value="changeDate" selected="selected">시간/장소 임시변경</option>
						</sform:select> <br>
					<span class='dateTR' id='targetTime'>${targetTimeStr}</span>
					<span class='dateTR' id='targetTimeWithoutDate' hidden="hidden">${targetTimeStrWithoutDate}</span></td>
				</tr>
				<tr>
					<td><span>날짜</span></td>
					<td colspan="3"><input width="30%"
						class="form-control input-sm" type="text" id="wrtDtReg"
						readonly="readonly"> <sform:input type="date"
							hidden="hidden" path="isTempDate" name="noticeDay"></sform:input>
					</td>
				</tr>
				<tr class='dateTR'>
					<td>
						<span>시작</span>
					</td>
					<td>
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
					</td>
					<td align="right">
						<span>종료</span>
					</td>
					<td align="right">
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
				<tr class='dateTR'>
					<td><span>장소</span></td>
					<td colspan="3"><sform:input type="text"
							path="lecturePlace" value="${lectureTimeSetting.lecturePlace }"></sform:input>
					</td>
				</tr>
			</table>
			<!-- 공지 내용 -->
			<table class="table">
				<tr>
					<td colspan="4">
					<sform:input type="text" maxlength="30" path="noticeTitle" placeholder="   공지 제목을 입력하세요"></sform:input>
					</td>
				</tr>
				<tr>
					<td colspan="4"><sform:textarea rows="13" cols="40" maxlength="1300"
							path="noticeContent" class="form-control" placeholder="공지 내용을 입력하세요"></sform:textarea> <!-- 히든 sform태그 -->
						<sform:input type="number" hidden="hidden" path="lectureTimeId"
							value="${lectureTimeSetting.lectureTimeId }"></sform:input> <sform:input
							type="number" hidden="hidden" path="lectureId"
							value="${lectureTimeSetting.lectureId }"></sform:input> <sform:input
							type="number" hidden="hidden" path="lectureClass"
							value="${lectureTimeSetting.lectureClass }"></sform:input></td>
				</tr>
				<tr align="right">
					<td colspan="4"><input type="submit" class="myButton" id="submitBtn"
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
	$("#lectureStart option:eq(${lectureTimeSetting.lectureStart - 1})").prop("selected", true);
	$("#lectureEnd option:eq(${lectureTimeSetting.lectureEnd - 1})").prop("selected", true);
});
$("#wrtDtReg").change(function(){
	$("#isTempDate").val($("#wrtDtReg").val());
}); 
$("#noticeType").change(function(){
	if($("#noticeType").val()=='changeDate'
			|| $("#noticeType").val()=='addDate'){
		$("#lectureStart option:eq(${lectureTimeSetting.lectureStart - 1})").prop("selected", true);
		$("#lectureEnd option:eq(${lectureTimeSetting.lectureEnd - 1})").prop("selected", true);
		$(".dateTR").removeAttr("hidden");
		
		if($("#noticeType").val()=='changeDate'){
			$("#targetTimeWithoutDate").attr("hidden", "hidden");
		} else {
			$("#targetTime").attr("hidden", "hidden");
		}
		
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

$("#cancel").click(function(event){
		$(location).attr('href', "/campus/campusMain");
	});
</script>
</html>