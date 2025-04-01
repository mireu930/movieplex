package com.movie.plex.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
		
		@Autowired
		private ReviewDAO reviewDAO;
		
		public int addMovieReview(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.addMovieReview(reviewDTO);
		}
		
		public int addTvReivew(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.addTvReview(reviewDTO);
		}
		
		public List<ReviewDTO> getMovieReviewList() throws Exception{
			return reviewDAO.getMovieReviewList();
		}
		
		public List<ReviewDTO> getTvReviewList() throws Exception{
			return reviewDAO.getTvReviewList();
		}
		
		public ReviewDTO getMovieReviewDetail(ReviewDTO reviewDTO) throws Exception {
			return reviewDAO.getMovieReviewDetail(reviewDTO);
		}
		
		public ReviewDTO getTvReviewDetail(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.getTvReviewDetail(reviewDTO);
		}
		
		public int updateMovieReview(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.updateMovieReview(reviewDTO);
		}
		
		public int updateTvReview(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.updateTvReview(reviewDTO);
		}
		
		public int deleteMovieReview(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.deleteMovieReview(reviewDTO);
		}
		
		public int deleteTvReview(ReviewDTO reviewDTO) throws Exception {
			return reviewDAO.deleteMovieReview(reviewDTO);
		}
}
