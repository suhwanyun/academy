<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/font-awesome.css" />
<title>식사 게시판 글 내용</title>
<script type="text/javascript">
function errorFun(e){
	e.src="<%=request.getContextPath()%>/upload/notFoundImg.png";
	}
</script>
</head>

<body>
	<jsp:include page="../../header/header.jsp" />
	<div id="wrap">
		<div class="upmargin">
			<div class="form9 pad">
				<table class="tableData">
					<colgroup>
						<col width="20%">
						<col width="50%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<tr>
						<td colspan="4">${postingData.postingTitle }</td>
					</tr>
					<c:if test="${postingData.postingPhoto ne 'default.png'}">
						<tr>
							<td colspan="4"><img class="imgBoard" src="<%=request.getContextPath()%>/upload/${postingData.postingPhoto}"  onerror="errorFun(this)"/></td>
						</tr>
					</c:if>
					<tr>
						<td colspan="4">${postingData.postingContent }</td>
					</tr>
					<tr>
						<td colspan="4" align="right">이미지, 추천버튼</td>
					</tr>
					<c:if test="${postingData.userId eq user.userId}">
					<tr>
						<td colspan="4">수정, 삭제</td>
					</tr>
					
					</c:if>
					<tr >
						<td colspan="3"><input id="commentInput" type="text" maxlength="250"></td>
						<td align="right"><button id="commentBtn">댓글 달기</button></td>
					</tr>
				</table>
				<table id="commentTable">
					<colgroup>
						<col width="30%">
						<col width="40%">
						<col width="30%">
					</colgroup>
					<c:forEach items="${commentList }" var="list">
					
					<tr id="${list.commentId }" >
						<td>${list.userId } </td>
						<td>${list.commentTime }</td>
						<c:choose>
							<c:when test="${!empty user && list.userId==user.userId}">
							<td>
								<button  id='commentUpdateBtn'>수정</button>
								<button  id='commentDeleteBtn'>삭제</button>
				 			</td>
							</c:when>
							<c:otherwise>
							<td>
								<button  id='childCommentBtn'>댓글달기</button>
						    </td>
							</c:otherwise>
						</c:choose>
					  </tr>
					  <tr>
					  	<td colspan='3'>${list.commentContent }</td>
					  </tr>
					  </c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
<c:url value="/addComment" var="addComment"/>

var tableInit;
function tableSetting(comment, child){
	$.each(comment, function(index, item){
		$("#commentTable").append(
				$("<tr id="+item.commentId +">"+
						"<td>"+item.userId +"</td>"+
						"<td>"+item.commentTime+"</td>"+
					  "</tr>"
					)
				);
				if(${!empty user} && item.userId=='${user.userId}'){
					$("#"+item.commentId).append(
						$("<td>"+
							"<button  id='commentUpdateBtn'>수정</button>"+
							"<button  id='commentDeleteBtn'>삭제</button>"+
						  "</td>"
						)
					);
				}else{
					$("#"+item.commentId).append(
						$("<td>"+
							"<button  id='childCommentBtn'>댓글달기</button>"+
						  "</td>"
						  )
					);
				}
				$("#"+item.commentId).append(
					$("<tr><td colspan='3'>"+item.commentContent+"</td></tr>")
				);
		});
	
	
	$.each(child, function(index, item){
		$("#"+item.commentParentId).after(
			$("<tr id="+item.commentId +">"+
				"<td>"+item.userId +"</td>"+
				"<td>"+item.commentTime+"</td>"+
			  "</tr>"
			)
		);
						
		if(${!empty user} && item.userId=='${user.userId}'){
			$("#"+item.commentId).append(
				$("<td>"+
					"<button  id='commentUpdateBtn'>수정</button>"+
					"<button  id='commentDeleteBtn'>삭제</button>"+
				  "</td>"
				)
			);
		}else{
			$("#"+item.commentId).append(
				$("<td>"+
					"<button  id='childCommentBtn'>댓글달기</button>"+
				  "</td>"
				  )
			);
		}
		$("#"+item.commentId).append(
			$("<tr><td colspan='3'>"+item.commentContent+"</td>")
		);
	});
}
$("document").ready(function(){
	tableInit = $("#commentTable");
	/* var comment = ${commentList};
	var child = ${childCommentList}; */
	tableSetting(comment, child);
	
});
$("#commentBtn").click(function(){
	$.ajax({
	      type : "post",
	      url : "${addComment}",
	      data : {
	    	  postingId : ${postingData.postingId},
	    	  commentContent : $("#commentInput").val()
	      },
	       success : function(result) {
	    	   alert(result);
	         	$("#commentTable").remove();
	         	$(".tableData").after(tableInit);
	         	tableSetting(result["parent"], result["child"]);
	         	
	      },
	      error : function(request, status, error) {
	         alert("code:" + request.status + "\n" + "message:"
	               + request.responseText + "\n" + "error:"
	               + error);
	      }
	   });
});
</script>
</html>