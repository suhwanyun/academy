function idCheck(x) {
	var ID_PATTERN = /^[a-z][a-z0-9_$@#]{3,11}$/i;
	if (ID_PATTERN.test(x)) {
		return true;
	} else {
		return false;
	}
}
function passCheck(x) {
	var PASS_PATTERN = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{6,20}$/;
	if (PASS_PATTERN.test(x)) {
		return true;
	} else {
		return false;
	}

}
function nameCheck(x) {
	var NAME_PATTERN = /^[가-힣]{2,5}$/;
	if (NAME_PATTERN.test(x)) {
		return true;
	} else {
		return false;
	}
}
function phoneCheck(x) {
	var PHONE_PATTERN = /^[0-9]{10,11}$/;
	if (PHONE_PATTERN.test(x)) {
		return true;
	} else {
		return false;
	}
}
function questionCheck(x) {
	if (x.length > 0 && x.length <= 100) {
		return true;
	} else {
		return false;
	}
}
function anserCheck(x) {
	if (x.length > 0 && x.length <= 20) {
		return true;
	} else {
		return false;
	}
}
