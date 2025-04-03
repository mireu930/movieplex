package com.movie.plex.movieBooks;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.plex.users.UserDTO;

@Repository
public class MovieBookDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.movie.plex.movieBooks.MovieBookDAO.";
	
	public List<String> getSeats(Long theaterId) throws Exception{
		return sqlSession.selectList(NAMESPACE + "getSeats", theaterId);
	}

	public int addSeat(Map<String, Object> map) {
		return sqlSession.insert(NAMESPACE + "addSeat", map);
	}

	public int addMovieBook(MovieBookDTO movieBookDTO) {
		return sqlSession.insert(NAMESPACE + "addMovieBook", movieBookDTO);
	}

	public Long getBookId(MovieBookDTO movieBookDTO) {
		return sqlSession.selectOne(NAMESPACE + "getBookId", movieBookDTO);
	}

	public int addPayment(MoviePayments dto) {
		return sqlSession.selectOne(NAMESPACE + "addPayment", dto);
		
	}

}
