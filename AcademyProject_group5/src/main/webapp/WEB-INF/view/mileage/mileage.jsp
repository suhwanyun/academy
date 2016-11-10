<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>마일리지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container">
		<div class="text-center">
			<h1>물품 구매</h1>
			<p>원하는 제품의 이름을 선택하세요</p>


			<img class="img-circle img-thumbnail" src="/images/mil02.png" alt="" />
			<img class="img-circle img-thumbnail imgMar" src="/images/mil01.png"
				alt="" /> <a id="mil1" class="btn btn-link btn-lg col-xs-6"><span>CU
					모바일 상품권</span></a> <a id="mil2" class="btn btn-link btn-lg col-xs-6"><span>아메리카노</span></a>





			<img class="img-circle img-thumbnail" src="/images/mil03.png" alt="" />
			<img class="img-circle img-thumbnail imgMar" src="/images/mil04.png"
				alt="" /> <a id="mil3" class="btn btn-link btn-lg col-xs-6"><span>허쉬초콜릿드링크</span></a>

			<a id="mil4" class="btn btn-link btn-lg col-xs-6"><span>ABC초콜릿</span></a>


			<img class="img-circle img-thumbnail" src="/images/mil05.png" alt="" />
			<img class="img-circle img-thumbnail imgMar" src="/images/mil06.png"
				alt="" /> <a id="mil5" class="btn btn-link btn-lg col-xs-6"><span>초코우유</span></a>
			<a id="mil6" class="btn btn-link btn-lg col-xs-6"><span>비타500</span></a>
		</div>

		<div style="margin-top: 5%;">
			<button id="confirmBtn" class="myButton size8mar">구매 확정</button>
			<button class="myButton size4">취소</button>
		</div>
	</div>

	<jsp:include page="/WEB-INF/view/footer/footer.jsp" />
</body>
<script>
$("#mil1").click(
		function(){
			$("#mil1").attr('class','btn btn-red btn-lg col-xs-6');
			
		});
$("#mil2").click(
		function(){
			$("#mil2").attr('class','btn btn-red btn-lg col-xs-6');
			
		});
$("#mil3").click(
		function(){
			$("#mil3").attr('class','btn btn-red btn-lg col-xs-6');
			
		});
$("#mil4").click(
		function(){
			$("#mil4").attr('class','btn btn-red btn-lg col-xs-6');
			
		});
$("#mil5").click(
		function(){
			$("#mil5").attr('class','btn btn-red btn-lg col-xs-6');
			
		});
$("#mil6").click(
		function(){
			$("#mil6").attr('class','btn btn-red btn-lg col-xs-6');
			
		});		

	$("#confirmBtn").click(
		function(){
			
			if(confirm("구입하시겠습니까?")==true){
				//승일아 빨간색으로 버튼이 바뀌면 그거에 대해서 구매 확정을 누르면 컨펌 창으로 넘어가게하면되
				
			}
		});
</script>
</html>