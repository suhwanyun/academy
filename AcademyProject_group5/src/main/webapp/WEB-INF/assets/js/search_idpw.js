var once = true;
$("#idCheck").click(
		function() {
			if (emailCheck($("#email").val())
					&& nameCheck($("#userName").val())) {
				$.ajax({
					type : "get",
					url : "findId",
					data : {
						userName : $("#userName").val(),
						email : $("#email").val()
					},
					success : function(res) {
						if (res.length > 2) {
							alert("귀하의 아이디는 : " + res);

						} else {
							alert("조회된 아이디가 없습니다.");
						}

					},
					error : function(request, status, error) {
						alert("요청 실패!\n잠시 후 다시 시도해 주세요");
					}
				});
			} else {
				alert("이름, 이메일을 확인해 주세요.");
			}

		});
$("#passCheck").click(
		function() {
			if (once) {
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
								$("#res").val("임시 비밀번호 : [" + res + "]");
								once = false;
							} else {
								alert("답이 올바르지 않습니다.");
							}

						},
						error : function(request, status, error) {
							alert("요청 실패!\n잠시 후 다시 시도해 주세요");
						}
					});
				}
			}

		});
$("#userId").change(
		function() {
			$.ajax({
					type : "get",
					url : "findQuestion",
					data : {
						userId : $("#userId").val()
					},
					success : function(res) {
						if (res.length > 1) {
							$("#passQuestion").html(res)

						} else {
							$("#passQuestion")
									.val("내가 태어난 고향은?");
						}

					},
					error : function(request, status, error) {
						alert("요청 실패!\n잠시 후 다시 시도해 주세요");
					}
				});
			
		});