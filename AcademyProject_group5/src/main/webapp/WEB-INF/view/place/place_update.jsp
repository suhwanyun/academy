<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 수정</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container text-center center">

			<form method="post" action="/write/postingUpdate" enctype="multipart/form-data">
				<table class="table">
				<colgroup>
					<col width="15%">
					<col width="70%">
					<col width="15%">
				</colgroup>

				<tr>
					<td><label for="postingTitle">제 목</label></td>
					<td colspan="2"><input type="text" maxlength="30"
						name="postingTitle" value="${postingData.postingTitle }"
						class="sizeInput" /></td>
				</tr>
				<tr>
					<td><label for="postingContent">내 용</label></td>
					<td colspan="2"><textarea style="" rows="13" maxlength="1300"
							name="postingContent">${postingData.postingContent }</textarea></td>
				</tr>
				<tr id="imgShow">
					<td></td>
					<td align="left">
						<button id="imgdelete" class="myButton">이미지 삭제</button>
						<span id="checkImg"></span>
					</td>
					<td align="right"><button id="imgUpdate" class="myButton">이미지 수정</button></td>
				</tr>
				<tr class="imgSetting" hidden="true">
					<td></td>
					<td align="left">
					<label for="fileInput" class="myButton">파일 선택</label>
					<input id="fileInput" type="file" name="uploadPhoto" accept="image/*" />
					<input id="fileText" class="fileText" type="text" value="파일 선택" disabled="disabled" style="width:100px;"/>
					</td>
					<td align="right"><button class="myButton" id="imgCancel">기존 이미지</button></td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="hidden" id="deletePhoto" name="deletePhoto" value="false"> 
						<input type="hidden" name="postingId" value="${postingData.postingId}">
						<button type="submit" id="save" class="myButton">글 수정</button>
						<button id="cancel" class="myButton">취소</button>
					</td>
				</tr>
			</table>

			</form>
		</div>
	
</body>

<script type="text/javascript">
var defaultCheckStr;
<c:choose>
	<c:when test="${postingData.postingPhoto != 'default.png'}">
		defaultCheckStr = "기존 이미지";
	</c:when>
	<c:otherwise>
		defaultCheckStr = "이미지 없음";
	</c:otherwise>
</c:choose>

$(document).ready(function(){
	$("#checkImg").html(defaultCheckStr);
	
	 var fileTarget = $('#fileInput');
	 
	  fileTarget.on('change', function(){  // 값이 변경되면
		// modern browser
	    if(window.FileReader){  
	      var filename = $(this)[0].files[0].name;
	    } 
	 	// old IE
	    else {  
	      var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
	    }    
	    // 추출한 파일명 삽입
	    $(this).siblings('#fileText').val(filename);
	  });
});

//기존 이미지
$("#imgCancel").click(function(event){
	event.preventDefault();
	$("#fileInput").val("");
	$(".imgSetting").attr("hidden","true");
	$("#imgShow").removeAttr("hidden");
	$("#deletePhoto").val("false");
	$("#checkImg").html(defaultCheckStr);
});
//이미지 수정
$("#imgUpdate").click(function(event){
	event.preventDefault();
	$("#imgShow").attr("hidden","true");
	$(".imgSetting").removeAttr("hidden");
	$("#deletePhoto").val("${postingData.postingPhoto}");
});
// 이미지 삭제
$("#imgdelete").click(function(event){
	event.preventDefault();
	$("#fileInput").val("");
	$("#deletePhoto").val("${postingData.postingPhoto}");
	$("#checkImg").html("이미지 없음")	;
});
// 취소
$("cancel").click(function(event){
	event.preventDefault();
	$(location).attr('href', "/placeMain");
});
</script>
</html>