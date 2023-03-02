package kr.spring.calendar.service;

import java.util.List;
import java.util.Map;

import kr.spring.calendar.vo.CalendarVO;
import kr.spring.calendar.vo.ParticipationVO;
import kr.spring.member.vo.MemberVO;

public interface CalendarService {
	public void insertCalendar(CalendarVO calendarVO);
	public List<CalendarVO> selectCalendarList();
	public CalendarVO selectCalendar(Integer calendar_num);
	public void updateCalendar(CalendarVO calendarVO);
	public void deleteCalendar(Integer calendar_num);
	public void insertParticipate(ParticipationVO participationVO);
	public int selectCountParticipate(Integer calendar_num);
	public List<MemberVO> selectParticipateMemberList(Integer calendar_num);
	public MemberVO selectMember(Integer mem_num);
	public ParticipationVO selectParticipate(ParticipationVO participationVO);
	public void deleteParticipate(Integer p_num);
}
