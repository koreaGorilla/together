package kr.spring.chat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.spring.chat.vo.ChatVO;
import kr.spring.party.vo.PartyVO;

@Mapper
public interface ChatMapper {
		//채팅방 목록
		@Select("SELECT p.party_num, p.party_name, p.party_content, p.party_reg_date, p.party_reg_type, " +
		        "p.party_photo, p.party_photo_name, pm.mem_num, pm.partymem_num, pm.party_auth " +
		        "FROM party p JOIN party_member pm ON p.party_num = pm.party_num " +
		        "WHERE pm.mem_num = #{mem_num}")
		public List<PartyVO> selectChatRoomList(
			                 Map<String,Object> map);
		//채팅방 상세
		@Select("SELECT * FROM party WHERE "
			  + "party_num=#{party_num}")
		public PartyVO selectChatRoom(
				                   Integer party_num);
		//채팅방 번호 생성
		@Select("SELECT party_seq.nextval FROM dual")
		public Integer selectChatRoomNum();
		
		//채팅 메시지 번호 생성
		@Select("SELECT party_chat_seq.nextval FROM dual")
		public Integer selectChatNum();
		
		//채팅 메시지 등록
		@Insert("INSERT INTO party_chat (chat_num,party_num,"
			  + "mem_num,message) VALUES (#{chat_num},"
			  + "#{party_num},#{mem_num},#{message})")
		public void insertChat(ChatVO chatVO);
		
		//채팅 메시지 읽기
		public List<ChatVO> selectChatDetail(
				                   Integer party_num);
		
		//채팅 멤버 읽기
		@Select("SELECT mem_num, mem_id FROM party_member "
			  + "JOIN member USING(mem_num) WHERE "
			  + "party_num=#{party_num}")
		public List<ChatVO> selectChatMember(
				                   Integer party_num);
		//읽지 않은 채팅 기록 저장
		@Insert("INSERT INTO party_chat_read (party_num,"
			  + "chat_num, mem_num) VALUES (#{party_num},"
			  + "#{chat_num},#{mem_num})")
		public void insertChatRead(
				@Param(value="party_num") int party_num,
				@Param(value="chat_num") int chat_num,
				@Param(value="mem_num") int mem_num);
		
		//읽은 채팅 기록 삭제
		@Delete("DELETE FROM party_chat_read WHERE "
			  + "party_num=#{party_num} AND "
			  + "mem_num=#{mem_num}")
		public void deleteChatRead(
				                 Map<String,Integer> map);

}
