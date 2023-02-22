package kr.spring.member.service;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	public MemberVO selectCheckMember(String id);

}
