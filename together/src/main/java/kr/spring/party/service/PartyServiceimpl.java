package kr.spring.party.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.party.dao.PartyMapper;
import kr.spring.party.vo.PartyVO;

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
	public void insertParty(PartyVO party) {
		partyMapper.insertParty(party);

	}

	@Override
	public PartyVO selectParty(Integer party_num) {
		
		return partyMapper.selectParty(party_num);
	}

}
