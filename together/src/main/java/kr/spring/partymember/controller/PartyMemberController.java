package kr.spring.partymember.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.partymember.service.PartyMemberService;
import kr.spring.partymember.vo.PartyMemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class PartyMemberController {
	private static final Logger logger = LoggerFactory.getLogger(PartyMemberController.class);
	
	@Autowired
	private PartyMemberService partyMemberService;
	
	//=====파티 회원목록 관리=====//
	@RequestMapping("/partymember/partyMemberList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")
			int currentPage, int party_num) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("party_num", party_num);
		//총 개수
		int count = partyMemberService.selectRowCount(map);
		
		logger.debug("<파티원 관리 - count> : " + count);
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage,count, 20, 10, "partyMemberList.do");
		
		//회원목록
		List<PartyMemberVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = partyMemberService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("partyMemberList");//이 이름으로 된 xml경로로 이동
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		
		return mav;
	}
	
		//등록 폼에서 전송된 데이터 처리
		@PostMapping("/partymember/partyMemberList.do")
		public String submit(@Valid PartyMemberVO partymember,
				BindingResult result,
				HttpServletRequest request,
				RedirectAttributes redirect,
				HttpSession session) {

			PartyMemberVO user = (PartyMemberVO)session.getAttribute("user");
			partymember.setMem_num(user.getMem_num());
			partymember.setMem_num(user.getMem_num());

			//승인
			partyMemberService.insertPartyMember(partymember);

			return "redirect:/partymember/partyMemberList.do";
		}
}
