package kr.spring.chat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.chat.vo.ChatVO;

@Mapper
public interface ChatMapper {
    
    @Select("SELECT party_num FROM party_member WHERE mem_num = #{mem_num}")
    public ChatVO getPartyNum(int mem_num);
    
    @Select("SELECT c.chat_num, c.party_num, c.mem_num, p.party_name " +
            "FROM party_chat c " +
            "JOIN party p ON c.party_num = p.party_num " +
            "WHERE c.party_num = #{party_num}")
    public List<ChatVO> getChatRoomList(int party_num);
    
	@Select("SELECT * FROM party_chat c JOIN party p "
			  + "USING(party_num) WHERE c.party_num=#{party_num}")
    public ChatVO selectChatRoom(int party_num);
	
	@Insert("INSERT INTO party_chat (chat_num, message, party_num, mem_num) "
			+ "VALUES (PARTY_CHAT_SEQ.nextval, #{message},#{party_num}, #{mem_num})")
	public void insertChat(ChatVO chat);
	
	@Select("SELECT p.party_num, p.mem_num, m.mem_name FROM party_member p JOIN member_detail m "
			+ "ON p.mem_num = m.mem_num WHERE p.party_num = #{party_num}")
	public List<ChatVO> selectChatMember(Integer chat_num);
	
}
