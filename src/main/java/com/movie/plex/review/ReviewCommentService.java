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
		
		public int updateComment (ReviewCommentDTO reviewCommentDTO) throws Exception {
			return reviewCommentDAO.updateComment(reviewCommentDTO);
		}
		
		public int deleteComment (Long commentId) throws Exception {
			return reviewCommentDAO.deleteComment(commentId);
		}
		
		public List<ReviewCommentDTO> getMyComments(Long userNum) throws Exception{
			return reviewCommentDAO.getMyComments(userNum);
		}
		
}
