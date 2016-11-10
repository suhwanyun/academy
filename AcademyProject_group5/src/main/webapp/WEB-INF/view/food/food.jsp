<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>식사게시판</title>
<script type="text/javascript">
   function errorFun(e) {
      e.src = "/upload/notFoundImg.png";
   }
</script>
</head>
<body>
   <!-- 테이블 시작 -->
   <jsp:include page="/WEB-INF/view/header/header.jsp" />

      <div class="container text-center">
      	<h1>식사 게시판</h1>
            <table class="table-condensed">
           
               <tr>
                  <td><select id="searchType">
                        <option selected="selected" value="user">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                        <option value="all">제목+내용</option>

                  </select></td>
                  <td colspan="2"><input type="search" id="searchInput" ></td>
                  <td class="text-center"><input type="button" id="searchBtn" class="bRight myButton"
                     value="찾기"></td>
               </tr>
               
               <tr>

                  <td colspan="3">
                     <input type="radio" name="sortVal" checked="checked" value="time">날짜
                     <input type="radio" name="sortVal" value="recommend">추천
                  </td>
                  <td>
                     <button id="writeBtn" class="myButton bRight">글쓰기</button>
                  </td>
               </tr>
              
            </table>
		</div>
		<div class="container text-center">
            <table class="list_table">
               <colgroup>
                  <col width="20%">
                  <col width="20%">
                  <col width="10%">
                  <col width="50%">
               </colgroup>
               <c:if test="${!empty mostRecommendData}">
                  <tr class="mostRecommend tableData" onclick="movePage(${mostRecommendData.postingId})">
                     <td rowspan="2"><img class="imgBoard"
                        src="/upload/preview_${mostRecommendData.postingPhoto}"
                        onerror="errorFun(this);" /></td>
                     <td colspan="3">${mostRecommendData.postingTitle }</td>

                  </tr>
                  <tr class="mostRecommend tableData" onclick="movePage(${mostRecommendData.postingId})">
                     <td>${mostRecommendData.userId }</td>
                     <td>${mostRecommendData.postingRecommend }</td>
                     <td>${mostRecommendData.postingTime }</td>
                  </tr>
               </c:if>
               <c:forEach items="${postingDataList }" var="list">
                  <tr class="tableData" onclick="movePage(${list.postingId})">
                     <td rowspan="2"><img class="imgBoard"
                        onerror="errorFun(this);"
                        src="/upload/preview_${list.postingPhoto}" /></td>
                     <td colspan="3">${list.postingTitle }</td>
                  </tr>
                  <tr class="tableData" onclick="movePage(${list.postingId})">
                     <td>${list.userId }</td>
                     <td>${list.postingRecommend }</td>
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
	<jsp:include page="/WEB-INF/view/footer/footer.jsp" /> 
   <!-- 테이블 종료 -->
</body>
<c:url value="/postingList" var="postingList"/>
<c:url value="/postingSearch" var="postingSearch"/>
<c:url value="/postingOrder" var="postingOrder"/>
<script type="text/javascript">
var mostRecommend;
var pageIndex = 1;
var nowSearching = 0;
//최고 추천 목록
$("document").ready(function() {
	mostRecommend = "<tr class='mostRecommend tableData' onclick='movePage(${mostRecommendData.postingId}'>";
	mostRecommend += $(".mostRecommend").html();
	mostRecommend += "</tr><tr class='mostRecommend tableData' onclick='movePage(${mostRecommendData.postingId}'>";
	mostRecommend += $(".mostRecommend + tr").html();
	mostRecommend += "</tr>";
});
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
					+ item.postingRecommend
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
									orderData : $(':radio[name="sortVal"]:checked').val()
									},
									success : function(result) {
										$(".tableData").remove();
										nowSearching = $("#searchInput").val().length;
										if ($("#searchInput").val().length < 1) {
											$("#beforeLocation").before(mostRecommend);
										}
										
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
		$(location).attr('href', "/write/foodjsp");
	});
	$(':radio[name="sortVal"]').change(function(){
		pageIndex = 1;
		$.ajax({
		      type : "GET",
		      url : "${postingOrder}",
		      data : {
		    	  orderData : $(':radio[name="sortVal"]:checked').val()
		      },
		       success : function(result) {
		    	   $(".tableData").remove();
		    	   if (nowSearching < 1) {
						$("#beforeLocation").before(mostRecommend);
					}
		    	   dataSetting(result);	
		       }
		   });
	});
//맨위로 버튼
$("#moveToStartBtn").click(function(){
	$("html, body").animate({scrollTop: 0}, 1000);
 });
//페이지 이동 함수
function movePage(el){
	$(location).attr("href", "/postingInfo?postingId="+el);
}
</script>
</html>