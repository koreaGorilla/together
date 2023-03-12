package kr.spring.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;
import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyVO;
import kr.spring.partymember.vo.PartyMemberVO;
import kr.spring.review.vo.ReviewVO;

@Mapper
public interface MypageMapper {
	//회원상세정보
	@Select("SELECT * FROM member m JOIN member_detail d "
			+ "ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	//회원정보 수정
	@Update("UPDATE member_detail SET mem_name=#{mem_name},"
			+ "mem_cell=#{mem_cell},mem_email=#{mem_email},"
			+ "mem_zipcode=#{mem_zipcode},mem_address1=#{mem_address1},mem_address2=#{mem_address2},"
			+ "hobby=#{hobby,jdbcType=VARCHAR},modify_date=SYSDATE WHERE mem_num=#{mem_num}")
	public void updateMember_detail(MemberVO member);
	//비밀번호 수정
	@Update("UPDATE member_detail SET "
			+ "mem_pw=#{mem_pw} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	//회원탈퇴
	@Update("UPDATE member SET mem_auth=0 WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Integer mem_num);
	//프로필 이미지 업데이트
	@Update("UPDATE member_detail SET photo=#{photo}," 
			+ "photo_name=#{photo_name} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	//내가 쓴 리뷰 가져오기(mem_num)
	public List<ReviewVO> selectListReviewByMem_num(
            Map<String,Object> map); 
	public int selectReviewCountByMem_num(
            Map<String,Object> map);
	//내가 생성한 파티 가져오기(mem_num)
	public List<PartyVO> selectListPartyByMem_num(
			Map<String,Object> map);
	public int selectPartyCountByMem_num(
            Map<String,Object> map);
	
	//파티목록 가져오기
	@Select("SELECT * FROM (SELECT a.*, rownum rnum "
			+ "FROM(SELECT * FROM party p JOIN member m "
			+ "USING(mem_num) JOIN party_fav f USING(party_num) "
			+ "WHERE f.mem_num=#{mem_num} ORDER BY party_num DESC)a) "
			+ "WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<PartyVO>getListPartyFavMem_num(
			Map<String,Object> map);
	//좋아요 누른 리뷰 가져오기
	@Select("SELECT * FROM (SELECT a.*, rownum rnum "
			+ "FROM(SELECT * FROM review r JOIN member m "
			+ "USING(mem_num) JOIN review_fav f USING(r_num)"
			+ "WHERE f.mem_num=#{mem_num} ORDER BY r_num DESC)a) "
			+ "WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<ReviewVO>getListReviewFavMem_num(
			Map<String,Object>map);
	
	@Select("SELECT * FROM (SELECT a.*, rownum rnum "
			+ "FROM(SELECT * FROM party p JOIN member m "
			+ "USING(mem_num) JOIN party_member b USING(party_num) "
			+ "WHERE b.mem_num=#{mem_num} ORDER BY party_num DESC)a) "
			+ "WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<PartyVO>getListpartyMem_num(
			Map<String,Object> map);

	
}
