package kr.spring.chat.service;

import java.util.List;

import kr.spring.chat.vo.ChatVO;

public interface ChatService {

	//회원이 가입되어 있는 파티채팅 찾기
	public ChatVO getPartyNum(int mem_num);
	
	//파티채팅방 목록 가져오기
	public List<ChatVO> getChatRoomList(int party_num);
	
	//채팅 방 선택
    public ChatVO selectChatRoom(int party_num);
    
    //채팅 추가
	public void insertChat(ChatVO chat);
	
	//채팅 목록 가져오기
	public List<ChatVO> selectChatMember(Integer party_num);
}
