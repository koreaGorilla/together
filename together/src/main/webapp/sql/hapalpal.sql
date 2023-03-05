--회원관리
create table member(
 mem_num number,
 mem_id varchar2(12) unique not null,
 mem_auth number(1) default 2 not null, --0탈퇴회원,1정지회원,2일반회원,9관리자
 constraint member_pk primary key (mem_num)
);

create table member_detail(
 mem_num number,
 mem_name varchar2(30) not null,
 mem_pw varchar2(20) not null,
 mem_cell varchar2(15) not null,
 mem_email varchar2(50) not null,
 mem_zipcode varchar2(5) not null,
 mem_address1 varchar2(90) not null,
 mem_address2 varchar2(90) not null,
 hobby varchar2(20),
 photo blob,
 photo_name varchar2(100),
 reg_date date default sysdate not null,
 modify_date date,
 constraint member_detail_pk primary key (mem_num),
 constraint member_detail_fk1 foreign key (mem_num)
                          references member (mem_num)
);

create sequence member_seq;












































