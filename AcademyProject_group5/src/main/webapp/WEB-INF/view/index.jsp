<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>My Campus Manager</title>

</head>

<body>
	<c:if test="${!empty msg}">
		<script>
			alert('${msg}');
		</script>
		<c:remove var="msg" />
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
<div class="footer">
<jsp:include page="/WEB-INF/view/footer/footer.jsp" /> 
</div>
</div>
</body>

</html>