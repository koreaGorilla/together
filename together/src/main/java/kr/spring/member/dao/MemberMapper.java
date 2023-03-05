package kr.spring.member.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
   //회원관리 - 일반회원
   
   @Select("SELECT member_seq.nextval FROM dual")
   public int selectMem_num();
   @Insert("INSERT INTO member (mem_num,mem_id) VALUES (#{mem_num},#{mem_id})")
   public void insertMember(MemberVO member);
   @Insert("INSERT INTO member_detail (mem_num,"
		   + "mem_name,mem_pw,mem_cell,mem_email,mem_zipcode,mem_address1,"
		   + "mem_address2) VALUES (#{mem_num},"
		   + "#{mem_name},#{mem_pw},#{mem_cell},#{mem_email},"
		   + "#{mem_zipcode},#{mem_address1},#{mem_address2})")
   public void insertMember_detail(MemberVO member);
   public MemberVO selectMember(Integer mem_num);
   public void updateMember(MemberVO member);
   public void updateMember_detail(MemberVO member);
   public void updatePassword(MemberVO member);
   public void deleteMember(Integer mem_num);
   public void deleteMember_detail(Integer mem_num);
   @Select("SELECT m.mem_num,m.mem_id,m.mem_auth,"
		   + "d.mem_pw,d.mem_name,d.mem_email FROM "
		   + "member m LEFT OUTER JOIN member_detail d "
		   + "ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
   public MemberVO selectCheckMember(String id);
}



