package kr.spring.review.controller;

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
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	//리뷰 상세페이지에 한 번에 보여질 댓글 개수
	private int rowCount = 10;
	
	@Autowired
	private ReviewService reviewService;
	
	//자바빈 초기화
	@ModelAttribute
	public ReviewVO initCommand() {
		return new ReviewVO();
	}
	
	//======게시판 글 목록======//
	@RequestMapping("/review/list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,String keyfield,String keyword) {
		
		
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//리뷰의 총 개수 또는 검색된 리뷰 개수 확인
		int count = reviewService.selectRowCount(map);
		
		logger.debug("<<count>> : "+ count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, 10,10,"list.do");
		
		List<ReviewVO> list = null;
		if(count>0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = reviewService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reviewList");
		mav.addObject("count",count);
		mav.addObject("list",list);
		mav.addObject("page",page.getPage());
		
		return mav;
	}
	
	//======이미지 출력======//
	@RequestMapping("/review/imageView.do")
	public ModelAndView viewImage(@RequestParam int r_num, @RequestParam int review_type) {
		
		ReviewVO review = reviewService.selectReview(r_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		if(review_type==1) {
			mav.addObject("imageFile",review.getPhoto());
			mav.addObject("filename",review.getPhoto_name());
		}else if(review_type==2) {
			mav.addObject("imageFile",review.getR_photo());
			mav.addObject("filename",review.getR_photoname());
		}
		
		return mav;
	}
	
	//=====리뷰쓰기=====//
	//리뷰 작성 폼
	@GetMapping("/review/write.do")
	public ModelAndView form(HttpSession session) {
		
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		int mem_num = user.getMem_num();
		//int mem_num = 100;
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("reviewWrite");
		mav.addObject("list",reviewService.selectPartyname(mem_num));
		
		return mav;
	}
	
	
	//등록 폼에서 전송된 데이터 처리
	@PostMapping("review/write.do")
	public String submit(@Valid ReviewVO reviewVO, BindingResult result,
						HttpServletRequest request,
						RedirectAttributes redirect,
						HttpSession session) {
		
		logger.debug("<<리뷰 작성하기>> : " +reviewVO);
		logger.debug("<<업로드 이미지 용량>> " + reviewVO.getR_photo().length); //byte 배열의 길이
		
		//if(reviewVO.getR_photo().length>5*1024*1024) {
		//	result.reject("limitUploadSize",new Object[] {"5MB"}, null);
		//}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		//if(result.hasErrors()) {
		//	return form();
		//}
		
		//회원번호 셋팅
		reviewVO.setMem_num(((MemberVO)session.getAttribute("user")).getMem_num());
		
		logger.debug("<<로그인한 회원 번호>>"+ reviewVO.getMem_num());
		//리뷰 작성하기
		reviewService.insertReview(reviewVO);
		
		//RedirectAttributes 객체는 리다이렉트 시점에 한 번만 사용되는 데이터를 전송.
		//브라우저에 데이터를 전송하지만 URL상에 보이지 않는 숨겨진 데이터의 형태로 전달
		//request에 저장되고 return에서 redirect 사용 가능
		redirect.addFlashAttribute("result","success");
				
		//숨겨진 데이터가 list.do에 보내짐
		return "redirect:/review/list.do";
	}
	
	//======리뷰 글 상세======//
	@RequestMapping("/review/detail.do")
	public ModelAndView process(@RequestParam int r_num) {
		logger.debug("<<review_num>> : " + r_num);
		
		//한 건의 데이터 가져옴
		ReviewVO review = reviewService.selectReview(r_num);
		
		//내용에 태그를 허용하지 않음
		//review.setR_content(StringUtil.useNoHtml(review.getR_content()));
		
		return new ModelAndView("reviewView","review",review);
	}
	
	
	//======게시판 글수정======//
	//수정폼 호출
	@GetMapping("/review/update.do")
	public String formUpdate(@RequestParam int r_num, Model model) {
		
		ReviewVO reviewVO = reviewService.selectReview(r_num);
		
		model.addAttribute("reviewVO",reviewVO);
		return "reviewModify";
	}	
	
	//======업로드한 리뷰 이미지 파일 삭제======//
	@RequestMapping("/review/deleteFile.do")
	@ResponseBody
	public Map<String,String> processFile(
			                   int r_num,
			                   HttpSession session){
		Map<String,String> mapJson = 
				new HashMap<String,String>();
		
		MemberVO user = 
			 (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			reviewService.deletePhoto(r_num);
			
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}
	
	
	
	
	
	
	
	
	
}