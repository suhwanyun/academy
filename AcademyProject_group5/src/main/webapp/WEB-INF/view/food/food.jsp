<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/css/main.css" />
<link rel="stylesheet"
	href="/css/font-awesome.css" />
<title>식사게시판</title>
<script type="text/javascript">
function errorFun(e){
	e.src="/upload/notFoundImg.png";
}

</script>
</head>
<body>
	<!-- 테이블 시작 -->
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
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
					<tr >
						<td><select id="sort">
								<option value="time">날짜 정렬</option>
								<option value="recommend">추천수 정렬</option>

						</select></td>
						<td></td>
						<td></td>
						<td><button id="writeBtn" class="myButton">글쓰기</button></td>
					</tr>
					<tr>

						<td><select id="serchType">
								<option selected="selected" value="user">작성자</option>
								<option value="title">제목</option>
								<option value="content">내용</option>
								<option value="all">제목+내용</option>

						</select></td>
						<td colspan="2"><input type="search" id="searchInput"></td>
						<td><input type="button" id="searchBtn" class="myButton"
							value="찾기"></td>
					</tr>

				</table>

				<table class="list_table">
					<colgroup>
						<col width="20%">
						<col width="20%">
						<col width="10%">
						<col width="50%">
					</colgroup>
					<c:if test="${!empty mostRecommendData}">
					<tr class="mostRecommend tableData">

						<td rowspan="2"><img class="imgBoard"
							src="/upload/preview_${mostRecommendData.postingPhoto}"
							onerror="errorFun(this);"/></td>
						<td colspan="3"><a
							href="/postingInfo?postingId=${mostRecommendData.postingId}">${mostRecommendData.postingTitle }</a></td>

					</tr>
					<tr class="mostRecommend tableData">
						<td>${mostRecommendData.userId }</td>
						<td>${mostRecommendData.postingRecommend }</td>
						<td>${mostRecommendData.postingTime }</td>
					</tr>
					</c:if>
					<c:forEach items="${postingDataList }" var="list">

						<tr class="tableData">

							<td rowspan="2"><img class="imgBoard" onerror="errorFun(this);"
								src="/upload/preview_${list.postingPhoto}" /></td>
							<td colspan="3"><a
								href="/postingInfo?postingId=${list.postingId}">${list.postingTitle }</a></td>

						</tr>
						<tr class="tableData">
							<td>${list.userId }</td>
							<td>${list.postingRecommend }</td>
							<td>${list.postingTime }</td>

						</tr>

					</c:forEach>
					<tr id="beforeLocation">
						<td colspan="3"><button id="moreBtn" class="myButton">더보기</button></td>
						<td><button class="myButton">맨 위로</button></td>
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
var mostRecommend;
$("document").ready(function(){
	mostRecommend = "<tr class='mostRecommend tableData'>";
	mostRecommend += $(".mostRecommend").html();
	mostRecommend += "</tr><tr class='mostRecommend tableData'>";
	mostRecommend += $(".mostRecommend + tr").html();
	mostRecommend += "</tr>";
});
var pageIndex = 1;

$("#searchBtn").click(function(){
	pageIndex = 1;
	$.ajax({
			type : "get",
			url : "${postingSearch}",
			data : {
				searchType : $("#serchType").val(),
				searchData : $("#searchInput").val(),
				orderData : $("#sort").val()
			},
			success : function(result) {
				 $(".tableData").remove();
				 $(".mostRecommend").remove();
				 if($("#searchInput").val().length < 1){
					 $("#beforeLocation").before(mostRecommend);
				 }
				 if(result.length==0){
					 alert("검색 결과가 없습니다.");
				 }else{						
				 $(result).each(function(index,item){
						$("#beforeLocation").before(
								 $("<tr class='tableData'><td rowspan='2'><img class='imgBoard' onerror='errorFun(this);'"+
										 "src=/upload/preview_"+
							               item.postingPhoto +"/></td><td colspan='3'>"+
							               "<a href='/postingInfo?postingId="+item.postingId+"'>"+
							               item.postingTitle+"</a></td></tr><tr class='tableData'><td>"+
							               item.userId+"</td><td>"+
							               item.postingRecommend+"</td><td>"+
							               item.postingTime+"</td></tr>"));
					});
				 }
			 } 
		
			
		
		});
	});

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
	            		$("<tr class='tableData'><td rowspan='2'><img class='imgBoard' onerror='errorFun(this);'"+
	            				"src=/upload/preview_"+
					               item.postingPhoto +"/></td><td colspan='3'>"+
					               "<a href='/postingInfo?postingId="+item.postingId+"'>"+
					               item.postingTitle+"</a></td></tr><tr class='tableData'><td>"+
					               item.userId+"</td><td>"+
					               item.postingRecommend+"</td><td>"+
					               item.postingTime+"</td></tr>"));
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
$("#writeBtn").click(function(){
	$(location).attr('href', "/write/foodjsp");
});
</script>
</html>