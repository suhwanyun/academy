<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

</head>
<body>


<!-- Container (Services Section) -->

<jsp:include page="../message.jsp" />
<div class="text-center">

<button type="button" class="btn btn-info btn-footer">
          <span class="glyphicon glyphicon-info-sign"></span> 
        </button>
      
</div>

<div id="collapse" class="jumbotron collapse">
<div class="container text-center">
  <h2>SERVICES</h2>
  <h4>What we offer</h4>
  <br>
  <div class="row">
    <div class="col-xs-4">
      <span class="glyphicon glyphicon-dashboard"></span>
      <h4>Alarm</h4>
      <p>알람</p>
    </div>
    <div class="col-xs-4">
      <span class="glyphicon glyphicon-education"></span>
      <h4>Study</h4>
      <p>학업</p>
    </div>
    <div class="col-xs-4">
      <span class="glyphicon glyphicon-cutlery"></span>
      <h4>Food</h4>
      <p>음식</p>
    </div>
  </div>
  <br><br>
  <div class="row">
    <div class="col-xs-4">
      <span class="glyphicon glyphicon-music"></span>
      <h4>Play</h4>
      <p>오락</p>
    </div>
    <div class="col-xs-4">
      <span class="glyphicon glyphicon-plane"></span>
      <h4>Place</h4>
      <p>명소</p>
    </div>
    <div class="col-xs-4">
      <span class="glyphicon glyphicon-wrench"></span>
      <h4 style="color:#303030;">Mileage</h4>
      <p>마일리지</p>
    </div>
  </div>
</div>
</div>
</div>
</body>
<script>
$(document).ready(function(){
    $(".btn-info").click(function(){
        $("#collapse").collapse('toggle');
    });

});

</script>
</html>
