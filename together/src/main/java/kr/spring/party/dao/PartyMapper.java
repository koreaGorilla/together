package kr.spring.party.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;
import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyVO;
import kr.spring.partymember.vo.PartyMemberVO;

@Mapper
public interface PartyMapper {
	public List<PartyVO> selectList(Map<String, Object>map);
	public int selectRowCount(Map<String,Object> map);
	
	@Select("SELECT party_seq.nextval FROM dual")
	public int selectPartyNum();
	
	@Insert("INSERT INTO party (party_num,party_name,party_content,party_photo,party_photo_name,party_hobby,party_reg_type,mem_num) "
			+ "VALUES(#{party_num},#{party_name},#{party_content},#{party_photo},#{party_photo_name},#{party_hobby},#{party_reg_type},#{mem_num})")
	public void insertParty(PartyVO party);
	
	@Insert("INSERT INTO party_member(partymem_num,party_auth,party_num,mem_num) "
            + "VALUES(party_member_seq.nextval,9,#{party_num},#{mem_num})")
    public void insertParty_Member(PartyMemberVO member);
	
	@Select("SELECT * FROM party p JOIN member m "
			  + "USING(mem_num) JOIN member_detail d "
			  + "USING(mem_num) WHERE p.party_num=#{party_num}")
		public PartyVO selectParty(Integer party_num);
	
	
	//@Select("select * from party where party_num=#{party_num}")
	//public PartyVO selectPartyDetail(Integer party_num);
	public void updateParty(PartyVO partyVO);
	
	@Update("UPDATE party SET party_photo='',party_photo_name='' WHERE party_num=#{party_num}")
	public void deleteFile(Integer party_num);
	
	@Delete("DELETE FROM party WHERE party_num=#{party_num}")
	public void deleteParty(Integer party_num);
	@Delete("DELETE FROM party_member WHERE party_num=#{party_num}")
	public void deleteParty_Member(Integer party_num);
	
	
	@Select("select * from party p join member_detail d using(mem_num) where p.party_num=#{party_num}")
	public PartyVO selectPartyDetail(Integer party_num);
	
	@Select("select * from party_member where party_num=#{party_num} AND mem_num=#{mem_num}")
	public PartyMemberVO selectPartyDetailForAuth(Integer party_num, Integer mem_num);
	 
	@Select("SELECT * FROM (SELECT * FROM party ORDER BY party_reg_date DESC) WHERE ROWNUM <= 4")
	public List<PartyVO> selectMainPartyList();
	
	@Select("select * from party_member p join member_detail d using(mem_num) join member using(mem_num) where p.party_num=#{party_num}")
	public List<PartyMemberVO> selectPartyMember(Integer party_num); //파티에 가입되어 있는 회원 정보 가져오기
	
	@Select("select count(*) from party_member where party_num=#{party_num} and mem_num=#{mem_num}")
	public int selectmemcount(Integer party_num,Integer mem_num);
	
	
	//파티 좋아요
	@Select("select * from party_fav where party_num=#{party_num} and mem_num=#{mem_num}")  
	public PartyFavVO selectFav(PartyFavVO fav);
	@Insert("insert into party_fav(p_fav_num,party_num,mem_num) values(partyfav_seq.nextval,#{party_num},#{mem_num})")
	public void insertFav(PartyFavVO fav);
	@Delete("delete from party_fav where p_fav_num=#{p_fav_num}")
	public void deleteFav(Integer p_fav_num);
	@Delete("delete from party_fav where party_num=#{party_num}")
	public void deleteFavByPartyNum(Integer party_num); //부모글 삭제하기 전에 좋아요 먼저 삭제
	
	@Select("SELECT * FROM party_member where party_num=#{party_num} and mem_num=#{mem_num}")
	public PartyMemberVO selectUser(Integer party_num, Integer mem_num);
	
	//읽지않은채팅 삭제
	@Delete("DELETE FROM PARTY_CHAT WHERE party_num=#{party_num}")
	public void deleteChatRead(Integer party_num);
	//채팅삭제
	@Delete("DELETE FROM PARTY_CHAT_READ WHERE party_num=#{party_num}")
	public void deleteChat(Integer party_num);
	
	//이미지 슬라이드용
	@Select("select * from (select * from party order by party_num desc) where rownum <= 5")
	public List<PartyVO> selectRecentParty();
	

} 
