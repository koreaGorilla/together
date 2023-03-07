package kr.spring.event.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.event.dao.EventMapper;
import kr.spring.event.vo.EventVO;

@Service
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventMapper eventMapper;
	
	@Override
	public List<EventVO> selectList(Map<String, Object> map) {
		return eventMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return eventMapper.selectRowCount(map);
	}

	@Override
	public void insertEvent(EventVO event) {
		eventMapper.insertEvent(event);
	}

	@Override
	public EventVO selectEvent(Integer event_num) {
		return eventMapper.selectEvent(event_num);
	}

	@Override
	public void updateHit(Integer event_num) {
		eventMapper.updateHit(event_num);
	}

	@Override
	public void updateEvent(EventVO event) {
		eventMapper.updateEvent(event);
	}

	@Override
	public void deleteFile(Integer event_num) {
		eventMapper.deleteFile(event_num);
	}

	@Override
	public void deleteEvent(Integer event_num) {
		// TODO Auto-generated method stub
		
	}

}




