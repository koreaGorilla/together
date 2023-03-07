package kr.spring.event.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.event.vo.EventVO;

@Mapper
public interface EventMapper {
	//부모글
	public List<EventVO> selectList(
			                 Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO event (event_num,event_title,"
		  + "event_content,event_file,event_filename,"
		  +	"event_start,event_end,estart_time,eend_time,mem_num) "
		  + "VALUES (event_seq.nextval,#{title},"
		  + "#{content},#{uploadfile},#{filename},"
		  + "#{event_start},#{event_end},#{estart_time},#{eend_time},"
		  + "#{mem_num})")
	public void insertEvent(EventVO event);
	@Select("SELECT * FROM event e JOIN member m "
		  + "USING(mem_num) JOIN member_detail d "
		  + "USING(mem_num) WHERE e.event_num=#{event_num}")
	public EventVO selectEvent(Integer event_num);
	@Update("UPDATE event SET event_hit=event_hit+1 WHERE event_num=#{event_num}")
	public void updateHit(Integer event_num);
	public void updateEvent(EventVO event);
	@Delete("DELETE FROM event WHERE event_num=#{event_num}")
	public void deleteEvent(Integer event_num);
	@Update("UPDATE event SET event_file='',event_filename='' WHERE event_num=#{event_num}")
	public void deleteFile(Integer event_num);
	
	//댓글

}





