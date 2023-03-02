package kr.spring.calendar.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.calendar.dao.CalendarMapper;
import kr.spring.calendar.vo.CalendarVO;
import kr.spring.calendar.vo.ParticipationVO;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional
public class CalenderServiceImpl implements CalendarService {
	@Autowired
	private CalendarMapper calendarMapper;
	
	@Override
	public void insertCalendar(CalendarVO calendarVO) {
		calendarMapper.insertCalendar(calendarVO);
	}

	@Override
	public List<CalendarVO> selectCalendarList() {
		return calendarMapper.selectCalendarList();
	}

	@Override
	public CalendarVO selectCalendar(Integer calendar_num) {
		return calendarMapper.selectCalendar(calendar_num);
	}

	@Override
	public void updateCalendar(CalendarVO calendarVO) {
		calendarMapper.updateCalendar(calendarVO);
	}

	@Override
	public void deleteCalendar(Integer calendar_num) {
		calendarMapper.deleteParticipateByCalnum(calendar_num);
		calendarMapper.deleteCalendar(calendar_num);
	}

	@Override
	public void insertParticipate(ParticipationVO participationVO) {
		calendarMapper.insertParticipate(participationVO);
	}

	@Override
	public int selectCountParticipate(Integer calendar_num) {
		return calendarMapper.selectCountParticipate(calendar_num);
	}
	
	@Override
	public List<MemberVO> selectParticipateMemberList(Integer calendar_num) {
		return calendarMapper.selectParticipateMemberList(calendar_num);
	}

	@Override
	public MemberVO selectMember(Integer mem_num) {
		return calendarMapper.selectMember(mem_num);
	}

	@Override
	public ParticipationVO selectParticipate(ParticipationVO participationVO) {
		return calendarMapper.selectParticipate(participationVO);
	}

	@Override
	public void deleteParticipate(Integer p_num) {
		calendarMapper.deleteParticipate(p_num);
	}
}
