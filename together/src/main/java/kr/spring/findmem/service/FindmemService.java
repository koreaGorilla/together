package kr.spring.findmem.service;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

public interface FindmemService {
	
    
    public String findId(String mem_name, String mem_cell);
    
    public String findPassword(String mem_id, String mem_cell);

    public String findPw(String mem_id, String mem_cell);
}
