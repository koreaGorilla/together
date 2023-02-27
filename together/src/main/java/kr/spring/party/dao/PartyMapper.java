package kr.spring.party.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;
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
	
	
	@Select("select * from party where party_num=#{party_num}")
	public PartyVO selectPartyDetail(Integer party_num);
	

}
