<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<title>전체 강의 목록</title>
</head>
<body>
<div class="container">
	<table class="table-condensed">
		<tr>
			<td><select id="serchType" >
					<option selected="selected" value="">선택</option>
					<option value="lecture">강의 이름</option>
					<option value="professor">교수 이름</option>
			</select></td>
			<td><input type="search" id="serachInput" /></td>
			<td>
				<button id="searchBtn" class="myButton">검색</button>
			</td>
		</tr>
	</table>

	<div class="container text-center">
		
			<table class="table">
				<thead>

					<tr>
						<th style="text-align: center;">강의 이름</th>
						<th style="text-align: center;">교수 이름</th>
						<th style="text-align: center;">강의 분반</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lectureList }" var="list">
						<tr>
							<td><a
								href="/lecture/lectureInfo?lectureId=${list.lectureId}&lectureClass=${list.lectureClass}">${list.lectureName}</a></td>
							<td>${list.professorName}</td>
							<td>${list.lectureClass}</td>
						</tr>
					</c:forEach>
				</tbody>
			
			</table>
			   
         </div>
         <div style="margin-top:5%;"> 
               <button id="moreBtn" class="myButton size8mar">더보기</button>
               <button class="myButton size4">맨 위로</button>
            </div>
		</div>
	
</body>


<script type="text/javascript">
	var pageIndex = 1;
	<c:url value="/campus/lectureList" var="nextlectureList"/>
	<c:url value="/campus/lectureListSearch" var="lectureListSearch"/>
	$("#searchBtn")
			.click(
					function() {
						$
								.ajax({
									type : "get",
									url : "${lectureListSearch}",
									data : {
										searchType : $("#serchType").val(),
										searchData : $("#serachInput").val()
									},
									success : function(result) {
										$(".tableData").remove();
										pageIndex = 1;
										if (result.length == 0) {
											alert("검색 결과가 없습니다.");
										} else {
											$(result)
													.each(
															function(index,
																	item) {
																$(
																		"#beforeLectureLocation")
																		.before(
																				$("<tr class = 'tableData'><td>"
																						+ "<a href="
																						+ "/lecture/lectureInfo?lectureId="
																						+ item.lectureId
																						+ "&lectureClass="
																						+ item.lectureClass
																						+ ">"
																						+ item.lectureName
																						+ "</a></td>"
																						+ "<td>"
																						+ item.professorName
																						+ "</td>"
																						+ "<td>"
																						+ item.lectureClass
																						+ "</td></tr>"));
															});
										}
									}

								});
					});
	$("#moreBtn")
			.click(
					function() {

						pageIndex++;

						$
								.ajax({
									type : "get",
									url : "${nextlectureList}",
									data : {
										page : pageIndex
									},
									success : function(result) {
										var itemCount = 0;
										$(result)
												.each(
														function(index, item) {
															itemCount++;
															$(
																	"#beforeLectureLocation")
																	.before(
																			$("<tr class = 'tableData'><td>"
																					+ "<a href="
																					+ "/lecture/lectureInfo?lectureId="
																					+ item.lectureId
																					+ "&lectureClass="
																					+ item.lectureClass
																					+ ">"
																					+ item.lectureName
																					+ "</a></td>"
																					+ "<td>"
																					+ item.professorName
																					+ "</td>"
																					+ "<td>"
																					+ item.lectureClass
																					+ "</td></tr>"));
														});

										if (itemCount == 0) {
											alert("더 이상 목록이 없습니다.");
										}
									},
									error : function(request, status, error) {
										alert("code:" + request.status + "\n"
												+ "message:"
												+ request.responseText + "\n"
												+ "error:" + error);
									}
								});
					});
</script>
</html>