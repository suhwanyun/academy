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
								<td align="left"><button id="postingDeleteBtn">삭제</button></td>
							</tr>
						</c:when>
						<c:when test="${postingData.userId ne user.userId}">
							<tr align="right">
								<td colspan="3"></td >
								<td><button id="recommendBtn">추천</button></td>
							</tr>
						</c:when>
					</c:choose>
					<tr >
						<td colspan="3"><input id="commentInput" type="text" maxlength="250"></td>
						<td align="right"><button id="commentBtn">댓글 달기</button></td>
					</tr>
					</tbody>
				</table>
				<table id="commentTable">
					<colgroup>
						<col width="5">
						<col width="30%">
						<col width="40%">
						<col width="30%">
					</colgroup>
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
								<button class='commentUpdateBtn'>수정</button>
								<button class='commentDeleteBtn'>삭제</button>
				 			</td>
							</c:when>
							<c:when test="${list.userId!=user.userId && list.commentParentId == null }">
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
					  	<td></td><td colspan='3'>${list.commentContent }</td>
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

var tableInit;
function tableSetting(parent, child){
	$.each(parent, function(index, item){
		$("#commentTable").append(
				$("<tr id="+item.commentId +">"+
						"<td colspan='2'>"+item.userId +"</td>"+
						"<td>"+item.commentTime+"</td>"+
					  "</tr>"
					)
				);
				if( ${!empty user} && item.userId=='${user.userId}'){
					$("#"+item.commentId).append(
						$("<td>"+
							"<button class='commentUpdateBtn'>수정</button>"+
							"<button class='commentDeleteBtn'>삭제</button>"+
						  "</td>"
						)
					);
				}else{
					$("#"+item.commentId).append(
						$("<td>"+
							"<button class='childCommentBtn' onclick='recomment(this)'>댓글달기</button>"+
						  "</td>"
						  )
					);
				}
				$("#"+item.commentId).after(
					$("<tr><td colspan='4'>"+item.commentContent+"</td></tr>")
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
					"<button class='commentUpdateBtn'>수정</button>"+
					"<button class='commentDeleteBtn'>삭제</button>"+
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
	    	   tableSetting(result["parent"],result["child"])	
	      },
	      error : function(request, status, error) {
	         alert("code:" + request.status + "\n" + "message:"
	               + request.responseText + "\n" + "error:"
	               + error);
	      }
	   });
	}else{
		alert("로그인후 이용하세요.");
		$(location).attr('href', "/loginjsp");
		
	}
	}else{alert("1자이상 입력하시오")}
});
//댓글의 댓글 입력창
function recomment(el){
	$(".childCommentSendBtn").parent().parent().remove();
	$(".childCommentSendInput").parent().parent().remove();
	$(el).parent().parent().next().after(
		$("<tr><td colspan='3'><input class='childCommentSendInput' type='text' maxlength='250'></td>"+
		"<td><button class='childCommentSendBtn' onclick='sendComment()'>입력</button></td></tr>")		
	);
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
		    	   tableSetting(result["parent"],result["child"])	
		      },
		      error : function(request, status, error) {
		         alert("code:" + request.status + "\n" + "message:"
		               + request.responseText + "\n" + "error:"
		               + error);
		      }
		   });
		}else{
			alert("로그인후 이용하세요.");
			$(location).attr('href', "/loginjsp");
			
		}
		}else{alert("1자이상 입력하시오");} 
}

$("#postingUpdateBtn").click(function(){
	$(location).attr('href', "/write/postingUpdatejsp");
});
</script>
</html>