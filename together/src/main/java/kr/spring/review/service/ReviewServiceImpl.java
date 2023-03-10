package kr.spring.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.party.vo.PartyVO;
import kr.spring.review.dao.ReviewMapper;
import kr.spring.review.vo.ReviewFavVO;
import kr.spring.review.vo.ReviewReplyVO;
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
		reviewMapper.deleteFavByReviewNum(r_num);//리뷰 좋아요 삭제
		reviewMapper.deleteReplyByReviewNum(r_num);//리뷰 댓글 삭제
		reviewMapper.deleteReview(r_num);//리뷰 삭제
		
	}

	@Override
	public void deletePhoto(Integer r_num) {
		reviewMapper.deletePhoto(r_num);
	}

	@Override
	public List<String> selectPartyname(Integer mem_num) {
		return reviewMapper.selectPartyname(mem_num);
		
	}

	@Override
	public ReviewFavVO selectFav(ReviewFavVO fav) {
		return reviewMapper.selectFav(fav);
	}

	@Override
	public int selectFavCount(Integer r_num) {
		return reviewMapper.selectFavCount(r_num);
	}

	@Override
	public void insertFav(ReviewFavVO fav) {
		reviewMapper.insertFav(fav);
	}

	@Override
	public void deleteFav(Integer r_fav_num) {
		reviewMapper.deleteFav(r_fav_num);
	}

	@Override
	public List<ReviewReplyVO> selectListReply(Map<String, Object> map) {
		return reviewMapper.selectListReply(map);
	}

	@Override
	public ReviewReplyVO selectReply(Integer c_num) {
		return reviewMapper.selectReply(c_num);
	}

	@Override
	public int selectReplyCount(Map<String, Object> map) {
		return reviewMapper.selectReplyCount(map);
	}

	@Override
	public void insertReply(ReviewReplyVO reviewReply) {
		reviewMapper.insertReply(reviewReply);
	}

	@Override
	public void updateReply(ReviewReplyVO reviewReply) {
		reviewMapper.updateReply(reviewReply);
	}

	@Override
	public void deleteReply(Integer c_num) {
		reviewMapper.deleteReply(c_num);
	}

	@Override
	public int partyMemberCount(Integer mem_num) {
		return reviewMapper.partyMemberCount(mem_num);
	}
	
	//@Override
	//public List<ReviewVO> selectfavcount2() {
	//	return reviewMapper.selectfavcount2();
	//}
	
}
