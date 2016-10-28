<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<br>
	<br>
	<br>
	<!--형 여기 br대신 top 마진 div넣어주시고 주석지워 주세요  -->
	<sform:form method="post" action="/noti/notiSetting"
		modelAttribute="settingData">
		<!-- 전체 켜기, 전체 끄기 버튼을 갖는 테이블 -->
		<table class="table table-bored">
			<colgroup>
				<col width="50%">
				<col width="25%">
				<col width="25%">
			</colgroup>
			<tr>
				<td></td>
				<td><button id="all_ONBtn">전체 켜기</button></td>
				<td><button id="all_OFFBtn">전체 끄기</button></td>
			</tr>
		</table>
		<!-- 4개의 알림설정 리스트 테이블 -->
		<c:forEach items="${settingData.settingList}" var="list"
			varStatus="status">
			<sform:hidden id="${list.notiType }" path="settingList[${status.index }].notiType"
						value="${list.notiType }"></sform:hidden>
					<sform:hidden id="${list.notiType }_userId" path="settingList[${status.index }].userId"
						value="${list.userId }"></sform:hidden>
					<sform:hidden id="${list.notiType }_weekCode" path="settingList[${status.index }].weekCode"
						value="${list.weekCode }"></sform:hidden>
					<sform:hidden id="${list.notiType }_notiOn" path="settingList[${status.index }].notiOn"
						value="${list.notiOn }"></sform:hidden>
					<sform:hidden id="${list.notiType }_notiHour" path="settingList[${status.index }].notiHour"
						value="${list.notiHour }"></sform:hidden>
					<sform:hidden id="${list.notiType }_notiMin" path="settingList[${status.index }].notiMin"
						value="${list.notiMin }"></sform:hidden>
			<table class="table table-bored">
				<col width="25%">
				<col width="30%">
				<col width="30%">
				<col width="15%">
				
				<tr>
					<c:choose>
						<c:when test="${list.notiType == 'lecture'}">
							<td>강의 시간</td>
							
						</c:when>
						<c:when test="${list.notiType == 'place'}">
							<td>명소 알림</td>
						</c:when>
						<c:when test="${list.notiType == 'play'}">
							<td>오락 알림</td>
						</c:when>
						<c:when test="${list.notiType == 'food'}">
							<td>음식 알림</td>
						</c:when>
					</c:choose>
					<!-- 실행시키면 시간밑에 톱니 모양이 나오는데 시간 옆으로 옮겨주세요. a태그로 메뉴만들기랑 같은 원리로 만들면되요 -->
					<c:if test="${list.notiType == 'lecture'}">
						<td><output id="${list.notiType}_time">
								<c:if test="${list.notiHour != 0}">${list.notiHour }시간&nbsp;</c:if>${list.notiMin }분전</output>
							<img id="${list.notiType }_timeSetting" alt="설정"
							src="/images/settingImg.PNG"></td>
						<td></td>
					</c:if>
					<c:if test="${list.notiType != 'lecture'}">
						<td>
							<output id="${list.notiType}_time">${list.notiHour }시&nbsp;${list.notiMin }분</output>
							<img id="${list.notiType }_timeSetting" alt="설정" src="/images/settingImg.PNG">
						</td>
						<td>
						<output id="${list.notiType }_day">요일 선택</output>
						<img id="${list.notiType }_weekSetting" alt="설정" src="/images/settingImg.PNG">
						</td>
					</c:if>
					<td></td>
					<td><c:if test="${list.notiOn == 1}">
							<output>ON</output>
						</c:if>
						<c:if test="${list.notiOn == 0}">
							<output>OFF</output>
						</c:if></td>
				</tr>
			</table>
		</c:forEach>

		<sform:button id="sendSetting" type="submit">설정 저장</sform:button>
	</sform:form>
	<!--TimeSetting Modal -->
 <div class="modal fade" id="timeModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">시간 설정</h4>
        </div>
        <div class="modal-body">
          <input id="settingHour" type="number" min="0" max="23" value="">
          <input id="settingMin" type="number" min="0" max="59" value="">
        </div>
        <div class="modal-footer">
        	<input id="target" type="hidden" value="">
          <button id="settingBtn" type="button" class="btn btn-default" data-dismiss="modal">설정 완료</button>
        </div>
      </div>
      
    </div>
  </div>
  
  	<!--daySetting Modal -->
 <div class="modal fade" id="dayModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">요일 설정</h4>
        </div>
        <div class="modal-body">
        <p>요일설정</p>
        </div>
        <div class="modal-footer">
        	<input id="target" type="hidden" value="">
          <button id="settingBtn" type="button" class="btn btn-default" data-dismiss="modal">설정 완료</button>
        </div>
      </div>
      
    </div>
  </div>
<script>
$("document").ready(function(){
	var a = $("#food_weekCode").val();
	alert($("#play_weekCode").val());
	alert($("#place_weekCode").val());
});
var hourCheck = true;
var minCheck = true;
$("#food_weekSetting").click(function(){
	alert("성공");
	$("#dayModal").modal();
})
$("#lecture_timeSetting").click(function(){
	$("#settingHour").val($("#lecture_notiHour").val());
	$("#settingMin").val($("#lecture_notiMin").val());
	$("#target").val("lecture");
    $("#timeModal").modal();
});
$("#food_timeSetting").click(function(){
	$("#settingHour").val($("#food_notiHour").val());
	$("#settingMin").val($("#food_notiMin").val());
	$("#target").val("food");
    $("#timeModal").modal();
});
$("#play_timeSetting").click(function(){
	$("#settingHour").val($("#play_notiHour").val());
	$("#settingMin").val($("#play_notiMin").val());
	$("#target").val("play");
   $("#timeModal").modal();
});
$("#place_timeSetting").click(function(){
	$("#settingHour").val($("#place_notiHour").val());
	$("#settingMin").val($("#place_notiMin").val());
	$("#target").val("place");
	$("#timeModal").modal();
});
$("#settingBtn").click(function(event){
	if(hourCheck && minCheck){
		$("#"+$("#target").val()+"_notiHour").val($("#settingHour").val());
		$("#"+$("#target").val()+"_notiMin").val($("#settingMin").val());
		if($("#target").val()=='lecture'){
			var parsHour = $("#settingHour").val();
			if(parsHour==0 ){
				$("#"+$("#target").val()+"_time").html($("#settingMin").val()+"분전");
			}else{
				$("#"+$("#target").val()+"_time").html($("#settingHour").val()+"시간 "+$("#settingMin").val()+"분전");
			}
		}else{
			$("#"+$("#target").val()+"_time").html($("#settingHour").val()+"시 "+$("#settingMin").val()+"분");
		}
	}else{
		event.stopPropagation();
	}
});

$("#settingHour").change(function(){
	hourCheck = false;
	var tempHour = $("#settingHour").val();
	if(0<=tempHour&&tempHour<24){
		hourCheck = true;
		$("#settingMin").focus();
	}else{
		alert("0~23사이의 숫자를 입력해주세요");
		
	}
});
$("#settingMin").change(function(){
	minCheck = false;
	var tempMin = $("#settingMin").val();
	if(0<=tempMin&&tempMin<60){
		minCheck = true;
	}else{
		alert("0~59사이의 숫자를 입력해주세요");
	}
});
</script>

</body>
</html>