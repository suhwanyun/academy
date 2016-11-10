<%@ page language="java" import="java.sql.*,java.io.*" contentType="text/html;charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>���� �ڼ��� ����</title>
<script type="text/javascript">
function errorFun(e){
	e.src="/upload/notFoundImg.png";
	}
</script>
</head>

<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container">
		<div class="text-center">
			
				<table class="table">
					
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
								<td align="right"><button id="postingUpdateBtn" class="bRight myButton" style="margin-right:-30%;">����</button></td>
								<td align="left">
									<button id="postingDeleteBtn" class="myButton bRight">����</button>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr align="right">
								<td colspan="2"></td >
								<td><span id="postingRecommendCount">${postingData.postingRecommend}</span></td>
								<td><button id="recommendBtn" class="myButton">��õ</button></td>
							</tr>
						</c:otherwise>
					</c:choose>
					<tr >
						<td colspan="3"><input id="commentInput" type="text" maxlength="250"></td>
						<td align="right"><button id="commentBtn" class="myButton">��� �ޱ�</button></td>
					</tr>
					</tbody>
				</table>
				<table id="commentTable" class="table">
					<c:forEach items="${commentList }" var="list">
					
					<tr id="${list.commentId }" >
						<c:if test="${list.commentParentId != null }">
						<td>��</td>
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
								<button class='childCommentBtn myButton' onclick="recomment(this)">��� �ޱ�</button>
				 				</c:if>
				 			</td>
							</c:when>
							<c:when test="${list.userId.length()>0&&list.userId!=user.userId && list.commentParentId == null }">
							<td>
								<button class='childCommentBtn myButton' onclick="recomment(this)">��� �ޱ�</button>
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
	
</body>

<script type="text/javascript">
<c:url value="/write/addComment" var="addComment"/>
<c:url value="/write/updateComment" var="updateComment"/>
<c:url value="/mileage/recommendPosting" var="recommend"/>

//��� �Է�â�� ���� ����
var prevParentId;
//���� �Է�â�� ���� ����
var prevParentUpdateId;
//��� ���� �����ϱ�
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
							"<button class='childCommentBtn myButton' onclick='recomment(this)'>��۴ޱ�</button>"+
						  "</td>"
						)
					);
				}else if(item.userId.length>0){
					$("#"+item.commentId).append(
							$("<td>"+
								"<button class='childCommentBtn myButton' onclick='recomment(this)'>��۴ޱ�</button>"+
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
				"<td>��</td>"+
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
//��� �Է�
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
	    	  alert("��� �ۼ��� ���� �Ͽ����ϴ�.\n���ΰ�ħ �� �ٽ� ���ּ���");
	      }
	   });
	}else{
		alert("�α����� �̿��ϼ���.");
		$(location).attr('href', "/loginjsp");
		
	}
	}else{alert("1���̻� �Է��Ͻÿ�")}
});
//��� ���� ����â ����
function commentUpdate(el){
	$(".commentModifyInput").parent().parent().remove();
	$(".commentModifyBtn").parent().parent().remove();
	var nowParentId = $(el).parent().parent().attr("id");
	if(nowParentId != prevParentUpdateId){
		prevParentUpdateId = nowParentId;
		$(el).parent().parent().next().after(
				$("<tr><td colspan='3'><input class='commentModifyInput' type='text' maxlength='250'></td>"+
						"<input type='hidden' id='commentIdFind' value="+nowParentId+">"+
				"<td><button class='commentModifyBtn' onclick='commentUpdateSend(this)' >����</button></td></tr>")
			);
	}else{
		prevParentUpdateId = null;
	}
}


//����� ��� �Է�â
function recomment(el){
	$(".childCommentSendBtn").parent().parent().remove();
	$(".childCommentSendInput").parent().parent().remove();
	var nowParentId = $(el).parent().parent().attr("id");
	if(nowParentId != prevParentId){
		prevParentId = nowParentId;
		$(el).parent().parent().next().after(
				$("<tr><td colspan='3'><input class='childCommentSendInput' type='text' maxlength='250'></td>"+
				"<td><button class='childCommentSendBtn' onclick='sendComment()'>�Է�</button></td></tr>")		
		);
	} else {
		prevParentId = null;
	}
	
}
//����� ��� �Է�
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
		    	  alert("��� �ۼ��� ���� �Ͽ����ϴ�.\n���ΰ�ħ �� �ٽ� ���ּ���");
		      }
		   });
		}else{
			alert("�α����� �̿��ϼ���.");
			$(location).attr('href', "/loginjsp");
			
		}
		}else{alert("1���̻� �Է��Ͻÿ�");} 
}
//��� ����
function commentDelete(el){
	if(confirm("������ ���� �Ͻðڽ��ϱ�?")){
		var btn = $(el).parent().parent().attr("id");
		$(location).attr('href', "/write/deleteComment?postingId=${postingData.postingId}&commentId="+btn);
	}
	
}
//��� ����
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
	    	  alert("��� ������ ���� �Ͽ����ϴ�.\n���ΰ�ħ�� �ٽ� ���ּ���");
	      }
	   });
}
//��õ
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
			alert("������ �߻� �߽��ϴ�.\n���ΰ�ħ �� �ٽ� ���ּ���.");
		}
	});
	}else{
		if(confirm("�α����� �ʿ��� ����Դϴ�.\n �α��� �Ͻðڽ��ϱ�?")){
			$(location).attr('href', "/loginjsp");
		}
	}
});
//�ۼ��� �������̵�
$("#postingUpdateBtn").click(function(){
	$(location).attr('href', "/write/foodUpdatejsp?postingId=${postingData.postingId}");
});
//�ۻ���
$("#postingDeleteBtn").click(function(){
	if(confirm("������ ���� �Ͻðڽ��ϱ�?")){
		$(location).attr('href', "/write/postingDelete?postingId=${postingData.postingId}");
	}else{
	}
});
</script>
</html>