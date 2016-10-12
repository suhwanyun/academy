CREATE SEQUENCE SEQ_noti_no INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_lecture_no INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_mileage_no INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_posting_food_no INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_posting_game_no INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_posting_attraction_no INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_posting_lecture_no INCREMENT BY 1 START WITH 1;

CREATE OR REPLACE TRIGGER TRI_noti_no BEFORE INSERT ON Notifications
for each row
BEGIN
  select SEQ_noti_no.nextval
    into :new.noti_id
    from dual; 
END;
/

CREATE OR REPLACE TRIGGER TRI_lecture_no BEFORE INSERT ON Lecture
for each row
BEGIN
  select SEQ_lecture_no.nextval
    into :new.lecture_id
    from dual; 
END;
/

CREATE OR REPLACE TRIGGER TRI_mileage_no BEFORE INSERT ON MileageProduct
for each row
BEGIN
  select SEQ_mileage_no.nextval
    into :new.product_id
    from dual; 
END;
/

CREATE OR REPLACE TRIGGER TRI_posting_food_no BEFORE INSERT ON Posting
for each row
when (new.posting_type = 'food')
BEGIN
  select SEQ_posting_food_no.nextval
    into :new.posting_id
    from dual; 
END;
/

CREATE OR REPLACE TRIGGER TRI_posting_game_no BEFORE INSERT ON Posting
for each row
when (new.posting_type = 'play')
BEGIN
  select SEQ_posting_game_no.nextval
    into :new.posting_id
    from dual; 
END;
/

CREATE OR REPLACE TRIGGER TRI_posting_attraction_no BEFORE INSERT ON Posting
for each row
when (new.posting_type = 'attr')
BEGIN
  select SEQ_posting_attraction_no.nextval
    into :new.posting_id
    from dual; 
END;
/

CREATE OR REPLACE TRIGGER TRI_posting_lecture_no BEFORE INSERT ON Posting
for each row
when (LPAD(new.posting_type, 4) = 'lect')
BEGIN
  select SEQ_posting_attraction_no.nextval
    into :new.posting_id
    from dual; 
END;
/



