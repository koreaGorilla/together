package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.review.vo.ReviewFavVO;
import kr.spring.review.vo.ReviewReplyVO;
import kr.spring.review.vo.ReviewVO;

public interface ReviewService {
	//리뷰 부모글
	public List<ReviewVO> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String , Object> map);
	public void insertReview(ReviewVO review);
	public ReviewVO selectReview(Integer r_num);
	public void updateReview(ReviewVO review);
	public void deleteReview(Integer r_num);
	public void deletePhoto(Integer r_num);
	
	//가입한 파티 이름 가져오기
	public List<String> selectPartyname(Integer mem_num);
	
	//리뷰 좋아요
	public ReviewFavVO selectFav(ReviewFavVO fav);
	public int selectFavCount(Integer r_num);
	public void insertFav(ReviewFavVO fav);
	public void deleteFav(Integer r_fav_num);
	
	//리뷰 댓글
	public List<ReviewReplyVO> selectListReply(Map<String, Object> map);
	public ReviewReplyVO selectReply(Integer c_num);
	public int selectReplyCount(Map<String, Object> map);
	public void insertReply(ReviewReplyVO reviewReply);
	public void updateReply(ReviewReplyVO reviewReply);
	public void deleteReply(Integer c_num);
}
