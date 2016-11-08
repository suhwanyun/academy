$(document).scroll(function(){
	var con = $("#moveToStartBtn");
	var position = $(window).scrollTop();
	if(position > 250){ con.fadeIn(500); }
	else if(position < 250){ con.fadeOut(500); }
});