package kr.spring.findmem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.findmem.dao.FindmemMapper;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class FindmemServiceImpl implements FindmemService{

	@Autowired
	private FindmemMapper findmemMapper;

	@Override
	public String findId(String mem_name, String mem_cell) {
		return findmemMapper.findId(mem_name, mem_cell);
	}

	@Override
	public String findPassword(String mem_cell, String mem_id) {
		return findmemMapper.findPassword(mem_cell, mem_id);
	}


	@Override
	public String findPw(String mem_id, String mem_cell) {
		return findmemMapper.findPw(mem_id, mem_cell);
	}

}
