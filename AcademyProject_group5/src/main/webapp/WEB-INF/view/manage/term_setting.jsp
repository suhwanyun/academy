<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/bootstrap-theme.css" />
<link rel="stylesheet" href="/css/bootstrap.css" />
<link rel="stylesheet" href="/css/datepicker3.css" />
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/bootstrap-datepicker.kr.js"></script>
<title>학기 설정</title>
</head>
<body>
		<div
			style="text-align: center; position: absolute; top: 0; left: 0; right: 0; bottom: 0; margin: auto auto; width: 300px; height: 300px;">
			<form action="/lectureManage/termSetting" method="post">
				<span>시작 날짜</span> <input type="date" hidden="hidden" id="termStart"
					name="termStart"> <input type="text" id="startDate" style="text-align: center;"
					readonly="readonly"> <br><br><span>종료 날짜</span> <input type="date"
					hidden="hidden" id="termEnd" name="termEnd"> <input
					type="text" style="text-align: center;" id="endDate" readonly="readonly">
					<br><br>
				<button type="submit" id="termSettingBtn">설정</button>
			</form>
		</div>
</body>
<script type="text/javascript">
	$("document").ready(function() {
		//날짜 수정 데이트픽커
		$('#startDate').datepicker({
			format : "yyyy-mm-dd",
			startView : 1,
			todayBtn : "linked",
			language : "kr",
			orientation : "top auto",
			keyboardNavigation : false,
			forceParse : false,
			autoclose : true,
			todayHighlight : true
		});
		$('#endDate').datepicker({
			format : "yyyy-mm-dd",
			startView : 1,
			todayBtn : "linked",
			language : "kr",
			orientation : "top auto",
			keyboardNavigation : false,
			forceParse : false,
			autoclose : true,
			todayHighlight : true
		});
	});
	$("#startDate").change(function() {
		$("#termStart").val($("#startDate").val());
	})
	$("#endDate").change(function() {
		$("#termEnd").val($("#endDate").val());
	})
	$("#termSettingBtn").click(function(event) {
		if (confirm("학기설정은 한 번만 설정가능합니다.\n 설정 하시겠습니까?")) {
			if ($("#termStart").val() == "" || $("#termEnd").val() == "") {
				event.preventDefault();
				alert("학기 설정을 확인하세요")
			}
		} else {
			event.preventDefault();
		}

	})
</script>
</html>