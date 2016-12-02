<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<title>강의 관리자 메인화면</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/manage/header.jsp" />
	<div class="container">

		<button id="lectureAddBtn">강의 등록</button>
		<table id="lectureDataTable" border="1">
			<colgroup>
					<col width="15%">
					<col width="15%">
					<col width="15%">
					<col width="15%">
					<col width="40%">
			</colgroup>
			
			<tr>
			<th>강의ID</th>
			<th>강의명</th>
			<th>분반</th>
			<th>담당교수</th>
			<th>강의시간</th>
			</tr>
			
			<c:forEach items="${lectureList }" var="list" >
			<tr align="center">
				<td><a href="/lectureManage/managejsp?lectureId=${list.lectureId }&lectureClass=${list.lectureClass }">${list.lectureId }</a></td>
				<td>${list.lectureName }</td>
				<td>${list.lectureClass }반</td>
				<td>${list.professorName }</td>
				
				<td>
					<ul>
						<c:forEach items="${list.lectureTimeList }" var="timeList" >
							<li>
								<a href="/lectureManage/timeManagejsp?lectureTimeId=${timeList.lectureTimeId }">
									<c:choose>
										<c:when test="${timeList.lectureWeek == 1}">
												<span>일요일</span>
											
										</c:when>
										<c:when test="${timeList.lectureWeek == 2 }">
												<span>월요일</span>
											
										</c:when>
										<c:when test="${timeList.lectureWeek == 3 }">
												<span>화요일</span>
											
										</c:when>
										<c:when test="${timeList.lectureWeek == 4 }">
												<span>수요일</span>
											
										</c:when>
										<c:when test="${timeList.lectureWeek == 5 }">
												<span>목요일</span>
											
										</c:when>
										<c:when test="${timeList.lectureWeek == 6 }">
												<span>금요일</span>
											
										</c:when>
										<c:when test="${timeList.lectureWeek == 7 }">
												<span>토요일</span>
											
										</c:when>
										<c:otherwise>
											<span>error</span>
										</c:otherwise>
									</c:choose>
									<span>${timeList.lectureStart }교시</span>
									<span>~</span>
									<span>${timeList.lectureEnd }교시</span>
									<span>${timeList.lecturePlace }</span>
								</a>
							</li>
						</c:forEach>
						<li>
							<a href="/lectureManage/timeAddjsp?lectureId=${list.lectureId }&lectureClass=${list.lectureClass}" >추가 +</a>
						</li>
					</ul>
				</td>
			</tr>
	
			</c:forEach>
		</table>
	</div>
		<div id=searchDiv align="center">
		<!-- 검색을 위한 div -->
			<select id="searchSelect" >
				<option value="lectureId">강의 ID</option>
				<option value="professorName">교수 이름</option>
				<option value="lectureName">강의 명</option>
			</select>
			<input type="search" id="searchInput" value="${searchData }">
			<input type="button" id="searchBtn" value="검색">
		</div>
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
	dataHtml="<colgroup><col width='15%'><col width='15%'><col width='15%'><col width='15%'><col width='40%'></colgroup>";
	dataHtml+="<tr><th>강의ID</th><th>강의명</th><th>분반</th><th>담당교수</th><th>강의시간</th></tr>";
	
	for(var i=0; i<dataList.length; i++){
		dataHtml+=
		"<tr style='text-align:center;'>"+
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
$("#lectureAddBtn").click(function(){
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
	    	   $("#lectureDataTable").empty();
	    	   dataSetting(result);
	    	   $("#lectureDataTable").html(dataHtml);
	    	   $("#pageDiv").empty();
	    	   paging();
	    	   $("#pageDiv").html(pageHtml);
	      }
	   });
}
//검색 버튼
$("#searchBtn").click(function(){
	$(location).attr('href', "/lectureManage/search?searchType="+$("#searchSelect").val()+"&searchData="+$("#searchInput").val());
});
</script>
</html>