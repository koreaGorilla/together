package kr.spring.calendar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

import kr.spring.calendar.service.CalendarService;
import kr.spring.calendar.vo.CalendarVO;
import kr.spring.calendar.vo.ParticipationVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.party.vo.PartyVO;
import kr.spring.util.FileUtil;
import kr.spring.util.StringUtil;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
	private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);
	
	@Autowired
	private CalendarService calendarService;
	
	@ModelAttribute
	public CalendarVO initcommand() {
		return new CalendarVO();
	}
	
	//===========달력===========//
	@GetMapping("/calendar.do")
	public String calendar(HttpServletRequest request, Model model) {
		int party_num = Integer.parseInt(request.getParameter("party_num"));
		
		model.addAttribute("party_num", party_num);
		
		return "calendar";
	}
	
	//===========조회===========//
	//해당 파티 일정만 조회할 수 있게 수정해야 함
	@GetMapping("/calendarList.do")
	@ResponseBody
	public List<Map<String, Object>> calendarList(@RequestParam int party_num) {		
		JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        
        Map<String, Object> map = new HashMap<>();
		
		List<CalendarVO> list = calendarService.selectCalendarList(party_num);
		
		for (CalendarVO calendarVO : list) {
			String start = calendarVO.getStart_date() + " " + calendarVO.getStart_time();
			String end = calendarVO.getEnd_date() + " " + calendarVO.getEnd_time();
			
			map.put("id", calendarVO.getCalendar_num());
            map.put("title", calendarVO.getTitle());
            map.put("start", start);
            map.put("end", end);
            map.put("color", calendarVO.getColor());
            map.put("party_num", party_num);
 
            jsonObj = new JSONObject(map);
            jsonArr.add(jsonObj);
        }
		
		logger.info(jsonArr.toJSONString());
		
		return jsonArr;
	}
	
	//===========등록===========//
	@GetMapping("/calendarWrite.do")
	public String write(HttpServletRequest request, Model model) {
		int party_num = Integer.parseInt(request.getParameter("party_num"));
		
		model.addAttribute("party_num", party_num);
		
		return "calendarWrite";
	}
	
	@GetMapping("/calendarWrite2.do")
	public String write(@RequestParam String start_date, @RequestParam int party_num, Model model) {
		//int party_num = Integer.parseInt(request.getParameter("party_num"));
		
		model.addAttribute("party_num", party_num);
		model.addAttribute("start_date", start_date);
		
		return "calendarWrite";
	}
	
	@PostMapping("/calendarWrite.do")
	public String submit(@Valid CalendarVO calendarVO, BindingResult result, RedirectAttributes redirect, HttpSession session) {
		logger.debug("<<등록>> : " + calendarVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "calendar";
		}
		
		calendarVO.setMem_num(((MemberVO)session.getAttribute("user")).getMem_num());
		calendarVO.setParty_num(calendarVO.getParty_num());
		
		calendarService.insertCalendar(calendarVO);
		
		//RedirectAttributes 객체는 리다이렉트 시점에 한 번만 사용되는 데이터를 전송
		//브라우저에 데이터를 전송하지만 URL상에 보이지 않는 숨겨진 데이터의 형태로 전달
		redirect.addFlashAttribute("result", "success");
		
		return "redirect:/calendar/calendar.do?party_num=" + calendarVO.getParty_num();
	}
	
	//===========상세===========//	
	@RequestMapping("/calendarDetail.do")
	@ResponseBody
	public ModelAndView detail(@RequestParam int calendar_num, @RequestParam int party_num) {
		logger.debug("<<상세>> : " + calendar_num);
		
		CalendarVO calendar = calendarService.selectCalendar(calendar_num);
		
		calendar.setTitle(StringUtil.useNoHtml(calendar.getTitle()));
		calendar.setContent(StringUtil.useBrNoHtml(calendar.getContent()));
		
		int count = calendarService.selectCountParticipate(calendar_num);
		
		List<MemberVO> list = null;
		
		if(count > 0) {
			list = calendarService.selectParticipateMemberList(calendar_num);
		}
		
		logger.debug("<<멤버>> : " + list);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calendarDetail");
		mav.addObject("calendar", calendar);
		mav.addObject("count", count);
		mav.addObject("member", list);
		mav.addObject("party_num", party_num);
		
		return mav;
	}
	
	//===========참여 여부===========//
	@RequestMapping("/getParticipation.do")
	@ResponseBody
	public Map<String, String> getParticipation(ParticipationVO participationVO, HttpSession session){
		Map<String, String> mapJson = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user == null) {
			mapJson.put("status", "notparticipation");
		} else {
			participationVO.setMem_num(user.getMem_num());
			
			ParticipationVO participation = calendarService.selectParticipate(participationVO);
			
			if(participation != null) {
				mapJson.put("status", "participation");
			} else {
				mapJson.put("status", "notparticipation");
			}
		}
		
		return mapJson;
	}
	
	//===========수정===========//
	//수정 폼	
	@GetMapping("/calendarModify.do")
	public String modify(@RequestParam int calendar_num, @RequestParam int party_num, Model model) {
		logger.debug("<<수정 폼>> : " + calendar_num + ", " + party_num);
		
		CalendarVO calendar = calendarService.selectCalendar(calendar_num);
		
		model.addAttribute("calendar", calendar);
		
		return "calendarModify";
	}
	
	//폼에서 전송된 데이터 처리
	@RequestMapping("/calendarModify.do")
	public String modify(@Valid CalendarVO calendarVO, BindingResult result,  HttpServletRequest request, Model model) {
		logger.debug("<<수정>> : " + calendarVO);
		
		if(result.hasErrors()) {
			return "/calendar/calendar.do";
		}
		
		calendarService.updateCalendar(calendarVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "수정 완료");
		model.addAttribute("url", request.getContextPath() + "/calendar/calendar.do?party_num=" + calendarVO.getParty_num());
		
		return "common/resultView";
	}
	
	//===========삭제===========//
	@RequestMapping("/calendarDelete.do")
	public String delete(@RequestParam int calendar_num, Model model, HttpServletRequest request) {
		logger.debug("<<삭제>> : " + calendar_num);
		
		calendarService.deleteCalendar(calendar_num);
		
		//View에 표시할 메시지
		model.addAttribute("message", "삭제 완료");
		model.addAttribute("url", request.getContextPath() + "/calendar/calendar.do");
		
		return "common/resultView";
	}
	
	//===========참여버튼===========//
	@RequestMapping("/participation.do")
	@ResponseBody
	public Map<String, String> participate(ParticipationVO participationVO, @RequestParam int calendar_num, HttpSession session) {
		logger.debug("<<참여>> : " + calendar_num);
		
		Map<String, String> mapJson = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user == null) {
			mapJson.put("result", "logout");
		} else {
			participationVO.setCalendar_num(calendar_num);
			participationVO.setMem_num(user.getMem_num());
			
			calendarService.insertParticipate(participationVO);
			
			mapJson.put("result", "success");
		}
		return mapJson;
	}
	
	//===========미참여버튼===========//
	@RequestMapping("/notparticipation.do")
	@ResponseBody
	public Map<String, String> notparticipate(ParticipationVO participationVO, HttpSession session) {		
		Map<String, String> mapJson = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		participationVO.setMem_num(user.getMem_num());
		
		ParticipationVO participation = calendarService.selectParticipate(participationVO);
		
		calendarService.deleteParticipate(participation.getP_num());
		
		mapJson.put("result", "success");
		
		return mapJson;
	}
	
	//===========이미지 출력===========//
	//프로필 사진 출력(회원번호 지정)
	@RequestMapping("/profileImg.do")
	public String getProfileByMem_num(@RequestParam int mem_num, HttpSession session, HttpServletRequest request, Model model) {
		MemberVO memberVO = calendarService.selectMember(mem_num);
		
		viewProfile(memberVO, request, model);
		
		return "imageView";
	}
	
	//프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, Model model) {
		if(memberVO.getPhoto_name() == null) {
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
				
			model.addAttribute("imageFile", readbyte);
			model.addAttribute("filename", "face.png");
		} else {
			model.addAttribute("imageFile", memberVO.getPhoto());
			model.addAttribute("filename", memberVO.getPhoto_name());
		}
	}
}
