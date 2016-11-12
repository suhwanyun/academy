<%@ page language="java" import="java.sql.*,java.io.*" contentType="text/html;charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>학업 게시판</title>
</head>

<body>

		<div class="container text-center">
			<table class="table-condensed">
               <tr>
                  <td><select id="searchType">
                        <option selected="selected" value="user">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="all">제목+내용</option>

                  </select></td>
                  <td><input type="search" id="searchInput" ></td>
                  <td>
					<input type="button" id="searchBtn" class="bRight myButton"
                     value="찾기">
				  </td>
               </tr>
               <tr >
               	  <td>
                  </td>
                  <td>
                  </td>
                  <td>
                  	<c:if test="${isPresident == 'Y' }">
                  	 <button id="writeNoticeBtn" class="myButton">공지 쓰기</button>
                    </c:if>
                     <button id="writeBtn" class="myButton bRight">글쓰기</button>
                  </td>
               </tr>
              
            </table>
            <table class="list_table">
               <colgroup>
                  <col width="20%">
                  <col width="20%">
                  <col width="10%">
                  <col width="50%">
               </colgroup>
               <c:forEach items="${postingDataList }" var="list">
                  <tr class="tableData" onclick="movePage(${list.postingId})">
                     <td rowspan="2"><img class="imgBoard"
                        onerror="errorFun(this);"
                        src="/upload/preview_${list.postingPhoto}" /></td>
                     <td colspan="3">${list.postingTitle }</td>
                  </tr>
                  <tr class="tableData" onclick="movePage(${list.postingId})">
                     <td>${list.userId }</td>
                     <td></td>
                     <td>${list.postingTime }</td>
                  </tr>
               </c:forEach>
             <tr id="beforeLocation" >
               <td colspan=4><button id="moreBtn" class="myButton foodBtn">더보기</button>
               	<span id="moveToStartBtn">
               		<img src="/images/arrow.PNG" alt="화살표이미지"/>
               	</span>
               </td>
            </tr>
             </table>
         </div>

</body>
<c:url value="/postingList" var="postingList"/>
<c:url value="/postingSearch" var="postingSearch"/>
<c:url value="/postingOrder" var="postingOrder"/>
<script type="text/javascript">
var pageIndex = 1;
var nowSearching = 0;
function dataSetting(list){
	$(list).each(function (index, item){
		$("#beforeLocation").before(
			$("<tr class='tableData' onclick='movePage("+item.postingId+")'><td rowspan='2'><img class='imgBoard' onerror='errorFun(this);'"
					+ "src=/upload/preview_"
					+ item.postingPhoto
					+ "/></td><td colspan='3'>"
					+ item.postingTitle
					+ "</td></tr><tr class='tableData' onclick='movePage("+item.postingId+")'><td>"
					+ item.userId
					+ "</td><td>"
					+ ""
					+ "</td><td>"
					+ item.postingTime
					+ "</td></tr>")
					);
	});
}
$("#searchBtn").click(function(){
						pageIndex = 1;
						$.ajax({
							type : "get",
							url : "${postingSearch}",
							data : {
									searchType : $("#searchType").val(),
									searchData : $("#searchInput").val(),
									orderData : ""
									},
									success : function(result) {
										$(".tableData").remove();
										nowSearching = $("#searchInput").val().length;
																		
										if (result.length == 0) {
											alert("검색 결과가 없습니다.");
										} else {
											dataSetting(result);
										}
									}, error : function(){
										alert("요청에 실패 하였습니다.");
									}
								});
					});

$("#moreBtn").click(function() {
					pageIndex++;
						$.ajax({
								type : "get",
								url : "${postingList}",
								data : {
										page : pageIndex
										},
								success : function(result) {
									dataSetting(result);
									if (result.length < 1) {
											alert("더 이상 목록이 없습니다.");
										}
									},
									error : function() {
										alert("요청에 실패 하였습니다.");
									}
								});
					});
	$("#writeBtn").click(function() {
		$(location).attr('href', "/write/lecturejsp");
	});
//맨위로 버튼
$("#moveToStartBtn").click(function(){
	$("html, body").animate({scrollTop: 0}, 1000);
 });
//페이지 이동 함수
function movePage(el){
	$(location).attr("href", "/postingInfo?postingId="+el);
}
//
$("#writeNoticeBtn").click(function(event){
	event.stopPropagation();
	$(location).attr("href","/write/lectureNotiAddjsp")
});
</script>
</html>