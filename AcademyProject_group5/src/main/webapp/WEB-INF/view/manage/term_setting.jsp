<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/bootstrap-theme.css"/>
<link rel="stylesheet" href="/css/bootstrap.css"/>
<link rel="stylesheet" href="/css/datepicker3.css"/>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/bootstrap-datepicker.kr.js"></script>
<title>학기 설정</title>
</head>
<body>
	<div class="containerM center text-center Lmargin">
		<span>시작 날짜</span>
		<input class="form-control input-sm" type="text" id="startDate"readonly="readonly">
	    <input type="button" id="StartDateCancel" value="선택 취소">
	    <span>종료 날짜</span>
	    <input class="form-control input-sm" type="text" id="endDate"readonly="readonly">
	    <input type="button" id="endDateCancel" value="선택 취소">
    </div>
			
</body>
<script type="text/javascript">
$("document").ready(function(){
	//날짜 수정 데이트픽커
	$('#startDate').datepicker({
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
$("#StartDateCancel").click(function(){
	$("#startDate").val("");
});
</script>
</html>