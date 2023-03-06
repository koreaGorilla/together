package kr.spring.review.controller;

import java.util.Collections;
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
import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewFavVO;
import kr.spring.review.vo.ReviewReplyVO;
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
		
		if(reviewVO.getR_photo().length>5*1024*1024) {
			result.reject("limitUploadSize",new Object[] {"5MB"}, null);
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "reviewWrite";
		}
		
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
	
	//수정폼에서 전송된 데이터 처리
	@PostMapping("/review/update.do")
	public String submitUpdate(@Valid ReviewVO reviewVO,
								BindingResult result,
								HttpServletRequest request,
								Model model) {
		logger.debug("<<글수정>> : " + reviewVO);
		
		logger.debug("<<업로드 파일 용량>> : "+ reviewVO.getR_photo().length);
		if(reviewVO.getR_photo().length>5*1024*1024) {
			result.reject("limitUploadSize",new Object[]{"5MB"},null);
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			//content가 입력되지 않아서 유효성 체크에 
			//걸리면 파일 정보를 잃어버리기 때문에 폼을 호출할 떄 
			//파일 정보를 다시 셋팅해줘야 함
			ReviewVO vo = reviewService.selectReview(reviewVO.getR_num());
			reviewVO.setR_photoname(vo.getR_photoname());
			return "reviewModify";
		}
		
		//리뷰 수정
		reviewService.updateReview(reviewVO);
		
		//view에 표시할 메시지
		model.addAttribute("message","리뷰수정완료!");
		model.addAttribute("url",request.getContextPath()+"/review/detail.do?r_num="+reviewVO.getR_num());
		
		return "common/resultView";
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
	
	//=====리뷰 삭제=====//
	@RequestMapping("/review/delete.do")
	public String submitDelete(@RequestParam int r_num) {
		logger.debug("<<리뷰 글삭제>> : "+r_num);
		
		//글삭제
		reviewService.deleteReview(r_num);
		
		return "redirect:/review/list.do";
	}
	
	//=====리뷰 좋아요=====//
	//좋아요 읽기
	@RequestMapping("/review/getFav.do")
	@ResponseBody
	public Map<String, Object> getFav(ReviewFavVO fav, HttpSession session){
		logger.debug("<<부모글 좋아요>> : " + fav);
		
		Map<String, Object> mapJson = new HashMap<String,Object>();
		
		//로그인 안 했을 경우 좋아요 선택 안 한 기본 이미지
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("status", "noFav");
		}else {
			fav.setMem_num(user.getMem_num());
			ReviewFavVO reviewFav = reviewService.selectFav(fav);
			
			if(reviewFav!=null) {
				mapJson.put("status", "yesFav");
			}else {
				mapJson.put("status", "noFav");
			}
		}
		mapJson.put("count", reviewService.selectFavCount(fav.getR_num()));
		return mapJson;
	}
	
	//좋아요 등록
	@RequestMapping("/review/writeFav.do")
	@ResponseBody
	public Map<String, Object> writeFav(ReviewFavVO fav, HttpSession session){
		logger.debug("<<부모글 좋아요 등록>> : fav");
		
		Map<String, Object> mapJson = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			fav.setMem_num(user.getMem_num());
			
			logger.debug("<<부모글 좋아요 등록>> : "+ fav);
			ReviewFavVO reviewFav = reviewService.selectFav(fav);
			if(reviewFav!=null) {
				reviewService.deleteFav(reviewFav.getR_fav_num());
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}else {
				reviewService.insertFav(fav);
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}
			mapJson.put("count", reviewService.selectFavCount(fav.getR_num()));
		}
		return mapJson;
	}
	
	//=====리뷰댓글등록=====//
	@RequestMapping("/review/writeReply.do")
	@ResponseBody
	public Map<String, String> writeReply(ReviewReplyVO vo,HttpSession session, HttpServletRequest reqeuest){
		logger.debug("<<댓글 등록>> : " + vo);
		
		Map<String, String> mapJson = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			mapJson.put("result", "logout");
		}else {
			//회원번호 등록
			vo.setMem_num(user.getMem_num());
			//댓글 등록
			reviewService.insertReply(vo);
			mapJson.put("result", "success");
		}
		return mapJson;
	}
	
	//=====리뷰댓글목록=====//
	@RequestMapping("/review/listReply.do")
	@ResponseBody
	public Map<String, Object> listReply(@RequestParam(value="pageNum",defaultValue="1")int currentPage,@RequestParam int r_num,HttpSession session){
		logger.debug("<<currentPage>> : "+ currentPage);
		logger.debug("<<r_num>> : " + r_num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("r_num", r_num);
		
		//총 글의 개수
		int count = reviewService.selectReplyCount(map);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, 1, null); //1,null은 초기값으로 딱히 의미가 없음 앞에 3개가 중요
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		
		//목록 데이터 읽기
		List<ReviewReplyVO> list = null;
		if(count>0) {
			list = reviewService.selectListReply(map);
		}else {
			list = Collections.emptyList(); //count가 0이면 비어있는 리스트 생성
		}
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("count", count);
		mapJson.put("list", list);
		mapJson.put("rowCount", "rowCount");
		
		//=====로그인한 회원정보 세팅=====//
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user!=null) {
			mapJson.put("user_num", user.getMem_num());
		}
		return mapJson;
	}
	
	
	
	
	
	
}