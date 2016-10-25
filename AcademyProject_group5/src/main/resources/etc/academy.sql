
/* Drop Tables */

DROP TABLE CancelLecture CASCADE CONSTRAINTS;
DROP TABLE LectureApply CASCADE CONSTRAINTS;
DROP TABLE LectureNotice CASCADE CONSTRAINTS;
DROP TABLE LectureTime CASCADE CONSTRAINTS;
DROP TABLE Lecture CASCADE CONSTRAINTS;
DROP TABLE Manager CASCADE CONSTRAINTS;
DROP TABLE Mileage CASCADE CONSTRAINTS;
DROP TABLE UserMileage CASCADE CONSTRAINTS;
DROP TABLE MileageProduct CASCADE CONSTRAINTS;
DROP TABLE Notifications CASCADE CONSTRAINTS;
DROP TABLE NotificationSetting CASCADE CONSTRAINTS;
DROP TABLE PostingComment CASCADE CONSTRAINTS;
DROP TABLE Recommend CASCADE CONSTRAINTS;
DROP TABLE Posting CASCADE CONSTRAINTS;
DROP TABLE Term CASCADE CONSTRAINTS;
DROP TABLE UserData CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE CancelLecture
(
	lecture_id number NOT NULL,
	lecture_class number NOT NULL,
	lecture_start number NOT NULL,
	cancel_date date NOT NULL,
	CONSTRAINT Cancel_PK PRIMARY KEY (lecture_id, lecture_class, lecture_start, cancel_date)
);


CREATE TABLE Lecture
(
	lecture_id number NOT NULL,
	lecture_class number NOT NULL,
	lecture_name varchar2(50) NOT NULL,
	professor_name varchar2(15) NOT NULL,
	CONSTRAINT Lecture_PK PRIMARY KEY (lecture_id, lecture_class)
);


CREATE TABLE LectureApply
(
	lecture_id number NOT NULL,
	user_id varchar2(10) NOT NULL,
	lecture_class number NOT NULL,
	is_president char(1) NOT NULL,
	right_end_time date,
	CONSTRAINT LectureApply_PK PRIMARY KEY (lecture_id, user_id)
);


CREATE TABLE LectureNotice
(
	notice_time date NOT NULL,
	lecture_id number NOT NULL,
	lecture_class number NOT NULL,
	notice_type varchar2(10) NOT NULL,
	notice_title varchar2(100) NOT NULL,
	notice_content varchar2(2000),
	CONSTRAINT LectureNotice_PK PRIMARY KEY (notice_time, lecture_id, lecture_class)
);


CREATE TABLE LectureTime
(
	lecture_id number NOT NULL,
	lecture_class number NOT NULL,
	lecture_start number NOT NULL,
	lecture_end number NOT NULL,
	lecture_place varchar2(50) NOT NULL,
	lecture_week number NOT NULL,
	is_temp_date date,
	CONSTRAINT LectureTime_PK PRIMARY KEY (lecture_id, lecture_class, lecture_start)
);


CREATE TABLE Manager
(
	manager_id varchar2(10) NOT NULL,
	manager_pass varchar2(20) NOT NULL,
	manager_type varchar2(10) NOT NULL,
	PRIMARY KEY (manager_id)
);


CREATE TABLE Mileage
(
	mile_name varchar2(20) NOT NULL,
	mile_value number NOT NULL,
	PRIMARY KEY (mile_name)
);


CREATE TABLE MileageProduct
(
	product_id number NOT NULL,
	product_name varchar2(20) NOT NULL,
	product_cost number NOT NULL,
	product_remain number NOT NULL,
	product_content varchar2(1000),
	product_imgfile varchar2(50),
	PRIMARY KEY (product_id)
);


CREATE TABLE Notifications
(
	noti_id number NOT NULL,
	user_id varchar2(10) NOT NULL,
	noti_type varchar2(10) NOT NULL,
	noti_title varchar2(100) NOT NULL,
	-- 게시판 용
	noti_content varchar2(2000),
	PRIMARY KEY (noti_id)
);


CREATE TABLE NotificationSetting
(
	noti_type varchar2(10) NOT NULL,
	user_id varchar2(10) NOT NULL,
	noti_on number NOT NULL,
	next_noti_time date NOT NULL,
	-- 하루 단위
	noti_time_interval number NOT NULL,
	noti_hour NOT NULL,
	noti_min NOT NULL,
	CONSTRAINT NotificationSetting_PK PRIMARY KEY (noti_type, user_id)
);


CREATE TABLE Posting
(
	posting_id number NOT NULL,
	posting_type varchar2(10) NOT NULL,
	user_id varchar2(10) NOT NULL,
	posting_time date NOT NULL,
	posting_hits number NOT NULL,
	posting_recommend number NOT NULL,
	posting_title varchar2(100) NOT NULL,
	posting_content varchar2(4000) NOT NULL,
	posting_photo varchar2(100),
	CONSTRAINT posting_PK PRIMARY KEY (posting_id, posting_type)
);


