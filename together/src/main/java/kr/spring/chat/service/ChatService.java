package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import kr.spring.chat.vo.ChatVO;
import kr.spring.party.vo.PartyVO;

public interface ChatService {
	//채팅방 목록
	public List<PartyVO> selectChatRoomList(
			                Map<String,Object> map);
	//채팅방 상세
	public PartyVO selectChatRoom(
			                 Integer party_num);
	//채팅 메시지 등록
	public void insertChat(ChatVO chatVO);
	//채팅 메시지 읽기
	public List<ChatVO> selectChatDetail(Map<String,Integer> map);
	
	//채팅 멤버 읽기
	public List<ChatVO> selectChatMember(
			Integer party_num);
}
