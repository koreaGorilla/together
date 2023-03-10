package kr.spring.mypage.controller;

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

import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.service.MypageService;
import kr.spring.party.service.PartyService;
import kr.spring.party.vo.PartyFavVO;
import kr.spring.party.vo.PartyVO;
import kr.spring.partymember.vo.PartyMemberVO;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.FileUtil;

@Controller
public class MypageController {
	private static final Logger logger=
			LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	private MypageService mypageService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private PartyService partyService;
	
	//자바빈 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	//====회원상세정보====//
	@RequestMapping("/mypage/myPage.do")
	public String process(HttpSession session,
						  Model model) {
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		
		//회원정보
		MemberVO member = mypageService.selectMember(
										user.getMem_num());
		logger.debug("<<회원상세정보>> :" + member);
		//내가 쓴 리뷰 목록
		Map<String,Object>map = 
				new HashMap<String,Object>();
		map.put("mem_num", user.getMem_num());
		//내가 쓴 리뷰 목록 5건 표시
		map.put("start", 1);
		map.put("end", 5);
		List<ReviewVO> reviewList = mypageService.selectListReviewByMem_num(map);
		
		logger.debug("<<내가쓴리뷰 목록>> :" + reviewList);
		
		
		//내가 생성한파티 목록
		Map<String,Object>map2 =
				new HashMap<String,Object>();
		map.put("mem_num", user.getMem_num());
		//내 파티 가입 목록 5건 표시
		map .put("start", 1);
		map .put("end", 5);
		List<PartyVO> partyList= mypageService.selectListPartyByMem_num(map );
		
		logger.debug("<<가입한 파티목록>>" + partyList);
		
		
		//찜한 파티 목록
		Map<String,Object>map4 =
				new HashMap<String,Object>();
		map.put("mem_num", user.getMem_num());
		//찜한 파티 목록 5건
		map.put("start", 1);
		map.put("end",5);
		List<PartyVO> getList = mypageService.getListPartyFavMem_num(map);
		logger.debug("<<찜한 파티 목록>>" + getList);
		//좋아요 누른 리뷰 목록
		Map<String, Object>map5 =
				new HashMap<String,Object>();
		map.put("mem_num", user.getMem_num());
		//리뷰 목록 5건
		map.put("start", 1);
		map.put("end", 5);
		List<ReviewVO> getReview = mypageService.getListReviewFavMem_num(map);
		logger.debug("<<좋아요 누른 리뷰 목록>>" + getReview);
		
		//가입한 파티 목록
		 Map<String,Object>map6 = 
				 new HashMap<String,Object>();
		 map.put("mem_num", user.getMem_num());
		 //파티목록 5건
		 map.put("start", 1);
		 map.put("end", 5);
		 List<PartyVO> getMyparty = mypageService.getListpartyMem_num(map);
		 logger.debug("<<가입한 파티>>" + getMyparty);
		
		model.addAttribute("member" , member);
		model.addAttribute("reviewList",reviewList);
		model.addAttribute("partyList",partyList);
		
		model.addAttribute("getList",getList);
		model.addAttribute("getReview" ,getReview);
		model.addAttribute("getMyparty",getMyparty);
		model.addAttribute("member",member);
		
		return "myPage";		
	}
	//=====프로필 사진 업로드==S===//
	@RequestMapping("/mypage/updateMyphoto.do")
	@ResponseBody
	public Map<String,String> processProfile(
					MemberVO memberVO,
					HttpSession session){
		
		Map<String,String> mapAjax = 
				new HashMap<String,String>();
		
		MemberVO user=
				(MemberVO)session.getAttribute("user");
		if(user==null) {
			mapAjax.put("result","logout");
		}else {
			memberVO.setMem_num(user.getMem_num());
			mypageService.updateProfile(memberVO);
			
			mapAjax.put("result", "success");
		}
		return mapAjax;
	}
	
	//====회원정보수정====//
	//수정폼 호출
	@RequestMapping("/mypage/update.do")
	public String formUpdate(HttpSession session,
							Model model) {
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		
		MemberVO memberVO = 
				mypageService.selectMember(user.getMem_num());
		
		model.addAttribute("memberVO", memberVO);
		
		return "memberModify";
	}
	//수정 폼에서 전송된 데이터 호출
	@PostMapping("/mypage/update.do")
	public String submitUpdate(@Valid MemberVO memberVO ,
								BindingResult result,
								HttpSession session) {
		logger.debug("<<회원정보수정 처리>> :" + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "memberModify";
		}
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		//회원정보가 전송되지 않아서 세션에 저장된 회원번호 사용
		memberVO.setMem_num(user.getMem_num());
		
		//회원정보수정
		mypageService.updateMember(memberVO);
		
		return "redirect:/mypage/myPage.do";
	}
	
	//======비밀번호 변경======//
	//비밀번호 변경 폼 호출
	@GetMapping("/mypage/changePassword.do")
	public String formChangePassword() {
		return "memberChangePassword";
	}
	
