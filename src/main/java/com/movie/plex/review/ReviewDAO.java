package com.movie.plex.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAO {
	
		@Autowired
		private SqlSession sqlSession;
		
		
		private final String NAMESPACE="com.movie.plex.review.ReviewDAO.";
		
		//리뷰 작성
		public int addReview(ReviewDTO reviewDTO) throws Exception {
			return sqlSession.insert(NAMESPACE+"addReview",reviewDTO);
		}
		
		//리뷰 조회
		public List<ReviewDTO> getReviewList(Long contentsId) throws Exception {
			return sqlSession.selectList(NAMESPACE+"getReviewList",contentsId);
		}
		
		// 리뷰 더보기
		public ReviewDTO getReviewDetail(Long reviewId) throws Exception{
			return sqlSession.selectOne(NAMESPACE+"getReviewDetail", reviewId);
		}
		
		//리뷰 수정
		public int updateReview(ReviewDTO reviewDTO) throws Exception{
			return sqlSession.update(NAMESPACE+"updateReview", reviewDTO);
		}
		
		//리뷰 삭제
		public int deleteReview(Long reviewId) throws Exception{
			return sqlSession.delete(NAMESPACE+"deleteReview",reviewId);
		}
		
		//리뷰 중복조회
		public int checkReviewExists(Long userNum, Long contentId, Long kind) throws Exception {
			 Map<String, Object> paramMap = new HashMap<String, Object>();
			    paramMap.put("userNum", userNum);
			    paramMap.put("contentId", contentId);
			    paramMap.put("kind", kind);
			
			return sqlSession.selectOne(NAMESPACE+"checkReviewExists", paramMap);
		}
		
		public List<ReviewDTO> getMyReviews(Long userNum) throws Exception {
			return sqlSession.selectList(NAMESPACE+"getMyReviews", userNum);
		}
		
		
		
		
		
		
}
