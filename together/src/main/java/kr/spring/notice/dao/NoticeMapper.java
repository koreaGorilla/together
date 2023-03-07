package kr.spring.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.notice.vo.NoticeVO;

@Mapper
public interface NoticeMapper {
	//부모글
	@Select("select * from (select a.*, rownum rnum from (select * from notice order by notice_num desc) a)"
			+ "where rnum >= #{start} and rnum <= #{end}")
	public List<NoticeVO> selectList(
			                 Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO notice (notice_num,notice_title,"
		  + "notice_content,notice_file,notice_filename,ip,mem_num) "
		  + "VALUES (notice_seq.nextval,#{notice_title},"
		  + "#{notice_content},#{notice_file},#{notice_filename},"
		  + "#{ip},#{mem_num})")
	public void insertNotice(NoticeVO notice);
	@Select("SELECT * FROM notice n JOIN member m "
		  + "USING(mem_num) JOIN member_detail d "
		  + "USING(mem_num) WHERE n.notice_num=#{notice_num}")
	public NoticeVO selectNotice(Integer notice_num);
	@Update("UPDATE notice SET notice_hit=notice_hit+1 WHERE notice_num=#{notice_num}")
	public void updateHit(Integer notice_num);
	public void updateNotice(NoticeVO notice);
	@Delete("DELETE FROM notice WHERE notice_num=#{notice_num}")
	public void deleteNotice(Integer notice_num);
	@Update("UPDATE notice SET notice_file='',notice_filename='' WHERE notice_num=#{notice_num}")
	public void deleteFile(Integer notice_num);
	
}





