<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
<title>식사게시판</title>
</head>
<body>
	<!-- 테이블 시작 -->
	<jsp:include page="../../header/header.jsp" />
	<div id="wrap">
		<div class="upmargin">
			<h1 class="member">식사 게시판</h1>
			<div class="form9 pad">
				<table>
					<colgroup>
						<col width="30%">
						<col width="20%">
						<col width="25%">
						<col width="25%">
					</colgroup>
					<tr>
						<td><select id="array" title="select array">
								<option selected="selected">정렬</option>
								<option>추천수 정렬</option>
								<option>날짜 정렬</option>
						</select></td>
						<td></td>
						<td></td>
						<td><a href="<%=request.getContextPath()%>/write/foodjsp">글쓰기</a></td>
					</tr>
					<tr>
						<td><select id="select" title="select select">
								<option selected="selected">작성자</option>
								<option>제목</option>
								<option>내용</option>
								<option>제목+내용</option>
						</select></td>
						<td colspan="2"><input type="search" id="search"></td>
						<td><input type="button" class="boardBtn" value="찾기"></td>
					</tr>

				</table>
			
				<table style="text-align: center;" class="imgTable">
					<colgroup>
						<col width="20%">
						<col width="35%">
						<col width="10%">
						<col width="35%">
					</colgroup>
					
					<c:forEach items="${postingDataList }" var="list">
						<tr>

							<td rowspan="2"><img class="imgBoard" src="<%=request.getContextPath()%>/upload/${list.postingPhoto}"  /></td>
							<td colspan="3">${list.postingTitle }</td>

						</tr>
						<tr> 
							<td>${list.userId }</td>
							<td>${list.postingRecommand }</td>
							<td>${list.postingTime }</td>
						</tr>
						</c:forEach>
						<tr id="beforeLocation">
							<td colspan="4" align="center" id="moreBtn"><button>더 보기</button></td>
						</tr>
					
				</table>
				
			</div>
		</div>
	</div>
	<!-- 테이블 종료 -->
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
<c:url value="/postingList" var="postingList"/>
<c:url value="/postingSearch" var="postingSearch"/>
var pageIndex = 1;
$("#moreBtn").click(function(){
	
	pageIndex++;
	$.ajax({
		type : "get",
		url : "${postingList}",
		data : {
			page : pageIndex
		},
		 success : function(result) {
			var itemCount = 0;
			$(result).each(function(index,item){
				itemCount++;
				$("#beforeLocation").before(
					$("<tr>"+
					"<td rowspan='2'><img class='imgBoard'"+
					"src=<%=request.getContextPath()%>/upload/"+item.postingPhoto +"/></td>"+
					"<td colspan='3'>"+item.postingTitle+"</td>"+
				"</tr>"+
				"<tr>"+ 
					"<td>"+item.userId+"</td>"+
					"<td>"+item.postingRecommand+"</td>"+
					"<td>"+item.postingTime+"</td>"+
				"</tr>"));
				console.log(item.postingPhoto);
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