package kr.spring.findmem.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.findmem.dao.FindmemMapper;
import kr.spring.findmem.service.FindmemService;
import kr.spring.member.vo.MemberVO;

@Controller
public class FindmemController {
	private static final Logger logger=LoggerFactory.getLogger(FindmemController.class);

	
	@Autowired
	private FindmemService findmemService;
	
	@GetMapping("find/findList.do")
	public String findList() {
		
		return "findList";
	}
	//=====아이디 찾기=====//
	@GetMapping("find/findId.do")
	public String findIdJsp() {
		
		return "findId";
	}
	@PostMapping("find/findMyId")
	public String findId(@ModelAttribute("vo") MemberVO vo, Model model) {
	    String mem_name = vo.getMem_name();
	    String mem_cell = vo.getMem_cell();
	    String mem_id = findmemService.findId(mem_name, mem_cell);

	    if (mem_id == null) {
	        model.addAttribute("check", 1);
	    } else {
	        model.addAttribute("check", 0);
	        model.addAttribute("id", mem_id);
	        logger.debug("사용자 아이디: " + mem_id);
	    }
	    
	    return "findMyId";
	}
	
	
	//=====비밀번호 찾기=====//
	@GetMapping("find/checkCell.do")
	public String checkCell() {
		
		return "checkCell";
	}
	
	@PostMapping("find/findMyPw")
	public String findPw(@ModelAttribute("vo") MemberVO vo, Model model) {
		String mem_id = vo.getMem_name();
		String mem_cell = vo.getMem_cell();
		String cell = findmemService.findPw(mem_id, mem_cell);
	    String mem_pw = findmemService.findPassword(mem_id, mem_cell);
		
		if (cell == null) {
			model.addAttribute("check", 1);
		} else {
			model.addAttribute("check", 0);
	        model.addAttribute("cell", cell);
	        logger.debug("사용자 전화번호: " + cell);
	        model.addAttribute("password", mem_pw);
		}
		
		return "checkCell";
	}
}
