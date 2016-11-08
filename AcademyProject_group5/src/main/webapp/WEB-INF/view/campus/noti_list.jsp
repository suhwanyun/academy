<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<title>공지 목록</title>
</head>
<body>
	<div class="container">
		<table id="dataTable" class="table">
			<colgroup>
				<col width="30">
				<col width="40">
				<col width="30">
			</colgroup>
			<tr>
				<th>강의 이름</th>
				<th>공지 제목</th>
				<th>날 짜</th>
			</tr>
			<c:forEach items="${noticeList }" var="list">
				<tr align="center">
					<td>${list.lectureName }</td>
					<td>${list.noticeTitle }</td>
					<td>${list.noticeTime }</td>
				</tr>
			</c:forEach>
		</table>
		<div align="center">
			<button id="moreBtn" class="myButton size8mar">더보기</button>
		</div>
	</div>
</body>
<script type="text/javascript">
<c:url value="/campus/notiListMore" var="notiListMore"/>
 $("#moreBtn").click(function(){
	$.ajax({
		type: "get",
		url: "${notiListMore}",
		success : function(result){
			$("#dataTable").append(dataSetting(result));
		}, error : function(result){
			alert("요청에 실패 하였습니다.");
		}
	});
 });
 function dataSetting(listData){
	 var html = "";
	 $(listData).each(function(index, item){
		 html += "<tr align='center'>"+
				 	 "<td>"+item.lectureName+"</td>"+
					 "<td>"+item.noticeTitle+"</td>"+
					 "<td>"+item.noticeTime+"</td>"+
				 "</tr>";
	 });
	 return html;
 }
</script>
</html>