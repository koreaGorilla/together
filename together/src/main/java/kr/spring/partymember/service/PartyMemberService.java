package kr.spring.partymember.service;

import org.apache.ibatis.annotations.Update;

import kr.spring.partymember.vo.PartyMemberVO;

public interface PartyMemberService {
	//가입신청
	public void updatePartyMember(PartyMemberVO partyMemberVO);
	//가입거부
	public void updateByPartyAdmin(PartyMemberVO PartyMemberVO);
	//가입초대
	
	//나가기
	public void deletePartyMember(Integer mem_num);
}
