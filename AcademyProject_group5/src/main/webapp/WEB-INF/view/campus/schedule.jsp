<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script>

</script>
<title>시간표</title>
</head>
<div style="width: 1200px;">

	<table class="table table-bordered text-center tableL">
	<colgroup>
		<col width="9%">
		<col width="13%">
		<col width="13%">
		<col width="13%">
		<col width="13%">
		<col width="13%">
		<col width="13%">
		<col width="13%">
	</colgroup>
		<tr id="DayIndex">
			<th></th>
			<th>Mon</th>
			<th>Tue</th>
			<th>Wed</th>
			<th>Thu</th>
			<th>Fri</th>
			<th>Sat</th>
			<th>Sun</th>

		</tr>
		<tr id="class_1">
			<td class="text-center">1교시<br>09:00~<br>09:50
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
		<tr id="class_2">
			<td class="text-center">2교시<br>10:00~<br>10:50
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
		<tr id="class_3">
			<td class="text-center">3교시<br>11:00~<br>11:50
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
		<tr id="class_4">
			<td class="text-center">4교시<br>12:00~<br>12:50
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
		<tr id="class_5">
			<td class="text-center">5교시<br>13:00~<br>13:50
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
		<tr id="class_6">
			<td class="text-center">6교시<br>14:00~<br>14:50
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
		<tr id="class_7">
			<td class="text-center">7교시<br>15:00~<br>15:50
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
		<tr id="class_8">
			<td class="text-center">8교시<br>16:00~<br>16:50
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
		<tr id="class_9">
			<td class="text-center">9교시<br>17:00~<br>17:50
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>

		</tr>
	</table>
</div>
</body>
<script type="text/javascript">
<c:url value="/campus/schedule" var="schedule"/>
$("document").ready(function(){
	var tableList = "${timetable}";
	$.ajax({
	      type : "GET",
	      url : "${schedule}",
	       success : function(result) {
	    	   timeTable(result);
				},
			error : function(result){
				alert("시간표 요청에 실패 했습니다.");
			}
	   });
});
function timeTable(tableList){
	var startClass = 0;
	var endClass = 0;
	var day = 0;
	$(tableList).each(function(index,item){
		day = item.lectureWeek - 1;
		startClass = item.lectureStart;
		endClass = item.lectureEnd;
		for(var i = startClass; i <= endClass; i++){
			$("#class_"+i+" td:eq("+(day == 0 ? 7 : day)+")").html(item.lectureName+"("+item.lectureClass+")"+"<br>"+item.lecturePlace);
		}
	});
}
</script>
</html>