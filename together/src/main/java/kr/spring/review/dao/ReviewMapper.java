package kr.spring.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.review.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	//리뷰 부모글
	public List<ReviewVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String , Object> map);
	@Insert("insert into review(r_num,party_name,r_content, r_photo, r_photoname, mem_num) values(review_seq.nextval, #{party_name},#{r_content},#{r_photo},#{r_photoname},#{mem_num}) ")
	public void insertReview(ReviewVO review);
	@Select("select * from review r join member m using(mem_num) join member_detail d using(mem_num) where r.r_num=#{r_num}")
	public ReviewVO selectReview(Integer r_num);
	public void updateReview(ReviewVO review);
	public void deleteReview(Integer r_num);
	@Update("update review set r_photo='',r_photoname='' where r_num=#{r_num}")
	public void deletePhoto(Integer r_num);
	
	@Select("select party_name from party p join party_member b on p.party_num=b.party_num where b.mem_num=#{mem_num}")
	public List<String> selectPartyname(Integer mem_num); //리뷰작성 페이지에 파티 이름 가져오기
	
	
}
