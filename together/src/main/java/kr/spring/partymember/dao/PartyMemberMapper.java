package kr.spring.partymember.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import kr.spring.partymember.vo.PartyMemberVO;

public interface PartyMemberMapper {
	//가입신청
	@Insert("INSERT INTO party_member (partymem_num, party_auth, party_num, mem_num) VALUES (#{partymem_num},#{party_auth},#{party_num},#{mem_num})")
	public void insertPartyMember(PartyMemberVO partyMemberVO);
	
	//가입거부
	@Update("UPDATE Party_Member SET party_auth=#{party_auth} WHERE partymem_num=#{partymem_num}")
	public void updateByPartyAdmin(PartyMemberVO PartyMemberVO);
	//가입초대
	
	//나가기
	@Delete("DELETE FROM party_member WHERE partymem_num=#{partymem_num}")
	public void deletePartyMember(Integer mem_num);
}
