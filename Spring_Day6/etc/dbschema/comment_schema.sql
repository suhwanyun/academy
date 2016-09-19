drop table Tuser;
drop table tcomment;
drop sequence SEQ_COMMENT;

create table Tuser(
  User_id varchar2(20) primary key,
  Pass varchar2(20) not null);
  
create table tcomment(
  Comment_no number primary key,
  User_id varchar2(20) not null,
  content varchar2(500) not null);

alter table tcomment add constraint tcomment_tuser_fk
  foreign key(User_id) references tuser(User_id);

create SEQUENCE SEQ_COMMENT start with 1;

create or replace trigger tri_comment_no before insert on tcomment
for each row
begin
  select SEQ_COMMENT.nextval
    into :new.Comment_no
    from dual;
end;