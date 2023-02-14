create table party_member(
 partymem_num number not null,
 party_auth not null,
 partymem_reg_date date default sysdate not null,
 party_num number not null,
 mem_num number not null,
 constraint party_member_pk primary key (partymem_num),
 constraint party_member_fk1 foreign key (party_num) references party (party_num),
 constraint party_member_fk2 foreign key (mem_num) references mem_num (mem_num)
);