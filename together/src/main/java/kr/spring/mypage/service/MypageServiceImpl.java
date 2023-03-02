package kr.spring.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.dao.MypageMapper;
import kr.spring.party.vo.PartyVO;
import kr.spring.review.vo.ReviewVO;

@Service
@Transactional
public class MypageServiceImpl implements MypageService{
	
	@Autowired
	private MypageMapper mypageMapper;
	
	@Override
	public MemberVO selectMember(Integer mem_num) {
		
		return mypageMapper.selectMember(mem_num);
	}

	@Override
	public void updateMember(MemberVO member) {
		
		mypageMapper.updateMember_detail(member);
		
	}

	@Override
	public void updatePassword(MemberVO member) {
		mypageMapper.updatePassword(member);
		
	}

	@Override
	public void deleteMember(Integer mem_num) {
		mypageMapper.deleteMember(mem_num);
		mypageMapper.deleteMember_detail(mem_num);
		
	}

	@Override
	public void updateProfile(MemberVO member) {
		mypageMapper.updateProfile(member);
		
	}

	@Override
	public List<ReviewVO> selectListReviewByMem_num(Map<String, Object> map) {
		return mypageMapper.selectListReviewByMem_num(map);
	}

	@Override
	public int selectReviewCountByMem_num(Map<String, Object> map) {
		
		return mypageMapper.selectReviewCountByMem_num(map);
	}

	@Override
	public List<PartyVO> selectListPartyByMem_num(Map<String, Object> map) {
		
		return mypageMapper.selectListPartyByMem_num(map);
	}

	@Override
	public int selecPartyCountByMem_num(Map<String, Object> map) {
		
		return mypageMapper.selectPartyCountByMem_num(map);
	}

}
