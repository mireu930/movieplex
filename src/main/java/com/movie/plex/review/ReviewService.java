package com.movie.plex.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
		
		@Autowired
		private ReviewDAO reviewDAO;
		
		
		public int addReview(ReviewDTO reviewDTO) throws Exception{
			return reviewDAO.addReview(reviewDTO);
		}
		
		public List<ReviewDTO> getReviewList(Long contentId, Long kind) throws Exception{
			Map<String, Object> param = new HashMap<String, Object>();
		    param.put("contentId", contentId);
		    param.put("kind", kind);
		    return reviewDAO.getReviewList(param);
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
		
		public int checkReviewExists(Long userNum, Long contentId, Long kind) throws Exception{
			return reviewDAO.checkReviewExists(userNum, contentId, kind);
		}
		
		
}
