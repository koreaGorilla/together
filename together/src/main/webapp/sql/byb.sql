--파티
create table party(
 party_num number,
 party_name varchar2(40) not null,
 party_content clob not null,
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
