package kr.spring.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.spring.member.vo.MemberVO;
import kr.spring.review.service.ReviewService;
import kr.spring.review.vo.ReviewVO;

public class WriterCheckInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(WriterCheckInterceptor.class);
	
	@Autowired //입력한 사용자가 누군지 알아야 하기 때문에 한 건의 데이터 주입 받기위해 명시
	private ReviewService reviewService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response, 
							Object handler) throws Exception{
		
		logger.debug("<<로그인 회원번호와 작성자 회원번호 일치 여부 체크>>");
		
		//로그인 회원번호 구하기
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//리뷰 작성자의 회원버호 구하기
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		ReviewVO review= reviewService.selectReview(r_num);
		
		if(user!=null) {
			logger.debug("<<로그인 회원번호>> : " + user.getMem_num());
			logger.debug("<<리뷰 작성자 회원번호>> : " + review.getMem_num());
		}
		
		//로그인 회원번호와 작성자 회원번호 일치 여부 체크
		if(user == null || user.getMem_num()!=review.getMem_num()) {
			logger.debug("<<로그인 회원번호와 작성자 회원번호 불일치>>");
			
			request.setAttribute("accessMsg", "로그인 아이디와 작성자 아이디 불일치");
			request.setAttribute("accessBtn", "리뷰 목록");
			request.setAttribute("accessUrl", request.getContextPath()+"/review/list.do");
			
			//포워드 방식으로 화면 호출
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/notice.jsp");
			dispatcher.forward(request, response);
			
			return false;
		}
		
		logger.debug("<<로그인 회원번호와 작성자 회원번호 일치>>");
		
		
		return true;
	}
}
