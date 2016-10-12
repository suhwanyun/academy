var passSame = false;
$("#userPass").change(function() {
	if (passCheck($("#userPass").val())) {
		$("#userPass").attr('class', 'true');
		if ($("#passCheck").val() == $("#userPass").val()) {
			$("#passCheck").attr('class', 'true');
			passSame = true;
		} else {
			$("#passCheck").attr('class', 'false');
			passSame = false;
		}
	} else {
		$("#userPass").attr('class', 'false');
	}
});
$("#passCheck").change(
		function() {
			if (passCheck($("#userPass").val())
					&& $("#passCheck").val() == $("#userPass").val()) {
				$("#passCheck").attr('class', 'true');
				passSame = true;
			} else {
				$("#passCheck").attr('class', 'false');
				passSame = false;
			}

		});

$("#phoneNum").change(function() {
	if (phoneCheck($("#phoneNum").val())) {
		$("#phoneNum").attr('class', 'true');
	} else {
		$("#phoneNum").attr('class', 'false');
	}

});
$("#passQuestion").change(function() {
	if (questionCheck($("#passQuestion").val())) {
		$("#passQuestion").attr('class', 'true');
	} else {
		$("#passQuestion").attr('class', 'false');
	}
});
$("#passAnswer").change(function() {
	if (anserCheck($("#passAnswer").val())) {
		$("#passAnswer").attr('class', 'true');
	} else {
		$("#passAnswer").attr('class', 'false');
	}
});
$("#infoUpdate").click(
		function(event) {
			if ($("#userPass").val().length == 0 && $("#passCheck").val().length == 0) {
				if (phoneCheck($("#phoneNum").val())
						&& questionCheck($("#passQuestion").val())
						&& anserCheck($("#passAnswer").val()) == true) {
				} else {
					event.preventDefault();
					alert("잘못된 부분을 수정해주세요");
				}
			}else{
				if (passSame && passCheck($("#userPass").val())
						&& phoneCheck($("#phoneNum").val())
						&& questionCheck($("#passQuestion").val())
						&& anserCheck($("#passAnswer").val()) == true) {
				} else {
					event.preventDefault();
					alert("잘못된 부분을 수정해주세요");
				}
			}
			
		});