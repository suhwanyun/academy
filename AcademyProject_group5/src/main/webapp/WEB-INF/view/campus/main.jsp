<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<jsp:include page="/WEB-INF/view/header/header.jsp"></jsp:include>

	<div class="container">
		<div class="btn-group btn-group-justified">
			<a href="#" onclick="mylectureBtn()" class="btn btn-danger">내 강의</a>
			<a href="#" onclick="notilistBtn()" class="btn btn-danger">전체 공지</a>
			<a href="#" onclick="lecturelistBtn()" class="btn btn-danger">강의
				선택</a> <a href="#" onclick="schedule()" class="btn btn-danger">내 시간표</a>
		</div>



		<div id="includejsp" style="overflow-x: scroll; width: 100%; height: 100%"></div>
	</div>
</body>

<script type="text/javascript">
	<c:url value="/campus/notiList" var="notiList"/>
	<c:url value="/campus/selectedLectureList" var="selectedLectureList"/>
	<c:url value="/campus/lectureList" var="lectureList"/>
	<c:url value="/campus/schedulejsp" var="schedule"/>
$("document").ready(function(){
	var nowTab = "${nowTab}";
	<c:remove var="nowTab"/>
	switch(nowTab){
	case "selectedLectureList":
		mylectureBtn();
		break;
	case "notification":
		notilistBtn();
		break;
	case "lectureList":
		lecturelistBtn();
		break;
	default:
		schedule();
		break;
	}
	
});
function mylectureBtn(){
	$.ajax({ 
		type : "get",
		url : "${selectedLectureList}",
		success : function(result) {
			$("#includejsp").html(result);
		},
		/* error : function(){
			alert("요청을 실패 하였습니다.");
		} */
		error:function(request,status,error){
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	       }

	});
}
function notilistBtn(){
	$.ajax({
		type : "get",
		url : "${notiList}",
		success : function(result) {
			$("#includejsp").html(result);
		},
		error : function(){
			alert("요청을 실패 하였습니다.");
		}
	});
}

function lecturelistBtn(){
		$.ajax({
			type : "get",
			url : "${lectureList}",
			success : function(result) {
				$("#includejsp").html(result);
			},
			error : function(){
				alert("요청을 실패 하였습니다.");
			}
		});

	}
function schedule(){
		$.ajax({
			type : "get",
			url : "${schedule}",
			success : function(result) {
				$("#includejsp").html(result);
			},
			error : function(){
				alert("요청을 실패 하였습니다.");
			}
		});

	}
</script>
</html>