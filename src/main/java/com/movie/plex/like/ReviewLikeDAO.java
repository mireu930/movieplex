package com.movie.plex.like;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewLikeDAO {
	
		@Autowired
		private SqlSession sqlSession;
		
		private final String NAMESPACE = "com.movie.plex.like.ReviewLikeDAO.";
		
		public int isLiked(ReviewLikeDTO reviewLikeDTO) throws Exception{
			return sqlSession.selectOne(NAMESPACE+"isLiked", reviewLikeDTO);
		}
		
		public int addLike(ReviewLikeDTO reviewLikeDTO) throws Exception{
			return sqlSession.insert(NAMESPACE+"addLike", reviewLikeDTO);
		}
		
		public int deleteLike(ReviewLikeDTO reviewLikeDTO) throws Exception{
			return sqlSession.delete(NAMESPACE+"deleteLike", reviewLikeDTO);
		}
		
		public List<Long> getLikedReviewIds(Long userNum, Long kind) {
		    Map<String, Object> paramMap = new HashMap<String, Object>();
		    paramMap.put("userNum", userNum);
		    paramMap.put("kind", kind);
		    return sqlSession.selectList(NAMESPACE + "getLikedReviewIds", paramMap);
		}


}
