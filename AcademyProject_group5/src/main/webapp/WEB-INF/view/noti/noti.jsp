<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
 .weekBtnOn{
  background-color: blue;
 }
 .weekBtnOff{
  background-color: gray;
 }
</style>
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
					<td>
						<input id="${list.notiType }_toggle" data-toggle="toggle" type="checkbox" value="${list.notiOn}">
					</td>
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
        	<input  id="day0" type="hidden" value="">
        	<button id="day1" value="" onclick="btnToggle(this)">월</button>
        	<button id="day2" value="" onclick="btnToggle(this)">화</button>
        	<button id="day3" value="" onclick="btnToggle(this)">수</button>
        	<button id="day4" value="" onclick="btnToggle(this)">목</button>
        	<button id="day5" value="" onclick="btnToggle(this)">금</button>
        	<button id="day6" value="" onclick="btnToggle(this)">토</button>
        	<button id="day7" value="" onclick="btnToggle(this)">일</button>
       
       </div>
        <div class="modal-footer">
        	<input id="dayTarget" type="hidden" value="">
          <button id="daySettingBtn" type="button" class="btn btn-default" data-dismiss="modal">설정 완료</button>
        </div>
      </div>
      
    </div>
  </div>
<script>
//시간,분 확인을 위한 변수
var hourCheck = true; 
var minCheck = true;
//요일의 바이너리코드를 받을 변수
var food_WeekCode;
var play_WeekCode;
var place_WeekCode;
//토글버튼 초기화 변수
var isFirst = true;
$(document).ready(function(){
	onoffCheck('#lecture_toggle');
	onoffCheck('#food_toggle');
	onoffCheck('#play_toggle');
	onoffCheck('#place_toggle');
	isFirst = false;
});
//토글버튼 초기 설정
function onoffCheck(el){
	if($(el).val()=='1'){
		$(el).bootstrapToggle('on');
		$(el).val("1");
	}else if($(el).val()=='0'){
		$(el).bootstrapToggle('off');
		$(el).val("0");
	}
}

function daySettingFun(code){
	var day = new Array(8);
	for(var i = 0; i<code.length; i++){
		day[i]=code.charAt(i);
		$("#day"+i).val(day[i]);
		if($("#day"+i).val()==1){
			$("#day"+i).attr("class", "weekBtnOn");
		}else{
			$("#day"+i).attr("class", "weekBtnOff");
		}
	}
}
//요일 토글 버튼
function btnToggle(el){
	if($(el).val()=="1"){
		$(el).val("0");
		$(el).attr("class", "weekBtnOff");
	}else{
		$(el).val("1");
		$(el).attr("class", "weekBtnOn");
	}
	
}
function myLpad(string, padLength, padString){
    var s = string;
    while(s.length < padLength)
        s = padString + s;
    return s;
}
 
$("#daySettingBtn").click(function(){
	var send_WeekCode="";
	
	for(var i = 0; i<8; i++){
		send_WeekCode+=$("#day"+i).val();
	}
	var temp = String.fromCharCode(parseInt(send_WeekCode,2));
	$("#"+$("#dayTarget").val()+"_weekCode").val(temp);
	
	
})

$("#food_weekSetting").click(function(){
	var tempFood_WeekCode = ($("#food_weekCode").val()).charCodeAt(0).toString(2);
	food_WeekCode = myLpad(tempFood_WeekCode,8,0);
	daySettingFun(food_WeekCode);
	$("#dayTarget").val("food");
	$("#dayModal").modal();
})
$("#play_weekSetting").click(function(){
	var tempPlay_WeekCode = ($("#play_weekCode").val()).charCodeAt(0).toString(2);
	play_WeekCode = myLpad(tempPlay_WeekCode,8,0);
	daySettingFun(play_WeekCode);
	$("#dayTarget").val("play");
	$("#dayModal").modal();
})
$("#place_weekSetting").click(function(){
	var tempPlace_WeekCode = ($("#place_weekCode").val()).charCodeAt(0).toString(2);
	place_WeekCode = myLpad(tempPlace_WeekCode,8,0);
	daySettingFun(place_WeekCode);
	$("#dayTarget").val("place");
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

$("#lecture_toggle").change(function(){
	toggleFun("#lecture");
});
$("#food_toggle").change(function(){
	toggleFun("#food");
});
$("#play_toggle").change(function(){
	toggleFun("#play");
});
$("#place_toggle").change(function(){
	toggleFun("#place");
});
function toggleFun(el){
	if(!isFirst){
		if($(el+"_toggle").val()=='1'){
			$(el+"_toggle").val("0");
			$(el+"_notiOn").val("0");
		}else{
			$(el+"_toggle").val("1");
			$(el+"_notiOn").val("1");
		}
	}
}
function allOn(){
	$("#lecture_toggle").bootstrapToggle('on');
	$("#lecture_toggle").val("1");
	$("#lecture_notiOn").val("1");
	$("#food_toggle").bootstrapToggle('on');
	$("#food_toggle").val("1");
	$("#food_notiOn").val("1");
	$("#play_toggle").bootstrapToggle('on');
	$("#play_toggle").val("1");
	$("#play_notiOn").val("1");
	$("#place_toggle").bootstrapToggle('on');
	$("#place_toggle").val("1");
	$("#place_notiOn").val("1");
}
function allOff(){
	$("#lecture_toggle").bootstrapToggle('off');
	$("#lecture_toggle").val("0");
	$("#lecture_notiOn").val("0");
	$("#food_toggle").bootstrapToggle('off');
	$("#food_toggle").val("0");
	$("#food_notiOn").val("0");
	$("#play_toggle").bootstrapToggle('off');
	$("#play_toggle").val("0");
	$("#play_notiOn").val("0");
	$("#place_toggle").bootstrapToggle('off');
	$("#place_toggle").val("0");
	$("#place_notiOn").val("0");
}
$("#all_ONBtn").click(function(event){
	event.preventDefault();
	allOn();
});
$("#all_OFFBtn").click(function(event){
	event.preventDefault();
	allOff(); 
});
</script>
</body>
</html>