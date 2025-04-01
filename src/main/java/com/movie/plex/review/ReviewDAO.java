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
		
		//영화 리뷰 등록
		public int addMovieReview(ReviewDTO reviewDTO) throws Exception {
			return sqlSession.insert(NAMESPACE+"addMovieReview",reviewDTO);
		}
		//tv 리뷰 등록
		public int addTvReview(ReviewDTO reviewDTO) throws Exception {
			return sqlSession.insert(NAMESPACE+"addTvReview", reviewDTO);
		}
		//영화 리뷰 조회
		public List<ReviewDTO> getMovieReviewList() throws Exception {
			return sqlSession.selectList(NAMESPACE+"getMovieReviewList");
		}
		// tv 리뷰 조회
		public List<ReviewDTO> getTvReviewList() throws Exception {
			return sqlSession.selectList(NAMESPACE+"getTvReviewList");
		}
		//영화 리뷰 더보기
		public ReviewDTO getMovieReviewDetail(ReviewDTO reviewDTO) throws Exception{
			return sqlSession.selectOne(NAMESPACE+"getMovieReviewDetail", reviewDTO);
		}
		//tv 리뷰 더보기
		public ReviewDTO getTvReviewDetail(ReviewDTO reviewDTO) throws Exception{
			return sqlSession.selectOne(NAMESPACE+"getTvReviewDetail", reviewDTO);
		}
		//영화 리뷰 수정
		public int updateMovieReview(ReviewDTO reviewDTO) throws Exception{
			return sqlSession.update(NAMESPACE+"updateMovieReview", reviewDTO);
		}
		//tv 리뷰 수정
		public int updateTvReview(ReviewDTO reviewDTO) throws Exception{
			return sqlSession.update(NAMESPACE+"updateTvReview", reviewDTO);
		}
		//영화 리뷰 삭제
		public int deleteMovieReview(ReviewDTO reviewDTO) throws Exception{
			return sqlSession.delete(NAMESPACE+"deleteMovieReview",reviewDTO);
		}
		//tv 리뷰 삭제
		public int deleteTvReview(ReviewDTO reviewDTO) throws Exception{
			return sqlSession.delete(NAMESPACE+"deleteTvReview",reviewDTO);
		}
}
