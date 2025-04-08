package com.movie.plex.review;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewCommentDAO {
		
		@Autowired
		private SqlSession sqlSession;
		
		private final String NAMESPACE="com.movie.plex.review.ReviewCommentDAO.";
		
			//리뷰댓글 작성
			public int addComment(ReviewCommentDTO reviewCommentDTO) throws Exception {
				return sqlSession.insert(NAMESPACE+"addComment", reviewCommentDTO);
			}
			
			//리뷰 댓글 조회
			public List<ReviewCommentDTO> getCommentsByReviewId(Long reviewId) throws Exception{
				return sqlSession.selectList(NAMESPACE+"getCommentsByReviewId", reviewId);
			}
}

