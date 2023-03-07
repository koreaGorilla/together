--공지사항
create table notice(
notice_num number not null,
notice_title varchar2(50) not null,
notice_content clob not null,
notice_file blob,
notice_filename varchar2(100),
notice_date date default sysdate not null,
notice_modifydate date,
notice_hit number(8) default 0 not null,
ip varchar2(40) not null,
mem_num number,
constraint notice_pk primary key (notice_num),
constraint notice_fk foreign key (mem_num) references member (mem_num)
);
create sequence notice_seq;

--이벤트
create table event(
event_num number not null,
event_title varchar2(50) not null,
event_content clob not null,
event_date date default sysdate not null,
event_start varchar2(20),
event_end varchar2(20),
estart_time varchar2(20),
eend_time varchar2(20),
event_file blob,
event_filename varchar2(100),
event_hit number(8) default 0 not null,
mem_num number,
constraint event_pk primary key (event_num),
constraint event_fk foreign key (mem_num) references member (mem_num)
);
create sequence event_seq;



--이벤트 댓글
create table event_comments(
ec_num number not null,
ec_content varchar2(900) not null,
ec_date date not null,
ec_modifydate date,
mem_num number,
event_num number,
constraint event_comments_pk primary key (ec_num),
constraint event_comments_fk1 foreign key (mem_num) references member (mem_num),
constraint event_comments_fk2 foreign key (event_num) references event (event_num) 
);
create sequence event_comments_seq;
