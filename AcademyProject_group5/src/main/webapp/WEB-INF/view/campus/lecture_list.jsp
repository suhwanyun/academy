<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<title>전체 강의 목록</title>
</head>
<body>
	<table>
		<tr class="tableData">
			<td><select id="serchType">
					<option selected="selected" value="">선택</option>
					<option value="lecture">강의 이름</option>
					<option value="professor">교수 이름</option>
			</select></td>
			<td><input type="search" id="serachInput" /></td>
			<td><button id="searchBtn" class="myButton">검색</button></td>

		</tr>
	</table>
	<table class="list_table">
		<thead>
		<tr>
			<th>강의 이름</th>
			<th>교수 이름</th>
			<th>강의 분반</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${lectureList }" var="list">
			<tr class="tableData">
				<td><a href="/lecture/lectureInfo?lectureId=${list.lectureId}&lectureClass=${list.lectureClass}">${list.lectureName}</a></td>
				<td>${list.professorName}</td>
				<td>${list.lectureClass}</td>
			</tr>
		</c:forEach>
		</tbody>
		<tr id="beforeLectureLocation" class="tableData">
			<td colspan="2" align="center"><button id="moreBtn" class="myButton">더보기</button></td>
			<td><button class="myButton">맨 위로</button></td>
		</tr>
	</table>

</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var pageIndex = 1;
<c:url value="/campus/lectureList" var="nextlectureList"/>
<c:url value="/campus/lectureListSearch" var="lectureListSearch"/>
	$("#searchBtn").click(function(){
		$.ajax({
			type : "get",
			url : "${lectureListSearch}",
			data : {
				searchType : $("#serchType").val(),
				searchData : $("#serachInput").val()
			},
			 success : function(result) {
				 $(".tableData").remove();
				 pageIndex = 1;
				 if(result.length==0){
					 alert("검색 결과가 없습니다.");
				 }else{
				 $(result).each(function(index,item){
						$("#beforeLectureLocation").before(
							$("<tr class = 'tableData'><td>"+
							"<a href="+
							"/lecture/lectureInfo?lectureId="+item.lectureId+"&lectureClass="+item.lectureClass+
							">"+item.lectureName+"</a></td>"+
							  "<td>"+item.professorName+"</td>"+
							  "<td>"+item.lectureClass+"</td></tr>"));
					});
				 }
			 } 
		
		});
	});
	$("#moreBtn").click(function(){
				
			pageIndex++;
			
			$.ajax({
				type : "get",
				url : "${nextlectureList}",
				data : {
					page : pageIndex
				},
				 success : function(result) {
					var itemCount = 0;
					$(result).each(function(index,item){
						itemCount++;
						$("#beforeLectureLocation").before(
							$("<tr class = 'tableData'><td>"+
									"<a href="+
									"/lecture/lectureInfo?lectureId="+item.lectureId+"&lectureClass="+item.lectureClass+
									">"+item.lectureName+"</a></td>"+
									"<td>"+item.professorName+"</td>"+
							  "<td>"+item.lectureClass+"</td></tr>"));
					});
					
					if(itemCount == 0){
						alert("더 이상 목록이 없습니다.");
					}
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:"
							+ error);
				}
			});
		});
</script>
</html>