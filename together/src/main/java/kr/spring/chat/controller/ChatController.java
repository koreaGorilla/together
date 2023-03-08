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
import kr.spring.party.service.PartyService;
import kr.spring.party.vo.PartyVO;
import kr.spring.partymember.vo.PartyMemberVO;


@Controller
public class ChatController {

	private static final Logger logger =
			LoggerFactory.getLogger(
					          ChatController.class);
	
	@Autowired
    private ChatService chatService;
	
	@Autowired
	private PartyService partyService;

	
	//======채팅방 목록======//
	@RequestMapping("/chat/chatList.do")
	public String chatList(String keyword,
							HttpSession session,
							Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//현재 접속중인 유저 정보를 가져온다
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//현재 접속중이 아니면 알림창으로 전송
		if(user == null) {
			map.put("accessMsg", "로그인 후 이용해주세요");
			return "/common/notice";
		}
		else {
		
		//유저정보를 mem_num에 담는다
		int mem_num =  user.getMem_num();
		
		//파티목록을 list에 담아 전송
		List<PartyVO> list = chatService.selectChatRoomList(mem_num);
		
		model.addAttribute("list", list);
		
		return "chatList";
		}
	}
	
	//=======채팅 메시지 페이지 호출=======//
	@RequestMapping("/chat/chatDetail.do")
	public String chatDetail(
			      @RequestParam int party_num,
			      					HttpSession session,
			                        Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//현재 접속중인 유저 정보를 가져온다
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//현재접속되어있는 유저의 파티회원정보를 가져온다
		
		//현재 접속중이 아니거나 파티가입을 하지않으면 알림창으로 전송
		if(user == null) {
			return "/common/notice";
		}
		//현재 접속중이며, 파티가입시 채팅창상세 전송
		else {	
			PartyMemberVO userParty = partyService.selectUser(party_num, user.getMem_num());
			if(userParty == null){
				return "/common/notice";
			}
				//파티번호를 통해 채팅방을 찾아 partyVO에 저장
				PartyVO partyVO = 
						chatService.selectChatRoom(party_num);
				//파티번호를 통해 채팅방인원을 찾아 리스트로 저장
				List<ChatVO> list = 
						chatService.selectChatMember(party_num);
				
				
				model.addAttribute("partyVO", partyVO);
				model.addAttribute("list", list);
				model.addAttribute("userParty",userParty);
				
				//성공시 tiles.def에 있는 chatDetail로 전송
				return "chatDetail";
		}
	}
	
	//=======채팅 메시지 읽기========//
	@RequestMapping("/chat/chatDetailAjax.do")
	@ResponseBody
	public Map<String,Object> chatDetailAjax(
			      @RequestParam int party_num,
			               HttpSession session){
		Map<String,Object> mapAjax =
				new HashMap<String,Object>();
		
		//현재 접속중인 유저 정보를 가져온다
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		
		//현재 접속중이 아니면 알림창으로 전송
		if(user==null) {
			mapAjax.put("result", "logout");
		}else {
			Map<String,Integer> map = 
					new HashMap<String,Integer>();
			
			//요청받은 party_num을 받아 map에 저장한다
			map.put("party_num", party_num);
			//접속중인 유저에게서 mem_num을 받아  map에 저장한다
			map.put("mem_num", user.getMem_num());
			
			//전달받은 map을 이용해서 채팅메시지를 가져와 list형식으로 저장한다
			List<ChatVO> list = 
					chatService.selectChatDetail(map);
			
			logger.debug("<<채팅메시지 모음>> : " + list);
			
			//성공시 success메시지와 list 전송
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
		
		//현재 접속중인 유저 정보를 가져온다
		MemberVO user = 
			 (MemberVO)session.getAttribute("user");
		//로그인하지 않은 경우
		if(user==null) {
			mapAjax.put("result", "logout");
		}else {
			//전달받은 vo를 데이터베이스에 저장
			chatService.insertChat(vo);
			
			//성공시 success 전송
			mapAjax.put("result", "success");
		}
		
		return mapAjax;
	}
	//======이미지 출력======//
	@RequestMapping("/chat/imageView.do")
	public ModelAndView viewImage(@RequestParam int party_num, @RequestParam int party_type) {

		PartyVO party = partyService.selectPartyDetail(party_num);


		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");

		if(party_type==1) { //프로필 이미지 가져오기
			mav.addObject("imageFile",party.getPhoto());
			mav.addObject("filename",party.getPhoto_name());
		}else if(party_type==2) { //파티 이미지 가져오기
			mav.addObject("imageFile",party.getParty_photo());
			mav.addObject("filename",party.getParty_photo_name());
		}

		return mav;
	}
	
	
	

}
