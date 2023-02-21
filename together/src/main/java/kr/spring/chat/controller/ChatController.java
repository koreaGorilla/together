package kr.spring.chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.chat.service.ChatService;
import kr.spring.chat.vo.ChatVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.party.vo.PartyVO;


@Controller
public class ChatController {

	private static final Logger logger =
			LoggerFactory.getLogger(
					          ChatController.class);
	
	@Autowired
    private ChatService chatService;

	
	//======채팅방 목록======//
	@RequestMapping("/chat/chatList.do")
	public String chatList(String keyword,
							HttpSession session,
							Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("keyword", keyword);
		map.put("mem_num", user.getMem_num());
		
		List<PartyVO> list = chatService.selectChatRoomList(map);
		
		model.addAttribute("list", list);
		
		return "chatList";
	}
	
	//=======채팅 메시지 페이지 호출=======//
	@RequestMapping("/chat/chatDetail.do")
	public String chatDetail(
			      @RequestParam int party_num,
			                        Model model) {
		PartyVO partyVO = 
			chatService.selectChatRoom(party_num);
		List<ChatVO> list = 
			chatService.selectChatMember(party_num);
		
		model.addAttribute("partyVO", partyVO);
		model.addAttribute("list", list);
		
		return "chatDetail";
	}
	
	//=======채팅 메시지 읽기========//
	@RequestMapping("/chat/chatDetailAjax.do")
	@ResponseBody
	public Map<String,Object> chatDetailAjax(
			      @RequestParam int party_num,
			               HttpSession session){
		Map<String,Object> mapAjax =
				new HashMap<String,Object>();
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			mapAjax.put("result", "logout");
		}else {
			Map<String,Integer> map = 
					new HashMap<String,Integer>();
			map.put("party_num", party_num);
			map.put("mem_num", user.getMem_num());
			
			List<ChatVO> list = 
					chatService.selectChatDetail(map);
			logger.debug("<<채팅메시지 모음>> : " + list);
			
			mapAjax.put("result", "success");
			mapAjax.put("list", list);
		}
		
		return mapAjax;
	}
	
	//=========채팅 메시지 등록 ==========//
	@RequestMapping("/chat/writeChat.do")
	@ResponseBody
	public Map<String,String> writeChatAjax(
			            ChatVO vo,HttpSession session){
		
		logger.debug("<<채팅메시지 저장>> : " + vo);
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		
		MemberVO user = 
			 (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapAjax.put("result", "logout");
		}else {
			chatService.insertChat(vo);
			
			mapAjax.put("result", "success");
		}
		
		return mapAjax;
	}
	
	

}
