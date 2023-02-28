package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.vo.ReviewVO;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public List<ReviewVO> selectList(Map<String, Object> map) {
		return reviewMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return reviewMapper.selectRowCount(map);
	}

	@Override
	public void insertReview(ReviewVO review) {
		reviewMapper.insertReview(review);
	}

	@Override
	public ReviewVO selectReview(Integer r_num) {
		return reviewMapper.selectReview(r_num);
	}

	@Override
	public void updateReview(ReviewVO review) {
		reviewMapper.updateReview(review);
		
	}

	@Override
	public void deleteReview(Integer r_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePhoto(Integer r_num) {
		reviewMapper.deletePhoto(r_num);
	}

	@Override
	public List<String> selectPartyname(Integer mem_num) {
		return reviewMapper.selectPartyname(mem_num);
		
	}
	
}
