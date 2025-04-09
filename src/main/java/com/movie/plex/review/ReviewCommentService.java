package com.movie.plex.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommentService {
		
		@Autowired
		private ReviewCommentDAO reviewCommentDAO;
		
		public int addComment(ReviewCommentDTO reviewCommentDTO) throws Exception {
			return reviewCommentDAO.addComment(reviewCommentDTO);
		}
	
		public List<ReviewCommentDTO> getCommentsByReviewId(Long reviewId) throws Exception {
			return reviewCommentDAO.getCommentsByReviewId(reviewId);
		}
		
		
}
