<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<title>상품 정보</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	
	<style>
	.img-circle{
		width:60%;
		margin-top:5%;
	}
	
	p {
		width:100%;
		font-size:15px;
	}
	
	.bold-p {
		margin-top:10px;
		font-weight: bold;
	}
	
	.small-p {
		margin-top:15px;
		margin-bottom:20px;
		font-size:12px;
	}
	</style>
	
	<div class="container text-center">
		<c:if test="${empty isMine }">
			<p>보유 마일리지:&nbsp;${user.userMileage }</p>
		</c:if>
		
		<img class="img-circle" src="/upload/${productData.productImgfile }" alt="" />
		<p class="bold-p">${productData.productName}</p>
		<p>${productData.productContent}</p>	
		<c:choose>
			<c:when test="${empty isMine }">
				<p class="small-p">-${productData.productCost} 마일리지-</p>
				<button class="myButton" onclick="buyProduct()">구매하기</button>
				<button class="myButton" onclick="notBuyProduct()">돌아가기</button>
			</c:when>
			<c:otherwise>
				<p class="small-p">-${productData.productRemain }개 보유 중-</p>
				<button class="myButton" onclick="useProduct()">사용하기</button>
				<button class="myButton" onclick="notUseProduct()">돌아가기</button>
			</c:otherwise>
		</c:choose>			
	</div>

<jsp:include page="/WEB-INF/view/footer/footer.jsp" />
</body>
<script>
	function buyProduct(id){
		$(location).attr("href", "/mileage/productBuy?productId=" + ${productData.productId});		
	}
	
	function notBuyProduct(id){
		$(location).attr("href", "/mileageMain");		
	}
	
	function useProduct(id){
		if(confirm("정말 사용하시겠습니까?")){	
			$(location).attr("href", "/mileage/productUse?productId=" + ${productData.productId});	
		}
	}
	
	function notUseProduct(id){
		$(location).attr("href", "/mileage/myMileageMain");		
	}
</script>
</html>