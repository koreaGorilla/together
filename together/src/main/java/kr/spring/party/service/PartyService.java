package kr.spring.party.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.MemberVO;
import kr.spring.party.vo.PartyVO;
import kr.spring.partymember.vo.PartyMemberVO;

public interface PartyService {
	public List<PartyVO> selectList(
			Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertParty(PartyVO party,PartyMemberVO member);
	public PartyVO selectParty(Integer party_num);

	public PartyVO selectPartyDetail(Integer party_num);
	
	 public List<PartyVO> selectMainPartyList();
	
	
	
}
