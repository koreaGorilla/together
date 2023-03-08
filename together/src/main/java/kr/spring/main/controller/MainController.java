package kr.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.mypage.service.MypageService;
import kr.spring.party.service.PartyService;
import kr.spring.party.vo.PartyVO;

@Controller
public class MainController {
	
	@Autowired
	private PartyService partyService;
	
	@Autowired
	private MypageService mypageService;
	
	@RequestMapping("/")
	public String main() {
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/main/main.do")
	public String main(Model model) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", 1);
		map.put("end", 8);

		List<PartyVO> list = partyService.selectList(map);
		List<PartyVO> party_list = partyService.selectRecentParty();
		
		model.addAttribute("list", list); 
		model.addAttribute("recent_list", party_list);
		
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





