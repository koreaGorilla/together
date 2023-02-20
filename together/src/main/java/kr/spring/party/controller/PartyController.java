package kr.spring.party.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.member.vo.MemberVO;
import kr.spring.party.service.PartyService;
import kr.spring.party.vo.PartyVO;
import kr.spring.util.PagingUtil;

@Controller
public class PartyController {
	private static final Logger logger=LoggerFactory.getLogger(PartyController.class);
	
	private int rowCount = 20;

	@Autowired
	private PartyService partyService;

	//자바빈(VO) 초기화
	@ModelAttribute
	public PartyVO initCommand() {
		return new PartyVO();
	}
	//파티목록
		@RequestMapping("/party/list.do")
		public ModelAndView process(
				@RequestParam(value="pageNum",defaultValue="1") 
				int currentPage,
				String keyfield,String keyword) {

			Map<String,Object> map = 
					new HashMap<String,Object>();
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);

			//글의 총개수 또는 검색된 글의 개수
			int count = partyService.selectRowCount(map);

			logger.debug("<<count>> : " + count);

			//페이지 처리
			PagingUtil page = 
					new PagingUtil(keyfield,keyword,
							currentPage,count,20,10,"list.do");

			List<PartyVO> list = null;
			if(count > 0) {
				map.put("start", page.getStartRow());
				map.put("end", page.getEndRow());

				list = partyService.selectList(map);
			}

			ModelAndView mav = new ModelAndView();
			mav.setViewName("partyList");
			mav.addObject("count", count);
			mav.addObject("list", list);
			mav.addObject("page", page.getPage());

			return mav;
		}

	//파티생성
	//=======글쓰기=========//
	//등록 폼 호출
	@GetMapping("/party/write.do")
	public String form() {
		return "partyWrite";

	}

	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/party/write.do")
	public String submit(@Valid PartyVO partyVO,
			BindingResult result,
			HttpServletRequest request,
			RedirectAttributes redirect,
			HttpSession session) {
		logger.debug("<<게시판 글쓰기>> : " + partyVO);

		
		MemberVO user = (MemberVO)session.getAttribute("user");
		//로그인 처리가 되면 아래 코드 사용
		//partyVO.setMem_num(user.getMem_num());
		//로그인 처리가 안 되어 있을 때는
		partyVO.setMem_num(100);
		
		//글쓰기
		partyService.insertParty(partyVO);

		return "redirect:/party/list.do";
	}






}




