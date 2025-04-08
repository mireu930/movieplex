package com.movie.plex.like;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContentsLikeDAO {
	
		@Autowired
		private SqlSession sqlSession;
		
		private final String NAMESPACE = "com.movie.plex.like.ContentsLikeDAO.";
		
		//좋아요가 있는지 확인
		public boolean movieLikeCheck(ContentsLikeDTO contentsLikeDTO) {
			Long result = sqlSession.selectOne(NAMESPACE+"movieLikeCheck", contentsLikeDTO);
			return result != null && result >0;
		}
		
		//영화 좋아요 추가
		public void movieAddLike(ContentsLikeDTO contentsLikeDTO) {
			sqlSession.insert(NAMESPACE+"movieAddLike", contentsLikeDTO);
		}
		
		//영화 좋아요 삭제
		public void movieDeleteLike(ContentsLikeDTO contentsLikeDTO) {
			sqlSession.delete(NAMESPACE+"movieDeleteLike", contentsLikeDTO);
		}
		

		
		
}
