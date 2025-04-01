package com.movie.plex.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentsLikeService {
	
	@Autowired
	private ContentsLikeDAO contentsLikeDAO;
	
	public boolean toggleMovieLike(ContentsLikeDTO contentsLikeDTO) {
		//좋아요 여부
		boolean movieliked = contentsLikeDAO.movieLikeCheck(contentsLikeDTO);
		
		if(movieliked) {
			//이미 좋아요 -> 좋아요 취소
			contentsLikeDAO.movieDeleteLike(contentsLikeDTO);
			return false;
		}else {
			//좋아요 안눌려져 있으면 추가
			contentsLikeDAO.movieAddLike(contentsLikeDTO);
			return  true;
		}
	}
		
		
		

	
	
	
	
}
