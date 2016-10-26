var dupOk = false;
var passSame = false;
$("#joinBtn").click(
		function(event) {
			if (passSame && dupOk && idCheck($("#userId").val())
					&& passCheck($("#userPass").val())
					&& nameCheck($("#userName").val())
					&& phoneCheck($("#phoneNum").val())
					&& questionCheck($("#passQuestion").val())
					&& anserCheck($("#passAnswer").val()) == true) {
			} else {
				event.preventDefault();
				alert("가입 실패");
			}

		});

$("#userId").change(function() {
	dupOk = false;
	$("#trueorfalse").attr('class', 'glyphicon glyphicon-false');
	if (idCheck($("#userId").val())) {
		$("#userId").attr('class', 'true form-control');
	} else {
		$("#userId").attr('class', 'false form-control');
	}
});
$("#userPass").change(function() {
	if (passCheck($("#userPass").val())) {
		$("#userPass").attr('class', 'true form-control size8');
		if ($("#passCheck").val() == $("#userPass").val()) {
			$("#passCheck").attr('class', 'true form-control size8');
			passSame = true;
		} else {
			$("#passCheck").attr('class', 'false form-control size8');
			passSame = false;
		}
	} else {
		$("#userPass").attr('class', 'false form-control size8');
	}
});
$("#passCheck").change(
		function() {
			if (passCheck($("#userPass").val())
					&& $("#passCheck").val() == $("#userPass").val()) {
				$("#passCheck").attr('class', 'true form-control size8');
				passSame = true;
			} else {
				$("#passCheck").attr('class', 'false form-control size8');
				passSame = false;
			}

		});
$("#userName").change(function() {
	if (nameCheck($("#userName").val())) {
		$("#userName").attr('class', 'true form-control size8');
	} else {
		$("#userName").attr('class', 'false form-control size8');
	}

});
$("#phoneNum").change(function() {
	if (phoneCheck($("#phoneNum").val())) {
		$("#phoneNum").attr('class', 'true form-control size8');
	} else {
		$("#phoneNum").attr('class', 'false form-control size8');
	}

});
$("#passQuestion").change(function() {
	if (questionCheck($("#passQuestion").val())) {
		$("#passQuestion").attr('class', 'true form-control');
	} else {
		$("#passQuestion").attr('class', 'false form-control');
	}
});
$("#passAnswer").change(function() {
	if (anserCheck($("#passAnswer").val())) {
		$("#passAnswer").attr('class', 'true form-control');
	} else {
		$("#passAnswer").attr('class', 'false form-control');
	}
});
//아이디 중복확인
$("#duplicationCheckBtn").click(
		function(event) {
			event.preventDefault();
			if (idCheck($("#userId").val())) {
				$.ajax({
					type : "get",
					url : "findUser",
					data : {
						userId : $("#userId").val()
					},
					success : function(res) {

						if (res == "true") {
							alert("사용가능한 ID 입니다.");
							dupOk = true;
							$("#trueorfalse").attr('class',
									'glyphicon glyphicon-ok');

						} else {
							alert("이미 있는 ID 입니다.");
							dupOk = false;
							$("#trueorfalse").attr('class',
									'glyphicon glyphicon-false');
						}

					},
					error : function(request, status, error) {
						alert("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);
					}
				});
			} else {
				alert("아이디를 확인하세요");
			}

		});