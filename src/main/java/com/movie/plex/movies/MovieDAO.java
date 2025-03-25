package com.movie.plex.movies;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.movie.plex.movies.MovieDAO.";
	
	public int addJsonList(List<MovieDTO> dtos) throws Exception{
		return sqlSession.insert(NAMESPACE + "addJsonList", dtos);
	}
	
	public List<MovieDTO> getList() throws Exception{
		return sqlSession.selectList(NAMESPACE + "getMainList");
	}
	
	public MovieDTO getMovieTitle(MovieDTO movieDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE + "getMovieTitle", movieDTO);
	}
}
