package kr.spring.calendar.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.calendar.vo.CalendarVO;
import kr.spring.calendar.vo.ParticipationVO;
import kr.spring.member.vo.MemberVO;

@Mapper
public interface CalendarMapper {
	@Insert("insert into party_calendar (calendar_num, party_num, mem_num, title, content, start_date, end_date, start_time, end_time, location, color) values (party_calendar_seq.nextval, #{party_num}, #{mem_num}, #{title}, #{content}, #{start_date}, #{end_date}, #{start_time}, #{end_time}, #{location}, #{color})")
	public void insertCalendar(CalendarVO calendarVO);
	
	@Select("select * from party_calendar where party_num = #{party_num}")
	public List<CalendarVO> selectCalendarList(Integer party_num);
	
	@Select("select * from party_calendar where calendar_num = #{calendar_num}")
	public CalendarVO selectCalendar(Integer calendar_num);
	
	@Update("update party_calendar set title = #{title}, content = #{content}, start_date = #{start_date}, end_date = #{end_date}, start_time = #{start_time}, end_time = #{end_time}, location = #{location}, color = #{color} where calendar_num = #{calendar_num}")
	public void updateCalendar(CalendarVO calendarVO);
	
	@Delete("delete from party_calendar where calendar_num = #{calendar_num}")
	public void deleteCalendar(Integer calendar_num);
	
	@Insert("insert into participation (p_num, calendar_num, mem_num) values (participation_seq.nextval, #{calendar_num}, #{mem_num})")
	public void insertParticipate(ParticipationVO participationVO);
	
	@Select("select count(*) from participation where participate = 0 and calendar_num = #{calendar_num}")
	public int selectCountParticipate(Integer calendar_num);
	
	@Select("select * from member_detail where mem_num in (select mem_num from participation where participate = 0 and calendar_num = #{calendar_num})")
	public List<MemberVO> selectParticipateMemberList(Integer calendar_num);
	
	@Select("select * from member m join member_detail d on m.mem_num = d.mem_num where m.mem_num = #{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	
	@Select("select * from participation where calendar_num = #{calendar_num} and mem_num = #{mem_num}")
	public ParticipationVO selectParticipate(ParticipationVO participationVO);
	
	@Delete("delete from participation where p_num = #{p_num}")
	public void deleteParticipate(Integer p_num);
	
	@Delete("delete from participation where calendar_num = ${calendar_num}")
	public void deleteParticipateByCalnum(Integer calendar_num);
}
