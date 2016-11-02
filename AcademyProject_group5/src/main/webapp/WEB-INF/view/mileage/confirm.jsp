<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마일리지 구매 확정</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/header/header.jsp" />
	<div class="container">
		<div class="text-center">
		<h1>내 상품 보관함</h1>
			<p>(사용하려는 상품을 클릭하시면 확인창이 뜹니다.)</p>
		<img
				class="img-circle img-thumbnail" 
				src="/images/mil02.png" alt="" 
				data-toggle="modal" data-target="#myModal">
		<img
				class="img-circle img-thumbnail imgMar"
				src="/images/mil01.png" alt="" 
				data-toggle="modal" data-target="#myModal">
		
		
		<a class="btn btn-link btn-lg col-xs-6" ><span>CU 모바일 상품권</span></a>
		<a class="btn btn-link btn-lg col-xs-6" ><span>아메리카노</span></a> 
			
	</div>
	<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">상품 사용</h4>
        </div>
        <div class="modal-body">
          <p>점원에게 보여주세요</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>	
        </div>
      </div>
      
    </div>
  </div>
	
	</div>

</body>
</html>