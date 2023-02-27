package kr.spring.partymember.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.partymember.service.PartyMemberService;
import kr.spring.partymember.vo.PartyMemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class PartyMemberController {
	private static final Logger logger = LoggerFactory.getLogger(PartyMemberController.class);
	
	@Autowired
	private PartyMemberService partyMemberService;
	
	//=====파티 회원목록 관리=====//
	@RequestMapping("/partymember/partymemberlist.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1")
			int currentPage) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		//총 글의 개수
		int count = partyMemberService.selectRowCount(map);
		
		logger.debug("<파티원 관리 - count> : " + count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage,count, 20, 10, "admin_list.do");
		
		List<PartyMemberVO>list = null;
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
}
