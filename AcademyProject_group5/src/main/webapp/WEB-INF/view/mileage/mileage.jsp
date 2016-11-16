<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<title>마일리지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	
	<style>
	.img-circle{
		text-align:center;
		width:80%;
	}
	</style>
	<div class="container">
		<div class="text-center">
			<c:choose>
				<c:when test="${empty isMine}">
					<h1>물품 구매</h1>
					<p class="mileage_help">원하시는 상품을 선택하세요</p>
				</c:when>
				<c:otherwise>
					<h1>보관함</h1>
					<p class="mileage_help">사용하실 상품을 선택하세요</p>
				</c:otherwise>
			</c:choose>
			<c:if test="${!empty user }">
				<p>보유 마일리지:&nbsp;${user.userMileage }</p>
			</c:if>

			<c:forEach items="${productList }" var="list" varStatus="status" step="2">
				<a href="#" onclick="clickProduct(${list.productId})"
				   class="btn-lg col-xs-6">
					<img class="img-circle img-thumbnail" src="/upload/preview_${list.productImgfile }" alt="" />
					<br>${list.productName }
				</a>
				<c:if test="${productList.size() gt status.count}">
					<a href="#" onclick="clickProduct(${productList[status.count].productId})"
					   class="btn-lg col-xs-6">
						<img class="img-circle img-thumbnail" src="/upload/preview_${productList[status.count].productImgfile }" alt="" />
						<br>${productList[status.count].productName }
					</a>
				</c:if>
			</c:forEach>
		</div>
	</div>

<jsp:include page="/WEB-INF/view/footer/footer.jsp" />
</body>
<script>
	$(document).ready(function(){
		if(${productList.size() == 0}){
			$(".mileage_help").text("등록된 상품이 없습니다.");
		} 
		// 상품이 하나일 땐 가운데 정렬
		else if(${productList.size() == 1}) {
			$(".col-xs-6").css("margin-left", "25%");
		}
	});
	function clickProduct(id){
		if(${empty isMine}){
			$(location).attr("href", "/mileage/productBuyjsp?productId=" + id);		
		} else {
			$(location).attr("href", "/mileage/productUsejsp?productId=" + id);		
		}
	}
</script>
</html>