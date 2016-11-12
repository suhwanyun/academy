
function lectureIdCheck(x){
	var lectureId_PATTERN= /^[0-9]{1,15}$/;
	if (lectureId_PATTERN.test(x)) {
		return true;
	} else {
		return false;
	}
}
function lectureNameCheck(x){
	if (x.length>0 && x.length <=30) {
		return true;
	} else {
		return false;
	}
}
function professorNameCheck(x){
	var ko_NAME_PATTERN = /^[가-힣]{2,5}$/;
	var en_NAME_PATTERN = /^[a-zA-Z]{1,15}$/;
	if (ko_NAME_PATTERN.test(x) || en_NAME_PATTERN.test(x)) {
		return true;
	} else {
		return false;
	}
}
function lectureStartEndCheck(s, e){
	var NUM_PATTERN = /^[1-9]{1}$/;	
	if(NUM_PATTERN.test(s)&&NUM_PATTERN.test(e)){
		if(s<=e){
			return true;
		}
		return false;
	}else{
		return false;
	}
}
function lecutrePlaceCheck(x){
	if (x.length > 0 && x.length <= 15) {
		return true;
	} else {
		return false;
	}
}
function weekCheck(x){
	var NUM_PATTERN = /^[1-7]{1}$/;	
	if(NUM_PATTERN.test(x)){
		return true;
	}else{
		return false;
	}
}

