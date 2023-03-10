package kr.spring.mypage.service;

import java.util.List;
import java.util.Map;

import kr.spring.member.vo.MemberVO;
import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyVO;
import kr.spring.partymember.vo.PartyMemberVO;
import kr.spring.review.vo.ReviewVO;

public interface MypageService {
	//회원정보
	public MemberVO selectMember(Integer mem_num);
	//회원정보 수정
	public void updateMember(MemberVO member);
	//비밀번호 수정
	public void updatePassword(MemberVO member);
	//회원탈퇴
	public void deleteMember(Integer mem_num);
	
	//프로필 이미지 업데이트
	public void updateProfile(MemberVO member);
	//리뷰 가져오기
	public List<ReviewVO> selectListReviewByMem_num(
            Map<String,Object> map);
	public int selectReviewCountByMem_num(
            Map<String,Object> map);
	//내가 가입한 파티 가져오기(mem_num)
	public List<PartyVO> selectListPartyByMem_num(
			Map<String,Object> map);
	public int selecPartyCountByMem_num(
            Map<String,Object> map);
		//파티목록
		public List<PartyVO>getListPartyFavMem_num(
				Map<String,Object> map);
		//좋아요 누른 리뷰 가져오기
		public List<ReviewVO>getListReviewFavMem_num(
				Map<String,Object>map);
		
		public List<PartyVO>getListpartyMem_num(
				Map<String,Object> map);

}
