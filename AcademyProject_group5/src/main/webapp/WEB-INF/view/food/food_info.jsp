<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="/css/main.css" />
<link rel="stylesheet"
	href="asset/css/font-awesome.css" />
<title>식사 게시판 글 내용</title>
<script type="text/javascript">
function errorFun(e){
	e.src="/upload/notFoundImg.png";
	}
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div id="wrap">
		<div class="upmargin">
		<h1 class="member">식사 게시글</h1>
			<div class="form9 pad">
			
				<table class="list_table">
					<colgroup>
						<col width="20%">
						<col width="50%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
					<tr>
						<th colspan="4">${postingData.postingTitle }</th>
					</tr>
					</thead>
					<tbody>
					<c:if test="${postingData.postingPhoto ne 'default.png'}">
						<tr>
							<td colspan="4"><img class="imgBoard" src="/upload/${postingData.postingPhoto}"  onerror="errorFun(this)"/></td>
						</tr>
					</c:if>
					<tr>
						<td colspan="4">${postingData.postingContent }</td>
					</tr>
					<c:choose>
						<c:when test="${postingData.userId eq user.userId}">
							<tr style="text-align: right;">
								<td colspan="2"></td>
								<td align="right"><button id="postingUpdateBtn">수정</button></td>
								<td align="left">
									<button id="postingDeleteBtn">삭제</button>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr align="right">
								<td colspan="2"></td >
								<td><span id="postingRecommendCount">${postingData.postingRecommend}</span></td>
								<td><button id="recommendBtn">추천</button></td>
							</tr>
						</c:otherwise>
					</c:choose>
					<tr >
						<td colspan="3"><input id="commentInput" type="text" maxlength="250"></td>
						<td align="right"><button id="commentBtn">댓글 달기</button></td>
					</tr>
					</tbody>
				</table>
				<table id="commentTable">
					<c:forEach items="${commentList }" var="list">
					
					<tr id="${list.commentId }" >
						<c:if test="${list.commentParentId != null }">
						<td>└</td>
						<td>${list.userId } </td>
						</c:if>
						<c:if test="${list.commentParentId == null }">
						<td colspan="2">${list.userId } </td>
						</c:if>
						<td>${list.commentTime }</td>
						<c:choose>
							<c:when test="${!empty user && list.userId==user.userId}">
							<td>
								<img src='/images/updateImg.PNG' onclick='commentUpdate(this)'/>
								<img src='/images/deleteImg.PNG' onclick='commentDelete(this)'/>
								<c:if test="${list.userId.length()>0 && list.commentParentId == null }">
								<button class='childCommentBtn' onclick="recomment(this)">댓글 달기</button>
				 				</c:if>
				 			</td>
							</c:when>
							<c:when test="${list.userId.length()>0&&list.userId!=user.userId && list.commentParentId == null }">
							<td>
								<button class='childCommentBtn' onclick="recomment(this)">댓글 달기</button>
				 			</td>
							</c:when>
							<c:otherwise>
							<td>
						    </td>
							</c:otherwise>
						</c:choose>
					  </tr>
					  <c:if test="${list.commentParentId != null }">
					  <tr>
					  	<td></td><td colspan='3'>${list.commentContent }</td>
					 </tr>
					  </c:if>
					  <c:if test="${list.commentParentId == null }">
					  <tr>
					  	<td colspan='4'>${list.commentContent }</td>
					  </tr>
					  </c:if>
					  </c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
<c:url value="/write/addComment" var="addComment"/>
<c:url value="/write/updateComment" var="updateComment"/>
<c:url value="/mileage/recommendPosting" var="recommend"/>

//댓글 입력창을 위한 변수
var prevParentId;
//수정 입력창을 위한 변수
var prevParentUpdateId;
//댓글 새로 셋팅하기
function tableSetting(parent, child){
	$.each(parent, function(index, item){
		$("#commentTable").append(
				$("<tr id="+item.commentId +">"+
						"<td colspan='2'>"+item.userId+"</td>"+
						"<td>"+item.commentTime+"</td>"+
					  "</tr>"
					)
				);
				if( ${!empty user} && item.userId=='${user.userId}'){
					$("#"+item.commentId).append(
						$("<td>"+
							"<img src='/images/updateImg.PNG' onclick='commentUpdate(this)'/>"+
							"<img src='/images/deleteImg.PNG' onclick='commentDelete(this)'/>"+
							"<button class='childCommentBtn' onclick='recomment(this)'>댓글달기</button>"+
						  "</td>"
						)
					);
				}else if(item.userId.length>0){
					$("#"+item.commentId).append(
							$("<td>"+
								"<button class='childCommentBtn' onclick='recomment(this)'>댓글달기</button>"+
							  "</td>"
							  )
						);
				}else{
					$("#"+item.commentId).append(
						$("<td>"+
						  "</td>"
						  )
					);
				}
				$("#"+item.commentId).after(
					$("<tr>"+
						"<td colspan='4'>"+item.commentContent+"</td>"+
					 "</tr>")
				);
		});
	
	
	$.each(child, function(index, item){
		$("#"+item.commentParentId).next().after(
			$("<tr id="+item.commentId +">"+
				"<td>└</td>"+
				"<td>"+item.userId +"</td>"+
				"<td>"+item.commentTime+"</td>"+
			  "</tr>"
			)
		);
		if(${!empty user} && item.userId=='${user.userId}'){
			$("#"+item.commentId).append(
				$("<td>"+
					"<img src='/images/updateImg.PNG' onclick='commentUpdate(this)'/>"+
					"<img src='/images/deleteImg.PNG' onclick='commentDelete(this)'/>"+
				  "</td>"
				)
			);
		}else{
			$("#"+item.commentId).append(
				$("<td></td>")
			);
		}
		$("#"+item.commentId).after(
			$("<tr><td></td><td colspan='3'>"+item.commentContent+"</td></tr>")
		);
	});
}
//댓글 입력
$("#commentBtn").click(function(){
	if($("#commentInput").val().length>0){
	if(${!empty user.userId}){	
	$.ajax({
	      type : "post",
	      url : "${addComment}",
	      data : {
	    	  postingId : ${postingData.postingId},
	    	  commentContent : $("#commentInput").val()
	      },
	       success : function(result) {
	    	   $("#commentTable").empty();
	    	   $("#commentInput").val("");
	    	   prevParentId = null;
	   		   prevParentUpdateId = null;
	    	   tableSetting(result["parent"],result["child"].reverse());
	      },
	      error : function(){
	    	  alert("댓글 작성에 실패 하였습니다.\n새로고침 후 다시 해주세요");
	      }
	   });
	}else{
		alert("로그인후 이용하세요.");
		$(location).attr('href', "/loginjsp");
		
	}
	}else{alert("1자이상 입력하시오")}
});
//댓글 수정 수정창 생성
function commentUpdate(el){
	$(".commentModifyInput").parent().parent().remove();
	$(".commentModifyBtn").parent().parent().remove();
	var nowParentId = $(el).parent().parent().attr("id");
	if(nowParentId != prevParentUpdateId){
		prevParentUpdateId = nowParentId;
		$(el).parent().parent().next().after(
				$("<tr><td colspan='3'><input class='commentModifyInput' type='text' maxlength='250'></td>"+
						"<input type='hidden' id='commentIdFind' value="+nowParentId+">"+
				"<td><button class='commentModifyBtn' onclick='commentUpdateSend(this)' >수정</button></td></tr>")
			);
	}else{
		prevParentUpdateId = null;
	}
}


