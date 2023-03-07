package kr.spring.party.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.MemberVO;
import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyVO;
import kr.spring.partymember.vo.PartyMemberVO;

public interface PartyService {
	public List<PartyVO> selectList(
			Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertParty(PartyVO party,PartyMemberVO member);
	public PartyVO selectParty(Integer party_num);

	public PartyVO selectPartyDetail(Integer party_num);

	public PartyMemberVO selectPartyDetailForAuth(Integer party_num, Integer mem_num);

	public List<PartyVO> selectMainPartyList();

	public List<PartyMemberVO> selectPartyMember(Integer party_num);
	
	public PartyMemberVO selectUser(Integer party_num, Integer mem_num);

	public void deleteParty(Integer party_num);
	
	public int selectmemcount(Integer party_num,Integer mem_num);
	//파티 좋아요
	public PartyFavVO selectFav(PartyFavVO fav);
	public void insertFav(PartyFavVO fav);
	public void deleteFav(Integer fav_num);
	
	
	

}
