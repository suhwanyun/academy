<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의 시간 등록</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/manage/header.jsp" />
<sform:form modelAttribute="timeData" action="/lectureManage/timeAdd" method="post">
	<table>
		<tr>
			<th>강의 ID</th>
			<th>시작 교시</th>
			<th>종료 교시</th>
			<th>장소</th>
			<th>강의 요일</th>
			<th>임시 등록 날짜</th>
			<th></th>
		</tr>
		<tr>
			<td>
				<sform:input readonly="readonly" path="lectureId" value="${timeData.lectureId }"/>
				<sform:hidden path="lectureClass" value="${timeData.lectureClass }"/>
				<sform:hidden path="lectureTimeId" value="${timeData.lectureTimeId  }"/>
				<sform:hidden path="lectureWeek" value="0"/>
			</td>
			<td><sform:input type="number" path="lectureStart" value=""/></td>
			<td><sform:input type="number" path="lectureEnd" value=""/></td>
			<td><sform:input type="text" path="lecturePlace" value=""/></td>
			<td>
				<select id="selWeek">
					<option value="0" selected="selected">요일선택</option>
					<option value="1">일</option>
					<option value="2">월</option>
					<option value="3">화</option>
					<option value="4">수</option>
					<option value="5">목</option>
					<option value="6">금</option>
					<option value="7">토</option>
				</select>
			</td>
			<td><sform:input path="isTempDate" readonly="true" value="${timeData.isTempDate }"/></td>
<!--날짜 수정 여부<td><input class="form-control input-sm" type="text" id="wrtDtReg"readonly="readonly"></td>
			<td><input type="button" id="dateCancel" value="선택 취소"></td> -->
		</tr>
	</table>
	<input type="submit" id="registBtn" value="등록">
	<input type="button" id="cancelBtn" value="취소">
</sform:form>
</body>
<script type="text/javascript">
$("document").ready(function(){
	//날짜 수정 데이트픽커
	/* $('#wrtDtReg').datepicker({
		format: "yyyy-mm-dd",
		startView: 1,
		todayBtn:"linked",
		language: "kr",
		orientation: "top auto",
		keyboardNavigation: false,
		forceParse: false,
		autoclose: true,
		todayHighlight: true
		}); */
});
/*날짜 선택 관련 이벤트
$("#dateCancel").click(function(){
	$("#wrtDtReg").val("");
	$("#isTempDate").val($("#wrtDtReg").val());
});
$("#wrtDtReg").change(function(){
	$("#isTempDate").val($("#wrtDtReg").val());
}); */
$("#cancelBtn").click(function(){
	$(location).attr('href', "/lectureManage/main");
});
$("#selWeek").change(function(){
	$("#lectureWeek").val($("#selWeek").val());
})
$("#registBtn").click(function(event){
	if(lectureStartEndCheck($("#lectureStart").val(),$("#lectureEnd").val())&&
			lecutrePlaceCheck($("#lecturePlace").val())&&
			weekCheck($("#lectureWeek").val())
		){
		
	}else{
		alert("입력 값을 다시 한 번 확인해 주세요")
		event.preventDefault();
	}
})
function lectureStartEndCheck(s, e){
	var NUM_PATTERN = /^[0-9]{1}$/;	
	if(NUM_PATTERN.test(s)&&NUM_PATTERN.test(e)){
		if(s<e){
			return true;
		}
		return false;
	}else{
		return false;
	}
}
function lecutrePlaceCheck(x){
	if (x.length > 0 && x.length <= 15) {
		return true;
	} else {
		return false;
	}
}
function weekCheck(x){
	var NUM_PATTERN = /^[1-7]{1}$/;	
	if(NUM_PATTERN.test(x)){
		return true;
	}else{
		return false;
	}
}

</script>

</html>