package com.movie.plex.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
		
		@Autowired
		private ReviewDAO reviewDAO;
		
		
		public int addReview(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.addReview(reviewDTO);
		}
		
		public List<ReviewDTO> getReviewList(Long contentId) throws Exception{
			return reviewDAO.getReviewList(contentId);
		}
		
		public ReviewDTO getReviewDetail(Long reviewId ) throws Exception {
			return reviewDAO.getReviewDetail(reviewId);
		}
		
		public int updateReview(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.updateReview(reviewDTO);
		}
		
		public int deleteReview(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.deleteReview(reviewDTO);
		}
		
		
		
}
