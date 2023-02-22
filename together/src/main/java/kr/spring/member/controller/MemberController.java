package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;

@Controller
public class MemberController {
	private static final Logger logger = 
	  LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	//=========회원로그인============//
	//로그인 폼 호출
	@GetMapping("/member/login.do")
	public String formLogin() {
		return "memberLogin";
	}
	
	//로그인 폼에 전송된 데이터 처리
	@PostMapping("/member/login.do")
	public String submitLogin(@Valid MemberVO memberVO,
			              BindingResult result,
			              HttpSession session,
			              HttpServletResponse response) {
		
		logger.debug("<<회원로그인>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼을 호출
		//id와 passwd 필드만 체크
		if(result.hasFieldErrors("id") || 
				result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		
		//로그인 체크
		MemberVO member = null;
		try {
			member = memberService.selectCheckMember(
					                   memberVO.getMem_id());
			boolean check = false;
			
			if(member!=null) {
				//비밀번호 일치 여부 체크
				check = member.isCheckedPassword(
						       memberVO.getMem_pw());
			}
			if(check) {//인증 성공
				//자동로그인 체크
				
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				
				logger.debug("<<인증 성공>> : " + member.getMem_id());
				
				if(member.getMem_auth() == 9) {
					return "redirect:/main/admin.do";
				}else {
					return "redirect:/main/main.do";
				}
			}
			//인증 실패
			throw new AuthCheckException();
		}catch(AuthCheckException e) {
			//인증 실패로 로그인폼 호출
			if(member!=null && member.getMem_auth()==1) {
				//정지회원 메시지 표시
				result.reject("noAuthority");
			}else {
				result.reject("invalidIdOrPassword");
			}
			
			logger.debug("<<인증 실패>>");
			
			return formLogin();
		}
	}
	
	//=====회원로그아웃=====//
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session,
			          HttpServletResponse response) {
		
		//로그아웃
		session.invalidate();
		
		//자동로그인 클라이언트 쿠키 처리
		
		return "redirect:/main/main.do";
	}

}






