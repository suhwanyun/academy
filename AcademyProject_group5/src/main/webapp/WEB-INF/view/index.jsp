<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>My Campus Manager</title>

<%
	String browser = request.getHeader("User-Agent");	// 브라우저 구해오기
	boolean result = false;
	if(browser.indexOf("Android") > 0) {              // 안드로이드로 접속
		result = true;
	}else if(browser.indexOf("iPhone") > 0) {        // 아이폰으로 접속
		result = true;
	}
	if(result == true) {
		session.setAttribute("isPhone", "true"); 	// 스마트폰 접속 플래그
	} 	
%>
</head>

<body>
	<c:if test="${!empty isManage }">
		<c:remove var="isManage"/>
	</c:if>
	
	<jsp:include page="/WEB-INF/view/header/header.jsp" />

	<!-- Main -->

 
        <div class="container">
        	
        <div class="text-center">
           <a href="/campus/campusMain"><img
				class="img-circle img-thumbnail" src="/images/pic01.png" alt=""
				/></a>
		
		

            <a href="/foodMain" style="margin-left: 10%;"><img class="img-circle img-thumbnail"
				src="/images/pic02.png" alt="" /></a> 
		
			
       
    </div>

		  <div class="text-center">
          <a href="/mileageMain" ><img class="img-circle img-thumbnail"
			src="/images/pic05.png" alt="" /></a>
				</div>
       
      
        <div class="text-center">
           <a href="/playMain"><img class="img-circle img-thumbnail"
			src="/images/pic03.png" alt=""/></a>
	
           <a href="/placeMain" style="margin-left: 10%;"><img
			class="img-circle img-thumbnail" src="/images/pic04.png" alt="" /></a>

        </div>

</div>
<div class="footer">
<jsp:include page="/WEB-INF/view/footer/footer.jsp" /> 
</div>
</body>
</html>