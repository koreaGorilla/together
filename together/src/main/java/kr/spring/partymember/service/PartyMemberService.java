package kr.spring.partymember.service;

import java.util.List;
import java.util.Map;

import kr.spring.partymember.vo.PartyMemberVO;

public interface PartyMemberService {
	//멤버 리스트
	public List<PartyMemberVO> selectList(
			Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);

	//가입신청
	public void insertPartyMember(PartyMemberVO partyMemberVO);

	//가입거부
	public void updateByPartyAdmin(PartyMemberVO PartyMemberVO);
	//가입초대

	//나가기
	public void deletePartyMember(Integer mem_num);
}
