package com.movie.plex.nestcontents;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.plex.pages.Pager;

@Repository
public class NestContentDAO {
	
	@Autowired
	private SqlSession sqlSession;
	 
	private final String NAMESPACE = "com.movie.plex.nestcontents.NestContentDAO.";
	
	
	public int addJsonList(List<NestContentDTO> nestContentDTOs ) throws Exception {
		return sqlSession.insert(NAMESPACE+"addJsonList", nestContentDTOs);
	}

	public int addJsonTVList(List<NestContentDTO> nestContentDTOs ) throws Exception {
		return sqlSession.insert(NAMESPACE+"addJsonTVList", nestContentDTOs);
	}
	
	public Long getTotalCount(Pager pager) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTotalCount", pager);
	}
	
	public List<NestContentDTO> getMovieList(Pager pager) throws Exception{
		return sqlSession.selectList(NAMESPACE+"getMovieList", pager);
	}
	
	public Long getTvTotalCount(Pager pager) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTvTotalCount", pager);
	}
	
	public List<NestContentDTO> getTvList(Pager pager) throws Exception{
		return sqlSession.selectList(NAMESPACE+"getTvList",pager);
	}
	
	public NestContentDTO getMovieDetail(Long contentId) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getMovieDetail", contentId);
	}
	
	public NestContentDTO getTvDetail(Long contentId) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTvDetail", contentId);
	}
	
	
	
	
	
	
}
