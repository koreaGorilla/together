--리뷰 테이블
create table review(
 r_num number,
 r_date date default sysdate not null,
 r_modfiy_date date,
 party_name varchar2(100) not null,
 r_content varchar2(4000) not null,
 r_photo blob,
 r_photoname varchar2(100),
 mem_num number not null,
 constraint review_pk primary key(r_num),
 constraint review_fk2 foreign key (mem_num) references member(mem_num)
);

create sequence review_seq;

--리뷰 좋아요 테이블
create table review_fav(
 r_fav_num number,
 r_num number not null,
 mem_num number not null,
 constraint review_fav_pk primary key(r_fav_num),
 constraint review_fav_fk1 foreign key(r_num) references review(r_num),
 constraint review_fav_fk2 foreign key(mem_num) references member(mem_num) 
);

create sequence reviewfav_seq;

--파티 좋아요 테이블
create table party_fav(
 p_fav_num number,
 party_num number not null,
 mem_num number not null,
 constraint party_fav_pk primary key(p_fav_num),
 constraint party_fav_fk1 foreign key(party_num) references party(party_num),
 constraint party_fav_fk2 foreign key(mem_num) references member(mem_num) 
);

create sequence partyfav_seq;

--리뷰 댓글 테이블
create table review_comments(
 c_num number,
 c_date date default sysdate not null,
 c_modify_date date,
 c_content varchar2(900) not null,
 mem_num number not null,
 r_num number not null,
 constraint review_comments_pk primary key(c_num),
 constraint review_comments_fk1 foreign key(mem_num) references member(mem_num),
 constraint review_comments_fk2 foreign key(r_num) references review(r_num)
);

create sequence reviewcomm_seq;