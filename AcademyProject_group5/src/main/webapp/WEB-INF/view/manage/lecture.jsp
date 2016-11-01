<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>

<title>강의 관리자 메인화면</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/manage/header.jsp" />
	<div class="container">
		<button id="lectureAddBtn">강의 등록</button>
		<table>
			<tr>
				<td>강의 id</td>
				<td>강의 이름</td>
				<td>교수 이름</td>
				<td>강의 분반</td>
				<td>
					<ul>
						<li>
							<span>강의 시작시간</span>
							<span>강의 종료시간</span>
							<span>강의 장소</span>
							<span>강의 요일</span>
							<span>임시 등록 날짜</span>
						</li>
						<li>
							<span>강의 시작시간</span>
							<span>강의 종료시간</span>
							<span>강의 장소</span>
							<span>강의 요일</span>
							<span>임시 등록 날짜</span>
						</li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
$("#lectureAddBtn").click(function(){
	$(location).attr('href', "/lectureManage/addjsp");
});
</script>
</html>