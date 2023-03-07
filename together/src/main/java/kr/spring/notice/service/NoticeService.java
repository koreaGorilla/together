package kr.spring.notice.service;

import java.util.List;
import java.util.Map;

import kr.spring.notice.vo.NoticeVO;

public interface NoticeService {
	//부모글
	public List<NoticeVO> selectList(Map<String,Object> map);

	public int selectRowCount(Map<String,Object> map);
	public void insertNotice(NoticeVO notice);
	public NoticeVO selectNotice(Integer notice_num);
	public void updateHit(Integer notice_num);
	public void updateNotice(NoticeVO notice);
	public void deleteNotice(Integer notice_num);
	public void deleteFile(Integer notice_num);
}
