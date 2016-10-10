var isChanged = true;
	function idCheck(x){
		var ID_PATTERN = /^[a-z][a-z0-9_$@#]{3,11}$/i;
		if(ID_PATTERN.test(x)){
			$("#userId").attr('class', 'true');
			return true;
		}else{
			$("#userId").attr('class', 'false');
			return false;
		}
			
	}
	function passCheck(x){
		var PASS_PATTERN = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/;
		if(PASS_PATTERN.test(x)){
			$("#userPass").attr('class', 'true');
			return true;
		}else
			$("#userPass").attr('class', 'false');
			return false;
	}
	function nameCheck(x){
		var NAME_PATTERN = /^[가-힣]{2,5}$/;
		if(NAME_PATTERN.test(x)){
			$("#userName").attr('class', 'true');
			return true;
		}else
			$("#userName").attr('class', 'false');
			return false;
	}
	function phoneCheck(x){
		var PHONE_PATTERN = /^[0-9]{10,11}$/;
		if(PHONE_PATTERN.test(x)){
			$("#phoneNum").attr('class', 'true');
			return true;
		}else
			$("#phoneNum").attr('class', 'false');
			return false;
	}
	function questionCheck(x){
		if(x.length>0&&x.length<=100){
			$("#passQuestion").attr('class', 'true');
			return true;
		}else
			$("#passQuestion").attr('class', 'false');
			return false;
	} 
	function anserCheck(x){
		if(x.length>0&&x.length<=20){
			$("#passAnswer").attr('class', 'true');
			return true;
		}else
			$("#passAnswer").attr('class', 'false');
			return false;
	}
		$("#joinBtn").click(function(event){
			
			
				if (isChanged&&idCheck($("#userId").val()) && passCheck($("#userPass").val()) 
						&& nameCheck($("#userName").val()) && phoneCheck($("#phoneNum").val())
						&& questionCheck($("#passQuestion").val()) && anserCheck($("#passAnswer").val()) == true) {
					alert("가입 성공");
				} else {
					event.preventDefault();
					alert("가입 실패");
				}

			});
		
	$("#userId").change(function(){
		isChanged = false;
		$("#trueorfalse").attr('class', 'button alt falseButton');
		if(idCheck($("#userId").val())){
			$("#userId").attr('class', 'true');
		}else{
			$("#userId").attr('class', 'false');
		}
	});
	$("#userPass").change(function(){
		isChanged = false;
		if(passCheck($("#userPass").val())){
			if($("#passCheck").val()==$("#userPass").val()){
				$("#userPass").attr('class', 'true');
				isChanged = true;
				}else{
					$("#passCheck").attr('class', 'false');
				}
		}else{
			$("#userPass").attr('class', 'false');
		}	
	});
	$("#passCheck").change(function(){
		isChanged = false;
		if(passCheck($("#userPass").val())&&$("#passCheck").val()==$("#userPass").val()){
			$("#passCheck").attr('class', 'true');
			isChanged = true;
		}else{
			$("#passCheck").attr('class', 'false');
		}
			
	});
	$("#userName").change(function(){
		isChanged = false;
		if(nameCheck($("#userName").val())){
			$("#userName").attr('class', 'true');
			isChanged = true;
		}else{
			$("#userName").attr('class', 'false');	
		}
			
	});
	$("#phoneNum").change(function(){
		isChanged = false;
		if(phoneCheck($("#phoneNum").val())){
			$("#phoneNum").attr('class', 'true');
			isChanged = true;
		}else{
			$("#phoneNum").attr('class', 'false');
		}
			
	});
	$("#passQuestion").change(function(){
		isChanged = false;
		if(questionCheck($("#passQuestion").val())){
			$("#passQuestion").attr('class', 'true');
			isChanged = true;
		}else{
			$("#passQuestion").attr('class', 'false');
		}
	});
	$("#passAnswer").change(function(){
		isChanged = false;
		if(anserCheck($("#passAnswer").val())){
			$("#passAnswer").attr('class', 'true');
			isChanged = true;
		}else{
			$("#passAnswer").attr('class', 'false');
		}
	});
	//아이디 중복확인
	$("#duplicationCheckBtn").click(
			function(event) {
				event.preventDefault();
				if(idCheck($("#userId").val())){
					$.ajax({
						type : "get",
						url : "findUser",
						data : {
							userId : $("#userId").val()
						},
						success : function(res) {

							if (res == "true") {
								alert("사용가능한 ID 입니다.");
								isChanged = true;
								$("#trueorfalse").attr('class', 'button alt trueButton');
								
							} else {
								alert("이미 있는 ID 입니다.");
								$("#trueorfalse").attr('class', 'button alt falseButton');
							}

						},
						error : function(request, status, error) {
							alert("code:" + request.status + "\n" + "message:"
									+ request.responseText + "\n" + "error:"
									+ error);
						}
					});
				}else{
					alert("아이디를 확인하세요");
				}
				

			});