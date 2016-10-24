<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
<title>학업페이지</title>
</head>
<body>
	<table class="table">
		<thead>
		<tr>
		<td>
		<jsp:include page="../header/header.jsp" ></jsp:include>
		</td>
		</tr>
		</thead>
	</table>
	
	
		<div class="container">
			<div class="btn-group btn-group-justified">
  				  <a href="#" onclick="mylectureBtn()" class="btn btn-danger">내 강의목록</a>
   				 <a href="#" onclick="notilistBtn()" class="btn btn-danger">전체 공지목록</a>
    				<a href="#" onclick="lecturelistBtn()" class="btn btn-danger">강의 선택</a>
    				<a href="#" onclick="schedule()" class="btn btn-danger">내 시간표</a>
 			</div>

	
			
               <div id="incluedjsp" ></div>
       
    </div>
	
	
	</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	<c:url value="/campus/notiList" var="notiList"/>
	<c:url value="/lecture/selectedLectureList" var="selectedLectureList"/>
	<c:url value="/campus/lectureListJsp" var="lectureList"/>
	<c:url value="/campus/schedule" var="schedule"/>
function mylectureBtn(){
	$.ajax({ 
		type : "get",
		url : "${selectedLectureList}",
		success : function(result) {
			$("#incluedjsp").html(result);
		}
	});
}
function notilistBtn(){
	$.ajax({
		type : "get",
		url : "${notiList}",
		success : function(result) {
			$("#incluedjsp").html(result);
		}
	});
}

function lecturelistBtn(){
		$.ajax({
			type : "get",
			url : "${lectureList}",
			success : function(result) {
				$("#incluedjsp").html(result);
			}
		});

	}
function schedule(){
		$.ajax({
			type : "get",
			url : "${schedule}",
			success : function(result) {
				$("#incluedjsp").html(result);
			}
		});

	}
</script>
</html>