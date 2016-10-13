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
		<tr>
			<td><select>
					<option selected="selected">선택</option>
					<option>강의 이름</option>
					<option>교수 이름</option>
			</select></td>
			<td><input type="search" /></td>
			<td><button>검색</button></td>
		</tr>
	</table>
	<table>
		<tr>
			<th>강의 이름</th>
			<th>교수 이름</th>
			<th>강의 분반</th>
		</tr>
		<c:forEach items="${lectureList }" var="list">

			<tr>
				<td>${list.lectureName}</td>
				<td>${list.professorName}</td>
				<td>${list.lectureClass}</td>
			</tr>
		</c:forEach>
		<tr id="beforeLocation">
			<td colspan="3" align="center" ><button id="moreBtn" >더보기</button></td>
			<td align="right"><button>맨 위로</button></td>
		</tr>
	</table>

</body>

<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var pageIndex = 1;
<c:url value="/campus/lectureList" var="nextlectureList"/>

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
						$("#beforeLocation").before(
							$("<tr><td>"+item.lectureName+"</td>"+
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