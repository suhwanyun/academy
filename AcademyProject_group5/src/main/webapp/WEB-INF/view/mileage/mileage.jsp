<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>마일리지</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/header/header.jsp" />
	 <div class="container">
	 <div class="text-center">
			<h1>물품 구매</h1>
			<p>원하는 제품의 이름을 선택하세요</p>
	
		
		<a href="/mileage/confirm"><img
				class="img-circle img-thumbnail" 
				src="/images/mil02.png" alt="" /></a>
			<a href="/mileage/confirm"><img
				class="img-circle img-thumbnail imgMar"
				src="/images/mil01.png" alt=""/></a> 
		
		
			<a href="/mileage/confirm"
				class="btn btn-link btn-lg col-xs-6" ><span>CU 모바일 상품권</span></a>
			<a href="/mileage/confirm"
				class="btn btn-link btn-lg col-xs-6" ><span>아메리카노</span></a> 
			
		
	

		
			<a href="/mileage/confirm"><img
				class="img-circle img-thumbnail" 
				src="/images/mil03.png" alt="" /></a> <a
				href="/mileage/confirm"><img
				class="img-circle img-thumbnail imgMar" 
				src="/images/mil04.png" alt="" /></a>
		
		
		<button type="button" class="btn btn-link btn-lg col-xs-6">
			<span>허쉬초콜릿드링크</span></button>
			<a href="/mileage/confirm"
				class="btn btn-link btn-lg col-xs-6"><span>ABC초콜릿</span></a>
	
	
	<a href="/mileage/confirm"><img
				class="img-circle img-thumbnail" 
				src="/images/mil05.png" alt="" /></a> <a
				href="/mileage/confirm"><img
				class="img-circle img-thumbnail imgMar" 
				src="/images/mil06.png" alt="" /></a>
		
		
		
			<a href="/mileage/confirm"
				class="btn btn-link btn-lg col-xs-6" ><span>초코우유</span></a>
			<a href="/mileage/confirm"
				class="btn btn-link btn-lg col-xs-6" ><span>비타500</span></a>
		</div>

<div style="margin-top:5%;"> 
               <button id="confirmBtn" class="myButton size8mar">구매 확정</button>
               <button class="myButton size4">취소</button>
            </div>
</div>

	
</body>
</html>