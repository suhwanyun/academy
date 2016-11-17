<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>마일리지 관리자 메인화면</title>

<style>
	div {
		text-align: center;
	}

	#mileageDataTable {
	    display: inline-block;
	}

	#mileageDataTable tr{
		align:center;
		text-align: center;
	}
	#mileageAddBtn {
		float:right;
	}
</style>

</head>
<body>
	<jsp:include page="/WEB-INF/view/manage/header.jsp" />
	<div class="container">

		<button id="mileageAddBtn">물품 등록</button>
		<br>
		<br>
		<div align="center">
			<a id="searchType" href="#" onclick="changeType()" style="margin-right: 10px;">이름순</a>/
			<a id="searchOrder" href="#" onclick="changeOrder()">오름차순</a>
		</div>
		<hr>
		<table id="mileageDataTable" border="1">
			<tr>
				<c:forEach items="${productList }" var="list" >
					<td>
						<a href="/mileageManage/managejsp?productId=${list.productId }">
							<img src="/upload/preview_${list.productImgfile }" onerror="errorFun(this);"/>
						</a>
					</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${productList }" var="list" >
					<td><a href="/mileageManage/managejsp?productId=${list.productId }">${list.productName }</a></td>
				</c:forEach>
			<tr>
				<c:forEach items="${productList }" var="list" >
					<td>${list.productCost }</td>
				</c:forEach>
			</tr>
		</table>
	</div>
	
	<div id="pageDiv" align="center">
	<!-- 페이지 처리를 위한 div입니다. -->
	</div>
</body>

<script type="text/javascript">

var orderType = null;
var isAsc = null;

$(document).ready(function(){
	if(${!empty orderType}){
		orderType = "productCost";
		$("#searchType").text("가격순");
	} 
	if(${!empty isAsc}){
		isAsc = "false";
		$("#searchOrder").text("내림차순");
	} 
	
	pageCtrl(1);
});

/* 정렬방식 변경 */
function changeType(){
	
	if(${empty orderType}){
		orderType = "productCost";	
	} else {
		orderType = null;
	}
	
	order();
}
/* 정렬방향 변경 */
function changeOrder(){
	
	if(${empty isAsc}){
		isAsc = "false";		
	} else {
		isAsc = null;	
	}
	order();
}

/* 정렬 */
function order(){
	console.log("type:" + orderType + " asc:" + isAsc);
	var href = "/mileageManage/search";
	if(orderType != null && isAsc == null){
		href += "?orderType=" + orderType;
	} else if(isAsc != null && orderType == null){
		href += "?isAsc=" + isAsc;
	} else if(isAsc != null && orderType != null){
		href += "?orderType=" + orderType;
		href += "&isAsc=" + isAsc;
	}
	
	$(location).attr('href', href);
}

function pageCtrl(pages){
	var pageDiv = $("#pageDiv");
	var pageIdx = (pages-1) * 5 + 1;
	
	if(pageIdx > ${maxPage}){
		pageCtrl(pages-1);
	}
	pageDiv.html("");
	
	if(pages != 1){
		pageDiv.append($("<a href='#' onclick='pageCtrl(" + (pages-1) + ")'><i class='balloon test_3'></a>"));
	}
	
	for(; pageIdx <= ${maxPage}; pageIdx++){
		if(pageIdx == ${page}){
			pageDiv.append($("<a style='margin:10px; font-size:large; color:black;' href='/mileageManage/page?page=" + pageIdx + "'>" + pageIdx + "</a>"));
		} else {
			pageDiv.append($("<a style='margin:10px; font-size:large;' href='/mileageManage/page?page=" + pageIdx + "'>" + pageIdx + "</a>"));
		}
		if(pageIdx % 5 == 0 && pageIdx < ${maxPage}){
			pageDiv.append($("<a href='#' onclick='pageCtrl(" + (pages+1) + ")'><i class='balloon test_4'></a>"));
			break;
		}
	}
}

$("#mileageAddBtn").click(function(){
	$(location).attr('href', "/mileageManage/addjsp");
});
</script>
</html>