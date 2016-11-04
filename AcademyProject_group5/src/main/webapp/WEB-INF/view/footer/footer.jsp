<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

</head>
<body>


<!-- Container (Services Section) -->

<div class="text-center">

<button type="button" class="btn btn-footer" style="background: #ccc;">
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
      <a href="/noti/notiSettingjsp"><span class="glyphicon glyphicon-dashboard"></span></a>
      <h4>Alarm</h4>
      <p>알람</p>
    </div>
    <div class="col-xs-4">
        <a href="/campus/campusMain"><span class="glyphicon glyphicon-education"></span></a>
      <h4>Study</h4>
      <p>학업</p>
    </div>
     <div class="col-xs-4">
      <a href="/foodMain"><span class="glyphicon glyphicon-cutlery"></span></a>
      <h4>Food</h4>
      <p>음식</p>
    </div>
  </div>
  <br><br>
  <div class="row">
    <div class="col-xs-4">
        <a href="/playMain"><span class="glyphicon glyphicon-music"></span></a>
      <h4>Play</h4>
      <p>오락</p>
    </div>
    <div class="col-xs-4">
      <a href="/placeMain"><span class="glyphicon glyphicon-plane"></span></a>
      <h4>Place</h4>
      <p>명소</p>
    </div>
    <div class="col-xs-4">
       <a href="/mileageMain"><span class="glyphicon glyphicon-wrench"></span></a>
       <h4 style="color:#303030;">Mileage</h4>
      <p>마일리지</p>
    </div>
  </div>
</div>
</div>

</body>
<script>
$(document).ready(function(){
    $(".btn-footer").click(function(){
        $("#collapse").collapse('toggle');
    });

});

</script>
</html>
