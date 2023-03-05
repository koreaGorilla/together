package kr.spring.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface MemberMapper {
   //회원관리 - 일반회원
   @Select("SELECT m.mem_num,m.mem_id,m.mem_auth,"
           + "d.mem_pw,d.mem_name,d.mem_email FROM "
           + "member m LEFT OUTER JOIN member_detail d "
           + "ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
   public MemberVO selectCheckMember(String id);
}



