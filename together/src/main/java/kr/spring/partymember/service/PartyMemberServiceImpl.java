package kr.spring.partymember.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.partymember.dao.PartyMemberMapper;
import kr.spring.partymember.vo.PartyMemberVO;

@Service
public class PartyMemberServiceImpl implements PartyMemberService{
	
	@Autowired
	private PartyMemberMapper partyMemberMapper;

	@Override
	public List<PartyMemberVO> selectList(Map<String, Object> map) {
		return partyMemberMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return partyMemberMapper.selectRowCount(map);
	}

	@Override
	public void insertPartyMember(PartyMemberVO partyMemberVO) {
		partyMemberMapper.insertPartyMember(partyMemberVO);
		
	}
	
	@Override
	public void approvePartyMember(PartyMemberVO partyMemberVO) {
		partyMemberMapper.approvePartyMember(partyMemberVO);
	}

	@Override
	public void banPartyMember(PartyMemberVO partyMemberVO) {
		partyMemberMapper.banPartyMember(partyMemberVO);
	}

	@Override
	public void rejectPartyMember(PartyMemberVO PartyMemberVO) {
		partyMemberMapper.rejectPartyMember(PartyMemberVO);
	}

	@Override
	public void deletePartyMember(Integer mem_num) {
		partyMemberMapper.deletePartyMember(mem_num);
	}


	
		
}