//댓글의 댓글 입력창
function recomment(el){
	$(".childCommentSendBtn").parent().parent().remove();
	$(".childCommentSendInput").parent().parent().remove();
	var nowParentId = $(el).parent().parent().attr("id");
	if(nowParentId != prevParentId){
		prevParentId = nowParentId;
		$(el).parent().parent().next().after(
				$("<tr><td colspan='3'><input class='childCommentSendInput' type='text' maxlength='250'></td>"+
				"<td><button class='childCommentSendBtn' onclick='sendComment()'>입력</button></td></tr>")		
		);
	} else {
		prevParentId = null;
	}
	
}
//댓글의 댓글 입력
function sendComment(){
	  if($(".childCommentSendInput").val().length>0){
		if(${!empty user.userId}){	
		$.ajax({
		      type : "post",
		      url : "${addComment}",
		      data : {
		    	  postingId : ${postingData.postingId},
		    	  commentContent : $(".childCommentSendInput").val(),
		    	  commentParentId : $(".childCommentSendInput").parent().parent().prev().prev().attr("id")
		      },
		       success : function(result) {
		    	   $("#commentTable").empty();
		    	   prevParentId = null;
		   		   prevParentUpdateId = null;
		    	   tableSetting(result["parent"],result["child"].reverse())	
		      },
		      error : function(){
		    	  alert("댓글 작성에 실패 하였습니다.\n새로고침 후 다시 해주세요");
		      }
		   });
		}else{
			alert("로그인후 이용하세요.");
			$(location).attr('href', "/loginjsp");
			
		}
		}else{alert("1자이상 입력하시오");} 
}
//댓글 삭제
function commentDelete(el){
	if(confirm("정말로 삭제 하시겠습니까?")){
		var btn = $(el).parent().parent().attr("id");
		$(location).attr('href', "/write/deleteComment?postingId=${postingData.postingId}&commentId="+btn);
	}
	
}
//댓글 수정
function commentUpdateSend(el){
	$.ajax({
	      type : "post",
	      url : "${updateComment}",
	      data : {
	    	  postingId : ${postingData.postingId},
	    	  commentContent : $(".commentModifyInput").val(),
	    	  commentId : $("#commentIdFind").val()
	      },
	       success : function(result) {
	    	   $("#commentTable").empty();
	    	   prevParentId = null;
	   		   prevParentUpdateId = null;
	    	   tableSetting(result["parent"],result["child"].reverse())	
	      },
	      error : function(){
	    	  alert("댓글 수정에 실패 하였습니다.\n새로고침후 다시 해주세요");
	      }
	   });
}
//추천
$("#recommendBtn").click(function(){
	if(${!empty user.userId}){
		
	$.ajax({
		type: "post",
		url : "${recommend}",
		data: {
			postingId : ${postingData.postingId},
			userId: "${user.userId}"
		},
		success : function(result){
			$("#postingRecommendCount").html(result["count"]);
			alert(result["msg"]);
		},
		error : function(request, status, error) {
			alert("오류가 발생 했습니다.\n새로고침 후 다시 해주세요.");
		}
	});
	}else{
		if(confirm("로그인이 필요한 기능입니다.\n 로그인 하시겠습니까?")){
			$(location).attr('href', "/loginjsp");
		}
	}
});
//글수정 페이지이동
$("#postingUpdateBtn").click(function(){
	$(location).attr('href', "/write/foodUpdatejsp?postingId=${postingData.postingId}");
});
//글삭제
$("#postingDeleteBtn").click(function(){
	if(confirm("정말로 삭제 하시겠습니까?")){
		$(location).attr('href', "/write/postingDelete?postingId=${postingData.postingId}");
	}else{
	}
});
</script>
</html>