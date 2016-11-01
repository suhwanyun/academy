var idDupOk = false;
var emailDupOk = false;
var passSame = false;
$("#joinBtn").click(
		function(event) {
			if (passSame && emailDupOk &&idDupOk && idCheck($("#userId").val())
					&& passCheck($("#userPass").val())
					&& nameCheck($("#userName").val())
					&& emailCheck($("email").val())
					&& questionCheck($("#passQuestion").val())
					&& anserCheck($("#passAnswer").val()) == true) {
			} else {
				event.preventDefault();
				alert("가입 실패");
			}

		});

$("#userId").change(function() {
	idDupOk = false;
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
$("#email").change(function() {
	emailDupOk=false;
	$("#emailtrueorfalse").attr('class', 'glyphicon glyphicon-false');
	if (emailCheck($("#email").val())) {
		$("#email").attr('class', 'true form-control size10');
	} else {
		$("#email").attr('class', 'false form-control size10');
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
$("#idDuplicationCheckBtn").click(
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
							idDupOk = true;
							$("#trueorfalse").attr('class',
									'glyphicon glyphicon-ok');

						} else {
							alert("이미 있는 ID 입니다.");
							idDupOk = false;
							$("#trueorfalse").attr('class',
									'glyphicon glyphicon-false');
						}

					},
					error : function(request, status, error) {
						alert("요청 실패!\n잠시 후 다시 시도해 주세요");
					}
				});
			} else {
				alert("아이디를 확인하세요");
			}

		});
//이메일 중복확인
$("#emailDuplicationCheckBtn").click(
		function(event) {
			event.preventDefault();
			if (emailCheck($("#email").val())) {
				$.ajax({
					type : "get",
					url : "findEmail",
					data : {
						email : $("#email").val()
					},
					success : function(res) {

						if (res == "true") {
							alert("사용가능한 email 입니다.");
							emailDupOk = true;
							$("#emailtrueorfalse").attr('class',
									'glyphicon glyphicon-ok');

						} else {
							alert("이미 있는 email 입니다.");
							emailDupOk = false;
							$("#emailtrueorfalse").attr('class',
									'glyphicon glyphicon-false');
						}

					},
					error : function(request, status, error) {
						alert("요청 실패!\n잠시 후 다시 시도해 주세요");
					}
				});
			} else {
				alert("이메일을 확인하세요");
			}

		});