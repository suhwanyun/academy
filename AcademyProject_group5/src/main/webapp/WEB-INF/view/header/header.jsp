<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="/css/bootstrap-theme.css"/>
   <link rel="stylesheet" href="/css/bootstrap.css"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<jsp:include page="../message.jsp" />
<div class="text-center">
<button type="button" class="btn btn-danger btn-header">
          <span class="glyphicon glyphicon-home text-center"></span> 
        </button>
 <div id="collapse1" class="collapse jumbotron">
 <div class="container" >
 <c:choose>
			<c:when test="${!empty user }">
						<a href="/main"  class="btn-mcm"><span>MCM</span></a>
						<h3>${user.userName}님어서오세요</h3>
						<a href="/noti/notiSettingList"  class="btn btn-danger"><span>알림</span></a> 
						<a href="/info/myinfo" class="btn btn-danger"><span>내 정보관리</span></a><br><br>
						
						<a href="#"  class="btn btn-danger"><span>${user.userMileage} m</span></a>
						<a href="/logout"  class="btn btn-danger"><span>로그아웃</span></a>
					
				
				</c:when>
			<c:otherwise>
				
						<a href="/main"  class="btn-mcm"><span>MCM</span></a><br>
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
$(document).ready(function(){
    $(".btn-header").click(function(){
        $("#collapse1").collapse('toggle');
    });

});

</script>

</html>