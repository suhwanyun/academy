$("#idCheck").click(
		function() {
			if (phoneCheck($("#phoneNum").val())
					&& nameCheck($("#userName").val())) {
				$.ajax({
					type : "get",
					url : "findId",
					data : {
						userName : $("#userName").val(),
						phoneNum : $("#phoneNum").val()
					},
					success : function(res) {
						if (res.length > 2) {
							alert("귀하의 아이디는 : " + res);

						} else {
							alert("조회된 아이디가 없습니다.");
						}

					},
					error : function(request, status, error) {
						alert("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);
					}
				});
			} else {
				alert("이름, 전화번호 양식이 틀렸습니다.");
			}

		});
$("#passCheck").click(
		function() {
			if (anserCheck($("#passAnswer").val())) {
				$.ajax({
					type : "get",
					url : "findPass",
					data : {
						userId : $("#userId").val(),
						passAnswer : $("#passAnswer").val()
					},
					success : function(res) {
						if (res.length > 2) {
							$("#res").val("임시 비밀번호 : [ " + res + " ]");
						} else {
							alert("아이디나 비밀번호 답을 확인하세요.");
						}

					},
					error : function(request, status, error) {
						alert("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);
					}
				});
			} else {
				alert("질문 답 양식이 틀렸습니다.");
			}

		});
$("#userId").change(
		function() {
			if (idCheck($("#userId").val())) {
				$.ajax({
					type : "get",
					url : "findQuestion",
					data : {
						userId : $("#userId").val()
					},
					success : function(res) {
						if (res.length > 1) {
							$("#passQuestion").val(res)

						} else {
							$("#passQuestion")
									.val("조회된 질문이 없습니다. 관리자에게 문의 하세요");
						}

					},
					error : function(request, status, error) {
						alert("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);
					}
				});
			} else {
				alert("ID 양식이 틀렸습니다.");
			}
		});