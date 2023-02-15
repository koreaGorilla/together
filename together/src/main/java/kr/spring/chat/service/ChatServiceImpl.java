package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.chat.dao.ChatMapper;
import kr.spring.chat.vo.ChatVO;

	@Service
	public class ChatServiceImpl implements ChatService{
		
	    @Autowired
	    private ChatMapper chatMapper;
	
	
		@Override
		public ChatVO getPartyNum(int mem_num) {
			 return chatMapper.getPartyNum(mem_num);
		}
	
		@Override
		public List<ChatVO> getChatRoomList(int party_num) {
			return chatMapper.getChatRoomList(party_num);
		}
	
		@Override
		public List<ChatVO> selectChatMember(Integer chat_num) {
			// TODO Auto-generated method stub
			return null;
		}
	
		@Override
		public ChatVO selectChatRoom(int party_num) {
			// TODO Auto-generated method stub
			return null;
		}
	
		@Override
		public void insertChat(ChatVO chat) {
			// TODO Auto-generated method stub
			
		}
	
	
	
	}
