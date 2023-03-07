package kr.spring.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.review.vo.ReviewFavVO;
import kr.spring.review.vo.ReviewReplyVO;
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
	@Delete("delete from review where r_num=#{r_num}")
	public void deleteReview(Integer r_num);
	@Update("update review set r_photo='',r_photoname='' where r_num=#{r_num}")
	public void deletePhoto(Integer r_num);
	
	@Select("select party_name from party p join party_member b on p.party_num=b.party_num where b.mem_num=#{mem_num}")
	public List<String> selectPartyname(Integer mem_num); //리뷰작성 페이지에 파티 이름 가져오기
	
	
	//리뷰 좋아요
	@Select("select * from review_fav where r_num=#{r_num} and mem_num=#{mem_num}")
	public ReviewFavVO selectFav(ReviewFavVO fav);
	@Select("select count(*) from review_fav where r_num=#{r_num}")
	public int selectFavCount(Integer r_num);
	@Insert("insert into review_fav(r_fav_num,r_num,mem_num) values(reviewfav_seq.nextval,#{r_num},#{mem_num})")
	public void insertFav(ReviewFavVO fav);
	@Delete("delete from review_fav where r_fav_num=#{r_fav_num}")
	public void deleteFav(Integer r_fav_num);
	@Delete("delete from review_fav where r_num=#{r_num}")
	public void deleteFavByReviewNum(Integer r_num);
	
	//댓글
	public List<ReviewReplyVO> selectListReply(Map<String, Object> map);
	@Select("select * from review_comments where c_num=#{c_num}")
	public ReviewReplyVO selectReply(Integer c_num);
	@Select("select count(*) from review_comments join member using(mem_num) where r_num=#{r_num}") 
	public int selectReplyCount(Map<String, Object> map);
	@Insert("insert into review_comments(c_num,c_content,mem_num,r_num) values(reviewcomm_seq.nextval,#{c_content},#{mem_num},#{r_num})")
	public void insertReply(ReviewReplyVO reviewReply);
	@Update("update review_comments set c_modify_date=sysdate,c_content=#{c_content} where c_num=#{c_num}")
	public void updateReply(ReviewReplyVO reviewReply);
	@Delete("delete from review_comments where c_num=#{c_num}")
	public void deleteReply(Integer c_num);
	@Delete("delete from review_comments where r_num=#{r_num}")
	public void deleteReplyByReviewNum(Integer r_num);
		
}
