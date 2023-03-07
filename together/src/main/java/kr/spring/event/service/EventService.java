package kr.spring.event.service;

import java.util.List;
import java.util.Map;

import kr.spring.event.vo.EventVO;



public interface EventService {
	//부모글
	public List<EventVO> selectList(
			Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertEvent(EventVO event);
	public EventVO selectEvent(Integer event_num);
	public void updateHit(Integer event_num);
	public void updateEvent(EventVO event);
	public void deleteEvent(Integer event_num);
	public void deleteFile(Integer event_num);

}




