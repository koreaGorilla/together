package kr.spring.notice.controller;

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

import kr.spring.notice.service.NoticeService;
import kr.spring.notice.vo.NoticeVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;

@Controller
public class NoticeController {
   private static final Logger logger = 
                         LoggerFactory.getLogger(
                         NoticeController.class);
   private int rowCount = 20;
   @Autowired
   private NoticeService noticeService;
   
   //자바빈(VO) 초기화
   @ModelAttribute
   public NoticeVO initCommand() {
      return new NoticeVO();
   }
   
   //=======게시판 글 목록=========//
   @RequestMapping("/notice/list.do")
   public ModelAndView process(
     @RequestParam(value="pageNum",defaultValue="1") 
                      int currentPage,
                   String keyfield,String keyword) {
      
      Map<String,Object> map = 
            new HashMap<String,Object>();
      map.put("keyfield", keyfield);
      map.put("keyword", keyword);
      
      //글의 총개수 또는 검색된 글의 개수
      int count = noticeService.selectRowCount(map);
      
      logger.debug("<<count>> : " + count);
      
      //페이지 처리
      PagingUtil page = 
            new PagingUtil(keyfield,keyword,
             currentPage,count,20,10,"list.do");
            
      List<NoticeVO> list = null;
      if(count > 0) {
         map.put("start", page.getStartRow());
         map.put("end", page.getEndRow());
         
         list = noticeService.selectList(map);
      }
         
      ModelAndView mav = new ModelAndView();
      mav.setViewName("noticeList");
      mav.addObject("count", count);
      mav.addObject("list", list);
      mav.addObject("page", page.getPage());
      
      return mav;
   }
   
   //=======글쓰기=========//
   //등록 폼 호출
   @GetMapping("/notice/write.do")
   public String form() {
      return "noticeWrite";
   }
   
   //등록 폼에서 전송된 데이터 처리
   @PostMapping("/notice/write.do")
   public String submit(@Valid NoticeVO noticeVO,
               BindingResult result,
               HttpServletRequest request,
               RedirectAttributes redirect,
               HttpSession session) {
      logger.debug("<<공지사항 글쓰기>> : " + noticeVO);
      //logger.debug("<<공지사항 파일 용량>>: "+noticeVO.getNotice_file().length);

      if(noticeVO.getNotice_file().length >= 52428800) {
         result.reject("limitUploadSize");
      }
      
      //유효성 체크 결과 오류가 있으면 폼을 호출
      if(result.hasErrors()) {
         return form();
      }
      
      //회원번호 셋팅
      noticeVO.setMem_num(
         ((MemberVO)session.getAttribute("user")).getMem_num());
      //ip셋팅
      noticeVO.setIp(request.getRemoteAddr());
      //글쓰기
      noticeService.insertNotice(noticeVO);
      
      redirect.addFlashAttribute("result","success");
      
      return "redirect:/notice/list.do";
   }
   
   //========게시판 글상세=======//
   @RequestMapping("/notice/detail.do")
   public ModelAndView process(
                   @RequestParam int notice_num) {
      logger.debug("<<notice_num>> : " + notice_num);
      
      //해당 글의 조회수 증가
      noticeService.updateHit(notice_num);
      
      NoticeVO notice = 
            noticeService.selectNotice(notice_num);
      
      //제목에 태그를 허용하지 않음
      notice.setNotice_title(StringUtil.useNoHtml(
                              notice.getNotice_title()));
      //CKEditor 사용시 주석 처리
      //내용에 태그를 허용하지 않음
      //notice.setContent(StringUtil.useBrNoHtml(
      //                      board.getContent()));
                               //뷰이름      속성명   속성값
      return new ModelAndView("noticeView","notice",notice);
   }
   
   //=====게시판 글수정=====//
   //수정 폼 호출
   @GetMapping("/notice/update.do")
   public String formUpdate(
                   @RequestParam int notice_num,
                   Model model) {
      NoticeVO noticeVO = 
            noticeService.selectNotice(notice_num);
      
      model.addAttribute("noticeVO",noticeVO);
      
      return "noticeModify";
   }
   
   //수정 폼에서 전송된 데이터 처리
   @PostMapping("/notice/update.do")
   public String submitUpdate(@Valid NoticeVO noticeVO,
                            BindingResult result,
                            HttpServletRequest request,
                            Model model) {
      
      logger.debug("<<글수정>> : " + noticeVO);

      if(noticeVO.getNotice_filename() != "") {
          if(noticeVO.getNotice_file().length >= 52428800) {//5Mb
              result.reject("limitUploadSize");//에러필드를 따로 지정 안했기에 폼 맨 위에 호출
           }
      }

      //유효성 체크 결과 오류가 있으면 폼을 호출
      if(result.hasErrors()) {
         //title 또는 content가 입력되지 않아서 유효성
         //체크에 걸리면 파일 정보를 잃어버리기 때문에
         //폼을 호출할 때 파일 정보를 다시 셋팅
         NoticeVO vo = noticeService.selectNotice(
                       noticeVO.getNotice_num());
         
         if(noticeVO.getNotice_filename() != "") {
        	 noticeVO.setNotice_filename(vo.getNotice_filename());
         }
         
         return "noticeModify";
      }
      
      //ip셋팅
      noticeVO.setIp(request.getRemoteAddr());
      
      //글수정
      noticeService.updateNotice(noticeVO);
      
      //View에 표시할 메시지
      model.addAttribute("message", "글수정 완료!");
      model.addAttribute("url", 
         request.getContextPath()
         +"/notice/detail.do?notice_num="+noticeVO.getNotice_num());
      
      return "common/resultView";
   }
   
   //=====게시판 글삭제=======//
   @RequestMapping("/notice/delete.do")
   public String submitDelete(
         @RequestParam int notice_num,
         Model model,
         HttpServletRequest request) {
      
      logger.debug("<<게시판 글삭제>> : " + notice_num);
      
      //글삭제
      noticeService.deleteNotice(notice_num);
      
      return "redirect:/notice/list.do";
   }
   
   
   //=====파일 삭제=======//
   @RequestMapping("/notice/deleteFile.do")
   @ResponseBody
   public Map<String,String> processFile(
                            int notice_num,
                            HttpSession session){
      Map<String,String> mapJson = 
            new HashMap<String,String>();
      
      MemberVO user = 
          (MemberVO)session.getAttribute("user");
      if(user==null) {
         mapJson.put("result", "logout");
      }else {
    	  noticeService.deleteFile(notice_num);
         
         mapJson.put("result", "success");
      }
      
      return mapJson;
   }
   
   //=====이미지 출력=====//
   @RequestMapping("/notice/imageView.do")
   public ModelAndView viewImage(
                 @RequestParam int notice_num) {
      
      NoticeVO notice = 
    		 noticeService.selectNotice(notice_num);
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("imageView");
      
      mav.addObject("imageFile", notice.getNotice_file());
      mav.addObject("filename", notice.getNotice_filename());

      return mav;
   }
   
   //=====파일 다운로드====//
   @RequestMapping("/notice/file.do")
   public ModelAndView download(
                  @RequestParam int notice_num) {
      NoticeVO notice = 
    		  noticeService.selectNotice(notice_num);
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("downloadView");
      mav.addObject("downloadFile", 
    		  notice.getNotice_file());
      mav.addObject("filename", notice.getNotice_filename());
      
      return mav;
   }
   
}


