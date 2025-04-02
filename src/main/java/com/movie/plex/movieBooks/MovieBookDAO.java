package com.movie.plex.movieBooks;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieBookDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.movie.plex.movieBooks.MovieBookDAO.";
	
	public List<String> getSeats(Long theaterId) throws Exception{
		return sqlSession.selectList(NAMESPACE + "getSeats", theaterId);
	}
}
