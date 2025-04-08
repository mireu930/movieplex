package com.movie.plex.like;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContentsLikeDAO {
	
		@Autowired
		private SqlSession sqlSession;
		
		private final String NAMESPACE = "com.movie.plex.like.ContentsLikeDAO.";
		
		//좋아요 여부확인
		public int isLiked(ContentsLikeDTO contentsLikeDTO) {
		    return sqlSession.selectOne(NAMESPACE + "isLiked", contentsLikeDTO);
		}
		 
		//좋아요 추가
		public int addLike(ContentsLikeDTO contentsLikeDTO) {
			return sqlSession.insert(NAMESPACE+"addLike", contentsLikeDTO);
		}
		
		//좋아요 삭제
		public int deleteLike(ContentsLikeDTO contentsLikeDTO) {
			return sqlSession.delete(NAMESPACE+"deleteLike", contentsLikeDTO);
		}
		
		
		public List<Long> getLikedContentsIds(Long userNum, Long kind){
			Map<String, Object> paramMap = new HashMap<String, Object>();
		    paramMap.put("userNum", userNum);
		    paramMap.put("kind", kind);
			return sqlSession.selectList(NAMESPACE+"getLikedContentsIds", paramMap);
		}
		

		
		
}
