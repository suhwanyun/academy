$("#loginBtn").click(
		function() {
			$.ajax({
				type : "post",
				url : "/AcedemyProject_group5/login",
				data : {
					userId : $("#userId").val(),
					userPass : $("#userPass").val()
				},
				success : function(res) {
					if (res == "true") {
						$(location).attr('href', "/AcedemyProject_group5/main");
					} else {
						alert("아이디, 비밀번호를 확인하세요");
					}
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}
			});
		});

$("#searchBtn").click(function() {

	$(location).attr('href', "/AcedemyProject_group5/searchidpw");
});
$("#joinBtn").click(function() {
	$(location).attr('href', "/AcedemyProject_group5/joinjsp");
});
