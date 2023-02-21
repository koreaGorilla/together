--파티 채팅
create table party_chat(
	chat_num number,
	message varchar2(300) not null,
	timestamp Date default sysdate not null,
	party_num number not null,
	mem_num number not null,
	constraint party_chat_pk primary key (chat_num),
	constraint party_chat_fk foreign key (party_num)
                          references party (party_num),
	constraint party_chat_fk2 foreign key (mem_num)
                          references member (mem_num)
);
create sequence party_chat_seq;

--메시지 읽기
create table party_chat_read(
 party_num number not null,
 chat_num number not null,
 mem_num number not null,
 constraint read_fk foreign key (party_num)
              references party (party_num),
 constraint read_fk2 foreign key (chat_num)
                     references party_chat (chat_num),
 constraint read_fk3 foreign key (mem_num)
                     references member (mem_num)           
);