	//비밀번호 변경폼에서 전송된 데이터 처리
	@PostMapping("/mypage/changePassword.do")
	public String submitChangePassword(
									  @Valid MemberVO memberVO,
									  BindingResult result,
									  HttpSession session,
									  Model model,
									  HttpServletRequest request) {
		logger.debug("<<비밀번호변경 처리>> :" + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼호출
		if(result.hasFieldErrors("now_passwd") ||
				result.hasFieldErrors("mem_pw")) {
			return formChangePassword();
		}
		
		MemberVO user=
				(MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		MemberVO  db_member = mypageService.selectMember(memberVO.getMem_num());
		
		//폼에서 전송한 현재 비밀번호와 DB에서 받아온 비밀번호
		//일치 여부 체크
		if(!db_member.getMem_pw().equals(memberVO.getNow_passwd())) {
			result.rejectValue("now_passwd", "invalidPassword");
			return formChangePassword();
		}
		//비밀번호 변경
		mypageService.updatePassword(memberVO);
		
		//view에 표시할 메시지
		model.addAttribute("message","비밀번호변경 완료");
		
		model.addAttribute("url",request.getContextPath()+"/mypage/myPage.do");
		return "common/resultView";
	}
	//==회원정보삭제(회원탈퇴)==//
	//회원삭제 폼 호출
	@GetMapping("/mypage/delete.do")
	public String formDelete() {
		return "memberDelete";
	}
	
	//회원삭제 폼에서 전송된 데이터 처리
	@PostMapping("/mypage/delete.do")
	public String submitDelete(
						@Valid MemberVO memberVO,
						BindingResult result,
						HttpSession session,
						Model model) {
		logger.debug("<<회원탈퇴>> :" + memberVO);
		
		//유효성 체크 결과가 오류가 있으면 폼 호출
		//mem_id , mem_pw 필드의 에러만 체크
		if(result.hasFieldErrors("mem_id") ||
				result.hasFieldErrors("mem_pw")) {
			return formDelete();
		}
		MemberVO user =
				(MemberVO)session.getAttribute("user");
		MemberVO db_member = 
				mypageService.selectMember(user.getMem_num());
		boolean check = false;
		//비밀번호 일치 여부 체크
		try {
			if(db_member!=null
				&& db_member.getMem_id().equals(memberVO.getMem_id())){
					//비밀번호 일치 여부 체크
					check = db_member.isCheckedPassword(memberVO.getMem_pw());
				}
				if(check) {
					//인증 성공, 회원정보 삭제
					mypageService.deleteMember(user.getMem_num());
					
					//로그아웃
					session.invalidate();
					
					model.addAttribute("accessMsg","회원탈퇴를 완료했습니다.");
					
					return "common/notice";
				}
				//인증실패
				throw new AuthCheckException();
		}catch(AuthCheckException e) {
			result.reject("invalidIdOrPassword");
			return formDelete();
		}
		
	} 
	
	//==프로필 사진 출력(로그인 전용)==//
	@RequestMapping("/mypage/photoView.do")
	public String getProfile(HttpSession session,
							HttpServletRequest request,
							Model model) {
		MemberVO user = 
				(MemberVO)session.getAttribute("user");
		
		logger.debug("<<photoView>> :" + user);
		
		if(user==null) {
			byte[] readbyte = FileUtil.getBytes(
					request.getServletContext().getRealPath(
							      "/image_bundle/face.png"));
								//속성명       속성값						
			model.addAttribute("imageFile", readbyte);
			model.addAttribute("filename", "face.png");
		}else {
			MemberVO memberVO = mypageService.selectMember(
					                     user.getMem_num());
			logger.debug("<<memberVO>> : " + memberVO);
			viewProfile(memberVO,request,model);
		}
		return "imageView";
	}
	//프로필 사진 출력(회원번호 지정)
		@RequestMapping("/mypage/viewProfile.do")
		public String getProfileByMem_num(
				              @RequestParam int mem_num,
				              HttpSession session,
				              HttpServletRequest request,
				              Model model) {
			
			MemberVO memberVO = 
					mypageService.selectMember(mem_num);
			viewProfile(memberVO,request,model);
			
			return "imageView";
		}

	//프로필 사진 처리를 위한 공통 코드
		public void viewProfile(MemberVO memberVO,
				           HttpServletRequest request,
				           Model model) {
			if(memberVO==null || memberVO.getPhoto_name()==null) {
				byte[] readbyte = FileUtil.getBytes(
						request.getServletContext().getRealPath(
								      "/image_bundle/face.png"));
									//속성명       속성값						
				model.addAttribute("imageFile", readbyte);
				model.addAttribute("filename", "face.png");
			}else {
				model.addAttribute(
						"imageFile", memberVO.getPhoto());
				model.addAttribute(
						"filename", memberVO.getPhoto_name());
}
}
		}