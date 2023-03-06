package kr.spring.party.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.vo.MemberVO;
import kr.spring.party.dao.PartyMapper;
import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyVO;
import kr.spring.partymember.vo.PartyMemberVO;

@Service
@Transactional
public class PartyServiceimpl implements PartyService {
	@Autowired
	private PartyMapper partyMapper;

	@Override
	public List<PartyVO> selectList(Map<String, Object> map) {
		return partyMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {		
		return partyMapper.selectRowCount(map);
	}

	@Override
	public void insertParty(PartyVO party,PartyMemberVO member) {
		party.setParty_num(partyMapper.selectPartyNum());
		member.setParty_num(party.getParty_num());
		partyMapper.insertParty(party);
		partyMapper.insertParty_Member(member);
		

	}

	@Override
	public PartyVO selectParty(Integer party_num) {
		
		return partyMapper.selectParty(party_num);
	}

	@Override
	public PartyVO selectPartyDetail(Integer party_num) {
		return partyMapper.selectPartyDetail(party_num);
	}
	
	@Override
	public PartyMemberVO selectPartyDetailForAuth(Integer party_num, Integer mem_num) {
		return partyMapper.selectPartyDetailForAuth(party_num, mem_num);
	}

	//메인페이지 파티목록 호출
	@Override
	public List<PartyVO> selectMainPartyList() {
		return partyMapper.selectMainPartyList();
	} 

	@Override
	public List<PartyMemberVO> selectPartyMember(Integer party_num) {
		return partyMapper.selectPartyMember(party_num);
	}

	@Override
	public void deleteParty(Integer party_num) {
		//재우님 여기입니다
		partyMapper.deleteFavByPartyNum(party_num); //좋아요 삭제
		partyMapper.deleteParty_Member(party_num);
		partyMapper.deleteParty(party_num);
		
		
	}
	//파티 좋아요
	
	@Override
	public PartyFavVO selectFav(PartyFavVO fav) {
		return partyMapper.selectFav(fav);
	}

	@Override
	public void insertFav(PartyFavVO fav) {
		partyMapper.insertFav(fav);
	}

	@Override
	public void deleteFav(Integer fav_num) {
		partyMapper.deleteFav(fav_num);
	}

	@Override
	public int selectmemcount(Integer party_num, Integer mem_num) {
		return partyMapper.selectmemcount(party_num, mem_num);
	}

	

	
}
