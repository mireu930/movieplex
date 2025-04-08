package com.movie.plex.like;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewLikeService {

	@Autowired
	private ReviewLikeDAO reviewLikeDAO;
	
	public boolean toggleReviewLike(ReviewLikeDTO reviewLikeDTO) throws Exception {
		boolean isLiked = reviewLikeDAO.isLiked(reviewLikeDTO) >0;
		
		if (isLiked) {
			// 이미 눌렀으면 삭제
	        int result = reviewLikeDAO.deleteLike(reviewLikeDTO);
	        return false; // false를 반환해야 좋아요 해제 상태
	    } else {
	        // 안 눌렀으면 추가
	        int result = reviewLikeDAO.addLike(reviewLikeDTO);
	        return true; // true를 반환해야 좋아요 상태
	    }
		}
	
		public List<Long> getLikedReviewIds(Long userNum, Long kind) throws Exception {
	        return reviewLikeDAO.getLikedReviewIds(userNum, kind);
	    }
	
	
	
	
	
	
	
	
}
