package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.spring.chat.dao.ChatMapper;
import kr.spring.chat.vo.ChatVO;
import kr.spring.party.vo.PartyVO;

	@Service
	public class ChatServiceImpl implements ChatService{
		
		@Autowired
		private ChatMapper chatMapper;
		
		//채팅방 목록
		@Override
		public List<PartyVO> selectChatRoomList(Integer mem_num) {
			return chatMapper.selectChatRoomList(mem_num);
		}

		//채팅방 상세
		@Override
		public PartyVO selectChatRoom(Integer party_num) {
			return chatMapper.selectChatRoom(party_num);
		}

		//채팅 메시지 등록
		@Override
		public void insertChat(ChatVO chatVO) {
			chatVO.setChat_num(chatMapper.selectChatNum());
			//채팅 메시지 등록
			chatMapper.insertChat(chatVO);
			for(ChatVO vo : chatMapper.selectChatMember(chatVO.getParty_num())) {
				chatMapper.insertChatRead(chatVO.getParty_num(), chatVO.getChat_num(), vo.getMem_num());
			}
			
		}

		//채팅 메시지 읽기
		@Override
		public List<ChatVO> selectChatDetail(Map<String, Integer> map) {
			chatMapper.deleteChatRead(map);
			return chatMapper.selectChatDetail(map.get("party_num"));
		}

		//채팅 멤버 읽기
		@Override
		public List<ChatVO> selectChatMember(Integer party_num) {
			return chatMapper.selectChatMember(party_num);
		}


	
	
	
	}
