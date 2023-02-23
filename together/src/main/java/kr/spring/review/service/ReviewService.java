package kr.spring.review.service;

import java.util.List;
import java.util.Map;

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
	
	public List<String> selectPartyname(Integer mem_num);
}
