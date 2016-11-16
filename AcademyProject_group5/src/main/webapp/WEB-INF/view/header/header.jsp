<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap-theme.css" />
<link rel="stylesheet" href="/css/bootstrap.css" />
<link rel="stylesheet" href="/css/bootstrap-toggle.min.css" />
<link rel="stylesheet" href="/css/custom.css" />

<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/remoteBtn.js"></script>
<script src="/js/bootstrap-toggle.min.js"></script>

<!-- 이미지 캐싱 금지 -->
<% 
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache"); 
 response.setDateHeader("Expires",0);
%>
</head>

 <div class="text-center">


	<button type="button" class="btn btn-danger btn-header">
		<span class="glyphicon glyphicon-home text-center" ></span>
	</button>
	<div id="collapse1" class="collapse jumbotron">
		<div class="container">
			<c:choose>
				<c:when test="${!empty user }">
					<a href="/main" class="btn-mcm"><span>MCM</span></a>
					<h3>${user.userName}님 어서오세요</h3>
					<a href="/noti/notiSettingjsp" class="btn btn-danger"><span>알림</span></a>
					<a href="/info/myinfo" class="btn btn-danger"><span>내
							정보관리</span></a>
					<br>
					<br>

					<a href="/mileage/myMileageMain" class="btn btn-danger"><span>보관함</span></a>
					<a href="/logout" class="btn btn-danger"><span>로그아웃</span></a>



				</c:when>

			<c:otherwise>
				
						<a href="/main"  class="btn-mcm" ><span>My Campus Manager</span></a><br><br>
						 <a
							href="/loginjsp"
							class="btn btn-danger"><span>로그인</span></a> <a
							href="/joinjsp"
							class="btn btn-danger"><span>회원가입</span></a>
			
			</c:otherwise>
		</c:choose>
</div>	</div>

</div> 
</body>
<script>
 $(".btn-header").click(function(){
    $("#collapse1").collapse('toggle');
}); 
</script>
</html>