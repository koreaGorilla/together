package kr.spring.partymember.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import kr.spring.partymember.vo.PartyMemberVO;

public interface PartyMemberService {
	//멤버 리스트
	public List<PartyMemberVO> selectList(
			Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);

	//가입신청
	public void insertPartyMember(PartyMemberVO partyMemberVO);
	//가입승인
	public void approvePartyMember(Integer partymem_num);
	//가입거부
	public void rejectPartyMember(Integer partymem_num);
	//가입추방
	public void banPartyMember(Integer partymem_num);

	//나가기
	public void deletePartyMember(Integer partymem_num);
}
