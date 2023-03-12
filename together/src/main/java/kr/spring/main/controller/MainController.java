package kr.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.service.MypageService;
import kr.spring.party.service.PartyService;
import kr.spring.party.vo.PartyVO;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private PartyService partyService;
	
	@Autowired
	private MypageService mypageService;
	
	@RequestMapping("/")
	public String main() {
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/main/main.do") 
	public String main(HttpSession session, Model model) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("start", 1);
		map.put("end", 9);

		List<PartyVO> list = partyService.selectList(map);
		List<PartyVO> party_list = partyService.selectRecentParty();
		
		model.addAttribute("list", list); 
		model.addAttribute("recent_list", party_list);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		List<PartyVO> partyList = null;
		
		if(user != null) {
			Map<String,Object> mapJson = new HashMap<String,Object>();
			mapJson.put("mem_num", user.getMem_num());

			mapJson.put("start", 1);
			mapJson.put("end", 3);
			
			partyList = mypageService.getListpartyMem_num(mapJson);
			
			model.addAttribute("myparty", partyList);
		}	
		
		return "main";//타일스 설정값
	}
	

	//======이미지 출력======//
	@RequestMapping("/main/imageView.do")
	public ModelAndView viewImage(@RequestParam int party_num) {

		PartyVO party = partyService.selectPartyDetail(party_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");

		mav.addObject("imageFile",party.getParty_photo());
		mav.addObject("filename",party.getParty_photo_name());

		return mav;
	}

}





