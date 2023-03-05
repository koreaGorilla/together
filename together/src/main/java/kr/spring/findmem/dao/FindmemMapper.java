package kr.spring.findmem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper
public interface FindmemMapper {
	
    
	@Select("SELECT nvl(m.mem_id,0) FROM member m LEFT OUTER JOIN member_detail d "
			  + "ON m.mem_num=d.mem_num WHERE d.mem_name=#{mem_name} and "
			  + "d.mem_cell=#{mem_cell}")
    public String findId(String mem_name, String mem_cell);
	
	@Select("SELECT nvl(d.mem_cell,0) FROM member_detail d LEFT OUTER JOIN member m "
			+ "ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id} and "
			+ "d.mem_cell=#{mem_cell}")
	public String findPw(String mem_id, String mem_cell);
    
	@Select("SELECT d.mem_pw FROM member_detail d LEFT OUTER JOIN member m "
			  + "ON d.mem_num=m.mem_num WHERE m.mem_id=#{mem_id} and d.mem_cell=#{mem_cell}")
    public String findPassword(String mem_id, String mem_cell);

}
