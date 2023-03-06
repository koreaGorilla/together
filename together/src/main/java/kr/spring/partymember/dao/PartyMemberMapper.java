package kr.spring.partymember.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import kr.spring.partymember.vo.PartyMemberVO;

@Mapper
public interface PartyMemberMapper {
	//멤버 리스트
	public List<PartyMemberVO> selectList(
			Map<String,Object> map);
	
	public int selectRowCount(Map<String,Object> map);

	//가입신청
	@Insert("INSERT INTO party_member (partymem_num, party_auth, party_num, mem_num) VALUES (party_mem_seq.nextval,#{party_auth},#{party_num},#{mem_num})")
	public void insertPartyMember(PartyMemberVO partyMemberVO);
	
	//가입승인
	@Update("UPDATE Party_Member SET party_auth=#{party_auth} WHERE partymem_num=#{partymem_num}")
	public void approvePartyMember(Integer partymem_num, Integer party_auth);
	//가입거부
	@Delete("DELETE FROM party_member WHERE partymem_num=#{partymem_num}")
	public void rejectPartyMember(Integer partymem_num);
	//가입추방
	@Delete("DELETE FROM party_member WHERE partymem_num=#{partymem_num}")
	public void banPartyMember(Integer partymem_num);

	//나가기
	@Delete("DELETE FROM party_member WHERE partymem_num=#{partymem_num}")
	public void deletePartyMember(Integer partymem_num);
}
