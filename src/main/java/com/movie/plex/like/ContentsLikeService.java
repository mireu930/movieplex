package com.movie.plex.like;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentsLikeService {
	
	@Autowired
	private ContentsLikeDAO contentsLikeDAO;
	
	public boolean toggleLike(ContentsLikeDTO contentsLikeDTO) throws Exception{
		boolean isLiked = contentsLikeDAO.isLiked(contentsLikeDTO) >0;
		
		if (isLiked) {
			// 이미 눌렀으면 삭제
	        int result = contentsLikeDAO.deleteLike(contentsLikeDTO);
	        return result <= 0; // false를 반환해야 좋아요 해제 상태
	    } else {
	        // 안 눌렀으면 추가
	        int result = contentsLikeDAO.addLike(contentsLikeDTO);
	        return result > 0; // true를 반환해야 좋아요 상태
	    }
		}
	
		public List<Long> getLikedContentsIds(Long userNum, Long kind) throws Exception{
	        return contentsLikeDAO.getLikedContentsIds(userNum, kind);
	    }
		
		
		

	
	
	
	
}