CREATE TABLE PostingComment
(
	comment_id number NOT NULL,
	posting_id number NOT NULL,
	posting_type varchar2(10) NOT NULL,
	user_id varchar2(10),
	comment_parent_id number,
	comment_time date NOT NULL,
	comment_content varchar2(1000) NOT NULL,
	PRIMARY KEY (comment_id)
);


CREATE TABLE Recommend
(
	posting_id number NOT NULL,
	posting_type varchar2(10) NOT NULL,
	user_id varchar2(10) NOT NULL,
	CONSTRAINT Recommend_PK PRIMARY KEY (posting_id, posting_type, user_id)
);


CREATE TABLE Term
(
	term_year number NOT NULL,
	-- 1 또는 2
	term_classify number NOT NULL,
	term_start date NOT NULL,
	term_end date NOT NULL,
	CONSTRAINT term_PK PRIMARY KEY (term_year, term_classify)
);


CREATE TABLE UserData
(
	user_id varchar2(10) NOT NULL,
	user_pass varchar2(64) NOT NULL,
	user_name varchar2(15) NOT NULL,
	user_mileage number NOT NULL,
	phone_id varchar2(200),
	-- 분실시 활용
	phone_num varchar2(20) NOT NULL,
	pass_question varchar2(100) NOT NULL,
	pass_answer varchar2(20) NOT NULL,
	PRIMARY KEY (user_id)
);


CREATE TABLE UserMileage
(
	user_id varchar2(10) NOT NULL,
	product_id number NOT NULL,
	product_remain number NOT NULL,
	CONSTRAINT UserMileage_PK PRIMARY KEY (user_id, product_id)
);



/* Create Foreign Keys */

ALTER TABLE LectureApply
	ADD CONSTRAINT "LectureApply FK ref Lecture"
	FOREIGN KEY (lecture_id, lecture_class)
	REFERENCES Lecture (lecture_id, lecture_class)
;


ALTER TABLE LectureNotice
	ADD CONSTRAINT "LectureNotice FK ref Lecture"
	FOREIGN KEY (lecture_id, lecture_class)
	REFERENCES Lecture (lecture_id, lecture_class)
;


ALTER TABLE LectureTime
	ADD CONSTRAINT "LectureTime FK ref Lecture"
	FOREIGN KEY (lecture_id, lecture_class)
	REFERENCES Lecture (lecture_id, lecture_class)
;


ALTER TABLE CancelLecture
	ADD CONSTRAINT "CancelLecture FK ref Lecture"
	FOREIGN KEY (lecture_id, lecture_class, lecture_start)
	REFERENCES LectureTime (lecture_id, lecture_class, lecture_start)
;


ALTER TABLE UserMileage
	ADD CONSTRAINT "UserMileage FK ref MileageProd"
	FOREIGN KEY (product_id)
	REFERENCES MileageProduct (product_id)
;


ALTER TABLE Notifications
	ADD CONSTRAINT "Noti FK ref NotiSetting"
	FOREIGN KEY (user_id, noti_type)
	REFERENCES NotificationSetting (user_id, noti_type)
;


ALTER TABLE PostingComment
	ADD CONSTRAINT "PostingComm FK ref Posting"
	FOREIGN KEY (posting_id, posting_type)
	REFERENCES Posting (posting_id, posting_type)
;


ALTER TABLE Recommend
	ADD CONSTRAINT "Recommend FK ref Posting"
	FOREIGN KEY (posting_id, posting_type)
	REFERENCES Posting (posting_id, posting_type)
;


ALTER TABLE PostingComment
	ADD CONSTRAINT "PostingComm FK ref PostingComm"
	FOREIGN KEY (comment_parent_id)
	REFERENCES PostingComment (comment_id)
;


ALTER TABLE LectureApply
	ADD CONSTRAINT "LectureApply FK ref UserData"
	FOREIGN KEY (user_id)
	REFERENCES UserData (user_id)
;


ALTER TABLE NotificationSetting
	ADD CONSTRAINT "NotiSetting FK ref UserData"
	FOREIGN KEY (user_id)
	REFERENCES UserData (user_id)
;


ALTER TABLE Posting
	ADD CONSTRAINT "Posting FK ref UserData"
	FOREIGN KEY (user_id)
	REFERENCES UserData (user_id)
;


ALTER TABLE PostingComment
	ADD CONSTRAINT "PostingComm FK ref UserData"
	FOREIGN KEY (user_id)
	REFERENCES UserData (user_id)
;


ALTER TABLE Recommend
	ADD CONSTRAINT "Recommend FK ref UserData"
	FOREIGN KEY (user_id)
	REFERENCES UserData (user_id)
;


ALTER TABLE UserMileage
	ADD CONSTRAINT "UserMileage FK ref UserData"
	FOREIGN KEY (user_id)
	REFERENCES UserData (user_id)
;



/* Comments */

COMMENT ON COLUMN Notifications.noti_content IS '게시판 용';
COMMENT ON COLUMN NotificationSetting.noti_time_interval IS '하루 단위';
COMMENT ON COLUMN Term.term_classify IS '1 또는 2';
COMMENT ON COLUMN UserData.phone_num IS '분실시 활용';



