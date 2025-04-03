package com.movie.plex.review;

import java.util.List;

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
		public List<ReviewDTO> getReviewList(Long contentId) throws Exception {
			return sqlSession.selectList(NAMESPACE+"getReviewList");
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
		public int deleteReview(ReviewDTO reviewDTO) throws Exception{
			return sqlSession.delete(NAMESPACE+"deleteReview",reviewDTO);
		}
		
		
		
		
		
}
