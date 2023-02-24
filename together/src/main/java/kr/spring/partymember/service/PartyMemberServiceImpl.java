package kr.spring.partymember.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.spring.member.vo.MemberVO;
import kr.spring.partymember.dao.PartyMemberMapper;
import kr.spring.partymember.vo.PartyMemberVO;

@Service
public class PartyMemberServiceImpl implements PartyMemberService{
	
	
	private PartyMemberMapper partyMemberMapper;

	@Override
	public List<PartyMemberVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertPartyMember(PartyMemberVO partyMemberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByPartyAdmin(PartyMemberVO PartyMemberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePartyMember(Integer mem_num) {
		// TODO Auto-generated method stub
		
	}

	
		
}
