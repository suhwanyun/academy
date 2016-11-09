<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<title>전체 강의 목록</title>
</head>
<body>
<div class="container text-center">
	<table class="table-condensed">
		<tr>
			<td><select id="serchType" >
					<option selected="selected" value="">선택</option>
					<option value="lecture">강의 이름</option>
					<option value="professor">교수 이름</option>
			</select></td>
			<td><input type="search" id="serachInput" /></td>
			<td>
				<button id="searchBtn" class="myButton">검색</button>
			</td>
		</tr>
	</table>

	
		
			<table class="table">

					<tr>
						<th>강의 이름</th>
						<th>교수 이름</th>
						<th>강의 분반</th>
					</tr>
					<c:forEach items="${lectureList }" var="list">
						<tr class = 'tableData' onclick = "movePage(${list.lectureId}, ${list.lectureClass})">
							<td>${list.lectureName}</td>
							<td>${list.professorName}</td>
							<td>${list.lectureClass}</td>
						</tr>
					</c:forEach>
				<tr id="beforeLectureLocation">
					<td colspan="2" ><button id="moreBtn" class="myButton">더보기</button></td>
               		<td><button class="myButton">맨 위로</button></td>
				</tr>
			</table>
	  </div>
</body>


<script type="text/javascript">
function movePage(el, el2){
	$(location).attr("href", "/lecture/lectureInfo?lectureId="+el+"&lectureClass="+el2);
}
var pageIndex = 1;
<c:url value="/campus/lectureListMore" var="nextlectureList"/>
<c:url value="/campus/lectureListSearch" var="lectureListSearch"/>
function dataSetting(list){
$(list).each(function(index,item) {
				$(
					"#beforeLectureLocation").before(
						$("<tr class = 'tableData' onclick='movePage("+item.lectureId+","+item.lectureClass+")'><td>"
							+ item.lectureName
							+ "</td>"
							+ "<td>"
							+ item.professorName
							+ "</td>"
							+ "<td>"
							+ item.lectureClass
							+ "</td></tr>"));
			});
}
$("#searchBtn").click(function(){
						$.ajax({
							type : "get",
							url : "${lectureListSearch}",
							data : {
								searchType : $("#serchType").val(),
								searchData : $("#serachInput").val()
							},
							success : function(result){
								$(".tableData").empty();
								pageIndex = 1;
								if (result.length == 0) {
									alert("검색 결과가 없습니다.");
								} else {
									dataSetting(result);
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
								if (result.length < 1) {
									alert("더 이상 목록이 없습니다.");
								}else{
									dataSetting(result);
								}
							},
							error : function() {
								alert("요청에 실패 하였습니다.");
							}
						});
					});
</script>
</html>