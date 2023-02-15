package kr.spring.chat.controller;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.chat.service.ChatService;
import kr.spring.chat.vo.ChatVO;
import kr.spring.member.vo.MemberVO;


@Controller
public class ChatController {

	private static final Logger logger =
			LoggerFactory.getLogger(
					          ChatController.class);
	
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    
	//자바빈(VO) 초기화
	@ModelAttribute
	public ChatVO initCommand() {
		return new ChatVO();
	}
	
	//======채팅방 목록======//
	//채팅방 호출
	@RequestMapping(value = "/chat/chatList.do", method = RequestMethod.GET)
	public ModelAndView getChatRoomList(HttpSession session, HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView("/chat/chatList");
	    MemberVO user = (MemberVO) session.getAttribute("user");

	    //회원검증
	    if (user == null) {
	        mav.addObject("errorMessage", "loginError");
	        return mav;
	    }
	    //파티 검증
	    Integer party_num = (Integer) request.getSession().getAttribute("party_num");
	    if (party_num == null) {
	        mav.addObject("errorMessage", "PartyError");
	        return mav;
	    }

	    List<ChatVO> chatRooms = chatService.getChatRoomList(party_num);
	    logger.debug("<<chatRooms>>", chatRooms);
	    logger.debug("<<party_num>>", party_num);
	    
	    mav.addObject("chatRooms", chatRooms);
	    mav.addObject("party_num", party_num);
	    return mav;
	}
	//채팅방 호출
	@RequestMapping(value="/chat/chatDetail.do", method = RequestMethod.GET)
	public String chat(@RequestParam int party_num,
							Model model) {
		
		
		ChatVO chatVO = chatService.getPartyNum(party_num);
		List<ChatVO> list = chatService.selectChatMember(party_num);
		
		model.addAttribute("chatVO", chatVO);
		model.addAttribute("list", list);
		
		return "chatDetail";
	}

}
