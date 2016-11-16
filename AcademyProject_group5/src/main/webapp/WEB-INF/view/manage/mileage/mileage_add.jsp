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
	
	<input id="fileInput" type="file" name="uploadPhoto" accept="image/*" />
	</form>
</body>
</html>