package kr.spring.partymember.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.member.vo.MemberVO;
import kr.spring.partymember.service.PartyMemberService;
import kr.spring.partymember.vo.PartyMemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class PartyMemberController {
	private static final Logger logger = LoggerFactory.getLogger(PartyMemberController.class);

	@Autowired
	private PartyMemberService partyMemberService;

	// =====파티 회원목록 관리=====//
	@RequestMapping("/partymember/partyMemberList.do")
	public ModelAndView process(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage, int party_num) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("party_num", party_num);
		// 총 개수
		int count = partyMemberService.selectRowCount(map);

		logger.debug("<파티원 관리 - count> : " + count);
		// 페이지 처리
		PagingUtil page = new PagingUtil(currentPage, count, 20, 10, "partyMemberList.do");

		// 회원목록
		List<PartyMemberVO> list = null;
		if (count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());

			list = partyMemberService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("partyMemberList");// 이 이름으로 된 xml경로로 이동
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());

		return mav;
	}
	 //=========가입신청 ==========//
	 
	 @RequestMapping("/partymember/apply.do") 
	 public Map<String,Object>applyPartyMemberAjax(
			 PartyMemberVO vo,HttpSession session,HttpServletResponse response){
	 
	 Map<String,Object> mapJson = new HashMap<String,Object>();
	 
	 MemberVO user = (MemberVO)session.getAttribute("user"); 
	 
	 if(user==null) {
		 mapJson.put("result", "logout");
	 }else {
		 vo.setMem_num(user.getMem_num());
		 partyMemberService.insertPartyMember(vo);
	 
		 mapJson.put("result", "success");
	 }
	 
	 return mapJson;
	 }
}



//	@RequestMapping("/partymember/partyMemberCheck.do") 
//	public String submit(@Valid PartyMemberVO vo, BindingResult result, HttpServletRequest
//			request, RedirectAttributes redirect, HttpSession session) { 
//		MemberVO user = (MemberVO) session.getAttribute("user");
//
//		if (user == null) { 
//		
//			return "redirect:/member/memberLogin.do"; 
//		}
//
//		// 멤버번호 셋팅
//		vo.setMem_num(user.getMem_num()); 
//		// 파티원 가입 처리
//		partyMemberService.insertPartyMember(vo); 
//		// RedirectAttributes 객체는 리다이렉트 시점에 한 번만 사용되는 데이터를 전송. 
//		// 브라우저에 데이터를 전송하지만 URL상에 보이지 않는 숨겨진 데이터의 형태로 전달
//		redirect.addFlashAttribute("result", "success"); // 파티 상세 페이지로 이동 return
//		return "redirect:/partymember/partyMemberCheck.do";
//	} 
//}


//	//폼에서 전송된 데이터 처리
//	@PostMapping("/partymember/apply.do")
//	public String submit(PartyMemberVO vo,
//			HttpSession session,Model model,
//			HttpServletRequest request,
//			HttpServletResponse response) {
//
//		MemberVO user = 
//				(MemberVO)session.getAttribute("user");
//		
//		if(user == null) {
//			model.addAttribute("message", 
//					"로그인 후 이용가능합니다.");
//			model.addAttribute("url", 
//					request.getContextPath()+"/member/memberLogin.do");
//			return "common/resultView";
//		}
//
//		
//		Map<String,Object> map = 
//				new HashMap<String,Object>();
//		map.put("mem_num", user.getMem_num());
//		map.put("cart_numbers", 
//				orderVO.getCart_numbers());
//		int all_total = 
//				cartService.selectTotalByMem_num(map);
//		if(all_total<=0) {
//			model.addAttribute("message", 
//					"정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
//			model.addAttribute("url", 
//					request.getContextPath()+"/cart/list.do");
//			return "common/resultView";
//		}
/*
 * //=====가입 거부=======//
 * 
 * @RequestMapping("/partymember/delete.do") public String submitDelete(
 * 
 * @RequestParam int partymem_num){
 * 
 * 
 * //가입거부 partyMemberService.rejectPartyMember(partymem_num);
 * 
 * return "redirect:/partymember/partyMemberList.do"; } }
 */
