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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.member.vo.MemberVO;
import kr.spring.party.service.PartyService;
import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyVO;
import kr.spring.partymember.vo.PartyMemberVO;
import kr.spring.review.vo.ReviewVO;
import kr.spring.util.FileUtil;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

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
			@RequestParam(value="party_hobby",defaultValue="0") 
			int party_hobby,
			String keyfield,String keyword) {

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("party_hobby", party_hobby);

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


	//======이미지 출력======//
	@RequestMapping("/party/imageView.do")
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


	//파티생성
	//=======글쓰기=========//
	//등록 폼 호출
	@GetMapping("/party/write.do")
	public String form() {
		return "partyWrite";
	}

	//등록 폼에서 전송된 데이터 처리
	@PostMapping("/party/write.do")
	public String submit(@Valid PartyVO partyVO,PartyMemberVO member,
			BindingResult result,
			HttpServletRequest request,
			RedirectAttributes redirect,
			HttpSession session) {
		logger.debug("<<게시판 글쓰기>> : " + partyVO);


		MemberVO user = (MemberVO)session.getAttribute("user");
		//로그인 처리가 되면 아래 코드 사용
		partyVO.setMem_num(user.getMem_num());
		//로그인 처리가 안 되어 있을 때는
		//partyVO.setMem_num(100);
		partyVO.setMem_num(user.getMem_num());
		member.setMem_num(user.getMem_num());

		//글쓰기
		partyService.insertParty(partyVO, member);

		return "redirect:/party/list.do";
	}



	//======파티 상세======//
	@RequestMapping("/party/detail.do")
	public ModelAndView process(@RequestParam int party_num,HttpSession session) {
		logger.debug("<<party_num>> : " + party_num);

		//한 건의 데이터 가져오기
		PartyVO party = partyService.selectPartyDetail(party_num);


		List<PartyMemberVO> partyMember = partyService.selectPartyMember(party_num);
		
		logger.debug("<<partyMember>> : " + partyMember);
		party.setParty_name(StringUtil.useNoHtml(party.getParty_name()));

		ModelAndView mav = new ModelAndView();
		mav.setViewName("partyDetail");

		MemberVO user = (MemberVO)session.getAttribute("user");
		PartyMemberVO nowPartyUser = partyService.selectUser(party_num, user.getMem_num());
		
		if(user!=null) {
			int count = partyService.selectmemcount(party_num, user.getMem_num());

			logger.debug("<<count>> : "+ count);
			PartyMemberVO pMember = partyService.selectPartyDetailForAuth(party_num, user.getMem_num());
			mav.addObject("pMember", pMember);
			mav.addObject("count",count);
			mav.addObject("nowMem", nowPartyUser);
		}

		mav.addObject("party",party);
		mav.addObject("list",partyMember);
		mav.addObject("user",user);

		return mav; 
	}

	//=====파티 좋아요=====//
	//좋아요 읽기
	@RequestMapping("/party/getFav.do")
	@ResponseBody
	public Map<String ,Object> getFav(PartyFavVO fav,HttpSession session){
		logger.debug("<<파티 좋아요>> : " +fav);

		Map<String, Object> mapJson = new HashMap<String,Object>();

		//로그인 안 했을 경우 좋아요 안 한 기본 이미지 출력
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("status", "noFav");
		}else {
			//로그인된 아이디 세팅
			fav.setMem_num(user.getMem_num());
			PartyFavVO partyFav = partyService.selectFav(fav);

			if(partyFav!=null) {
				mapJson.put("status", "yesFav"); //좋아요 누른 이미지 출력
			}else {
				mapJson.put("status", "noFav");
			}
		}
		return mapJson;
	}

	//좋아요 등록
	@RequestMapping("/party/writeFav.do")
	@ResponseBody
	public Map<String, Object> writeFav(PartyFavVO fav, HttpSession session){
		logger.debug("<<부모글 좋아요 등록>> : " + fav);

		Map<String, Object> mapJson = new HashMap<String,Object>();

		MemberVO user = (MemberVO)session.getAttribute("user");

		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			//로그인된 회원번호 세팅
			fav.setMem_num(user.getMem_num());

			logger.debug("<<부모글 좋아요 등록>> : " + fav);

			PartyFavVO partyFav = partyService.selectFav(fav);

			logger.debug("<<partyFav>> : "+partyFav);

			if(partyFav!=null) {//좋아요 누른 거 
				//좋아요가 이미 등록되어있으면 삭제
				partyService.deleteFav(partyFav.getP_fav_num());
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}else {
				//좋아요 등록 하기
				partyService.insertFav(fav);
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}
		}
		return mapJson;
	}


	//=====파티 글삭제=======//
	@RequestMapping("/party/delete.do")
	public String partyDelete(
			@RequestParam int party_num,
			Model model,
			HttpServletRequest request) {

		logger.debug("<<게시판 글삭제>> : " + party_num);

		//글삭제
		partyService.deleteParty(party_num);


		return "redirect:/party/list.do";
	}




}