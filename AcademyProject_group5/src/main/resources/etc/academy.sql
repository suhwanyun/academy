
/* Drop Tables */

DROP TABLE CancelLecture CASCADE CONSTRAINTS;
DROP TABLE LectureApply CASCADE CONSTRAINTS;
DROP TABLE LectureNotice CASCADE CONSTRAINTS;
DROP TABLE LectureTime CASCADE CONSTRAINTS;
DROP TABLE Lecture CASCADE CONSTRAINTS;
DROP TABLE Manager CASCADE CONSTRAINTS;
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
	cancel_date date NOT NULL,
	lecture_time_id number NOT NULL,
	CONSTRAINT CancelLecture_PK PRIMARY KEY (cancel_date, lecture_time_id)
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
	lecture_notice_id number NOT NULL,
	notice_time date NOT NULL,
	lecture_id number NOT NULL,
	lecture_class number NOT NULL,
	notice_type varchar2(10) NOT NULL,
	notice_title varchar2(100) NOT NULL,
	notice_content varchar2(2000),
	PRIMARY KEY (lecture_notice_id)
);


CREATE TABLE LectureTime
(
	lecture_time_id number NOT NULL,
	lecture_id number NOT NULL,
	lecture_class number NOT NULL,
	lecture_start number NOT NULL,
	lecture_end number NOT NULL,
	lecture_place varchar2(50) NOT NULL,
	lecture_week number NOT NULL,
	is_temp_date date,
	PRIMARY KEY (lecture_time_id)
);


CREATE TABLE Manager
(
	manager_id varchar2(10) NOT NULL,
	manager_pass varchar2(20) NOT NULL,
	manager_type varchar2(10) NOT NULL,
	PRIMARY KEY (manager_id)
);

CREATE TABLE MileageProduct
(
	product_id number NOT NULL,
	product_name varchar2(20) NOT NULL,
	product_cost number NOT NULL,
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
	noti_content varchar2(2000),
	PRIMARY KEY (noti_id)
);


CREATE TABLE NotificationSetting
(
	noti_type varchar2(10) NOT NULL,
	user_id varchar2(10) NOT NULL,
	noti_on number NOT NULL,
	week_code char(1) NOT NULL,
	noti_hour number NOT NULL,
	noti_min number NOT NULL,
	CONSTRAINT NotificationSetting_PK PRIMARY KEY (noti_type, user_id)
);


CREATE TABLE Posting
(
	posting_id number NOT NULL,
	posting_type varchar2(50) NOT NULL,
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
	posting_type varchar2(50) NOT NULL,
	user_id varchar2(10),
	comment_parent_id number,
	comment_time date NOT NULL,
	comment_content varchar2(1000) NOT NULL,
	PRIMARY KEY (comment_id)
);


CREATE TABLE Recommend
(
	posting_id number NOT NULL,
	posting_type varchar2(50) NOT NULL,
	user_id varchar2(10) NOT NULL,
	CONSTRAINT Recommend_PK PRIMARY KEY (posting_id, posting_type, user_id)
);


CREATE TABLE Term
(
	term_start date NOT NULL,
	term_end date NOT NULL,
	PRIMARY KEY (term_start)
);


CREATE TABLE UserData
(
	user_id varchar2(10) NOT NULL,
	user_pass varchar2(64) NOT NULL,
	user_name varchar2(15) NOT NULL,
	user_mileage number NOT NULL,
	phone_id varchar2(200),
	-- 분실시 활용
	email varchar2(50) NOT NULL UNIQUE,
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
  ADD CONSTRAINT "LectureTime FK ref LectureTime"
	FOREIGN KEY (lecture_time_id)
	REFERENCES LectureTime (lecture_time_id)
	ON CASCADE
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



