<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
<title>마일리지</title>
</head>
<body>
<div id="wrap">
<a href="<%=request.getContextPath() %>"><img 
						class="circular--logo"
						src="<%=request.getContextPath() %>/images/logo.png" alt="" /></a>
						<h1 class="member">마일리지 물품 구매</h1>
	<div id="main">
		<div class="inner">
			<div class="columns">
				<a href="<%=request.getContextPath()%>/mileage/confirm"><img
					class="circular--square"
					src="<%=request.getContextPath()%>/images/mil01.png" alt="" /></a> 
					<a href="<%=request.getContextPath()%>/mileage/confirm" 
					class="button big alt"><span>아메리카노</span></a><br>
				
					<input type="checkbox" id="check"> <br>
				
				<a href="<%=request.getContextPath()%>/mileage/confirm"><img
					class="circular--square"
					src="<%=request.getContextPath()%>/images/mil02.png" alt="" /></a>
				<a href="<%=request.getContextPath()%>/mileage/confirm" 
					class="button big alt"><span>CU 모바일 상품권</span></a><br>
				
					<input type="checkbox" id="check"> <br>
			</div>
			
			<div class="columns">
				<a href="<%=request.getContextPath()%>/mileage/confirm"><img
					class="circular--square"
					src="<%=request.getContextPath()%>/images/mil03.png" alt="" /></a> 
				<a href="<%=request.getContextPath()%>/mileage/confirm" 
					class="button big alt"><span>허쉬초콜릿드링크</span></a><br>
				
					<input type="checkbox" id="check"> <br>
					
					<a href="<%=request.getContextPath()%>/mileage/confirm"><img
					class="circular--square"
					src="<%=request.getContextPath()%>/images/mil04.png" alt="" /></a>
				<a href="<%=request.getContextPath()%>/mileage/confirm" 
					class="button big alt"><span>ABC초콜릿</span></a><br>
				
					<input type="checkbox" id="check"> <br>
			</div>
		</div>
	</div>
	</div>
</body>
</html>