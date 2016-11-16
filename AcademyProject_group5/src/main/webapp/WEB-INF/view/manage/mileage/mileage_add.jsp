<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/manage/header.jsp" />

<title>물품 등록</title>
</head>
<body>
	<form method="post" action="/mileageManage/add" enctype="multipart/form-data"
			class="form-horizontal">
	<input type="text" name="productName" placeholder="물품명"/>
	<input type="text" name="productContent" placeholder="물품설명"/>
	<input type="number" name="productCost" placeholder="가격(마일리지)"/>
	<input id="fileInput" type="file" name="uploadPhoto" accept="image/*" />
	</form>
</body>
</html>