<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>마일리지 관리자 메인화면</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/manage/header.jsp" />
	<div class="container">

		<button id="mileageAddBtn">강의 등록</button>
		<table id="mileageDataTable" >

			<c:forEach items="${productList }" var="list" >
			<tr>
				<td><a href="/mileageManage/managejsp?productId=${list.productId }">${list.productName }</a></td>
				<td>${list.productCost }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<%-- 	<div id=searchDiv align="center">
		<!-- 검색을 위한 div -->
			<select id="searchSelect" >
				<option value="lectureId">강의 ID</option>
				<option value="professorName">교수 이름</option>
				<option value="lectureName">강의 명</option>
			</select>
			<input type="search" id="searchInput" value="${searchData }">
			<input type="button" id="searchBtn" value="검색">
		</div> --%>
		<div id="pageDiv" align="center">
		<!-- 페이지 처리를 위한 div입니다. -->
		</div>
</body>
<script type="text/javascript">
<c:url value="/lectureManage/page" var="page"/>
//페이징을 위한 번수
var nowPage = 1;
//페이지 요소 저장변수
var pageHtml;
//테이블 데이터 요소 저장변수
var dataHtml;
//페이지 요소 상황별 저장
function paging(){
	pageHtml = "";
		if(nowPage>1){
			pageHtml +=
			"<input type='button' onclick='firstPage()' value='첫 페이지'>"+
			"<input type='button' onclick='prevPage()' value='<<'>";
		}
		for(var i = nowPage-1; i>=1; i--){
			if(nowPage-4==i){
				break;
			}
			pageHtml +="<a onclick='movepage("+i+")'> ["+i+"] </a>";
		}
		pageHtml +="<a onclick='movepage("+nowPage+")'> _["+nowPage+"]_ </a>";
		for(var i = nowPage+1; i<=${ pageCount}; i++){
			if(nowPage+4==i){
				break;
			}
			pageHtml +="<a onclick='movepage("+i+")'> ["+i+"] </a>";
		} 
		if(nowPage<${ pageCount}){
			pageHtml +="<input type='button' onclick='nextPage()' value='>>'>"+
			"<input type='button' onclick='lastPage()' value='끝 페이지'>";
		}
}
function dataSetting(dataList){
	dataHtml="";
	for(var i=0; i<dataList.length; i++){
		dataHtml+=
		"<tr>"+
			"<td><a href='/lectureManage/managejsp?lectureId="+dataList[i].lectureId+"&lectureClass="+dataList[i].lectureClass +"'>"+
			dataList[i].lectureId +"</a></td>"+
			"<td>"+dataList[i].lectureName+"</td>"+
			"<td>"+dataList[i].professorName+"</td>"+
			"<td>"+dataList[i].lectureClass+"반</td>"+
			"<td>"+
				"<ul>";
					for(var j=0; j<dataList[i].lectureTimeList.length; j++){
						dataHtml+="<li>";
						switch(dataList[i].lectureTimeList[j].lectureWeek){
						case 1:
							dataHtml+=
								"<a href='/lectureManage/timeManagejsp?lectureTimeId="+dataList[i].lectureTimeList[j].lectureTimeId+"'>"+
								"<span>일요일</span></a>";
							break;
						case 2:
							dataHtml+=
								"<a href='/lectureManage/timeManagejsp?lectureTimeId="+dataList[i].lectureTimeList[j].lectureTimeId+"'>"+
								"<span>월요일</span></a>";
							break;
						case 3:
							dataHtml+=
								"<a href='/lectureManage/timeManagejsp?lectureTimeId="+dataList[i].lectureTimeList[j].lectureTimeId+"'>"+
								"<span>화요일</span></a>";
							break;
						case 4:
							dataHtml+=
								"<a href='/lectureManage/timeManagejsp?lectureTimeId="+dataList[i].lectureTimeList[j].lectureTimeId+"'>"+
								"<span>수요일</span></a>";
							break;
						case 5:
							dataHtml+=
								"<a href='/lectureManage/timeManagejsp?lectureTimeId="+dataList[i].lectureTimeList[j].lectureTimeId+"'>"+
								"<span>목요일</span></a>";
							break;
						case 6:
							dataHtml+=
								"<a href='/lectureManage/timeManagejsp?lectureTimeId="+dataList[i].lectureTimeList[j].lectureTimeId+"'>"+
								"<span>금요일</span></a>";
							break;
						case 7:
							dataHtml+=
								"<a href='/lectureManage/timeManagejsp?lectureTimeId="+dataList[i].lectureTimeList[j].lectureTimeId+"'>"+
								"<span>토요일</span></a>";
							break;
						}
						dataHtml+=
							"<span>"+dataList[i].lectureTimeList[j].lectureStart+"교시</span>"+
							"<span>~</span>"+
							"<span>"+dataList[i].lectureTimeList[j].lectureEnd+"교시</span>"+
							"<span>"+dataList[i].lectureTimeList[j].lecturePlace+"</span>";
						dataHtml+="</li>";
					}
							
					dataHtml+="<li>"+
					"<a href='/lectureManage/timeAddjsp?lectureId="+dataList[i].lectureId+"&lectureClass="+dataList[i].lectureClass+"'>추가 +</a>"+
					"</li></ul></td></tr>";
	}
}
$("document").ready(function(){
	paging();
	if(${ searchType=="professorName"}){
		$("#searchSelect option:eq(1)").attr("selected", "selected");
	}else if(${ searchType=="lectureName"}){
		$("#searchSelect option:eq(2)").attr("selected", "selected");
	}else{
		$("#searchSelect option:eq(0)").attr("selected", "selected");
	}
	
	
	$("#pageDiv").html(pageHtml);				
});
$("#mileageAddBtn").click(function(){
	$(location).attr('href', "/lectureManage/addjsp");
});
function movepage(index){
	nowPage = index;
	requesetPageFun();
}

function firstPage(){
	nowPage=1;
	requesetPageFun();
}
function prevPage(){
	if(nowPage>1){
		nowPage--;
	}else{
		nowPage=1;
	}
	requesetPageFun();
}
function nextPage(){
	if(nowPage< ${ pageCount}){
		nowPage++;
	}else{
		nowPage=${ pageCount};
	}
	requesetPageFun();
}
function lastPage(){
	nowPage=${pageCount};
	requesetPageFun();
}
function requesetPageFun(){
	$.ajax({
	      type : "get",
	      url : "${page}",
	      data : {
	    	  page : nowPage
	      },
	       success : function(result) {
	    	   $("#mileageDataTable").empty();
	    	   dataSetting(result);
	    	   $("#mileageDataTable").html(dataHtml);
	    	   $("#pageDiv").empty();
	    	   paging();
	    	   $("#pageDiv").html(pageHtml);
	      }
	   });
}
//검색 버튼
/* $("#searchBtn").click(function(){
	$(location).attr('href', "/lectureManage/search?searchType="+$("#searchSelect").val()+"&searchData="+$("#searchInput").val());
}); */
</script>
</html>