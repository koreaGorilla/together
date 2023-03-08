create table party_calendar(
	calendar_num number,
	party_num number not null,
	mem_num number not null,
	title varchar2(50) not null,
	content clob not null,
	start_date varchar2(20) not null,
	end_date varchar2(20) not null,
	start_time varchar2(20) not null,
	end_time varchar2(20) not null,
	location varchar2(100) not null,
	color varchar2(10) not null,
	constraint party_calendar_pk primary key (calendar_num),
	constraint party_calendar_fk1 foreign key (party_num) references party (party_num),
	constraint party_calendar_fk2 foreign key (mem_num) references member (mem_num)
); 

create sequence party_calendaer_seq;

create table participation(
	p_num number,
	calendar_num number not null,
	mem_num number not null,
	--0 : 참여, 1 : 미참여
	participate number(1) default 0 not null,
	constraint participation_pk primary key (p_num),
	constraint participation_fk1 foreign key (calendar_num) references party_calendar (calendar_num),
	constraint participation_fk2 foreign key (mem_num) references member (mem_num)
);

create sequence participation_seq;