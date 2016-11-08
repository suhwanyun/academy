<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>마일리지 관리자 메인 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/manage/header.jsp" />
	
	<div class="container-fix upMargin cMargin">
		<table class="table table-bordered center">
		<colgroup>
		<col width= "30%">
		<col width= "30%">
		<col width= "40%">
		</colgroup>
			<tr>
				<th>상품이름</th>
				<th>상품가격</th>
				<th>사진 미리보기</th>
				</tr>
		
			<tr>
				<td><input type="text" ></td>
				<td><input type="text" ></td>
				
				<td rowspan=3>
				<img class="imgBoard" src="/upload/${postingData.postingPhoto}"  onerror="errorFun(this)"/></td>
					
					</tr>
				
			<tr>
				<th>상품 내용</th>
				<th>수량</th>
			</tr>
			<tr>
				<td><input type="text"></td>
				<td><input type="text" ></td>
			</tr>	
			
	</table>
		<div class="bRight">
			<input id="fileInput"
						type="file" name="uploadPhoto" accept="image/*" style="font-size:20px;" />
			<button class="myButton">취소</button>
		</div>
	</div>
	
		
	
	
</body>
</html>