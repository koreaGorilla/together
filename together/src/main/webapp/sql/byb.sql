--파티
create table party(
 party_num number,
 party_name varchar2(40) not null,
 party_content clob not null,
 party_filename varchar2(150),
 party_reg_date date default sysdate not null,
 party_reg_type number(1)not null,
 party_photo blob,
 party_photo_name varchar(100),
 mem_num number not null,
 party_hobby number not null,
 constraint party_pk primary key (party_num),
 constraint party_fk1 foreign key (mem_num) 
                          references member (mem_num)

);
create sequence party_seq;
--파티회원 관리
create table party_member(
 partymem_num number,
 party_auth number(1) default 0 not null,
 party_num number not null,
 mem_num number not null,
 partymem_reg_date date default sysdate not null,
 constraint party_member_pk primary key (partymem_num),
 constraint party_member_fk1 foreign key (party_num)
                          references party (party_num)
 constraint party_member_fk2 foreign key (mem_num)
                          references member (mem_num)
);
create sequence party_member_seq;