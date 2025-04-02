package com.movie.plex.theater;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TheaterDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.movie.plex.theater.TheaterDAO.";
	
	public int addTheater(TheaterDTO theaterDTO) throws Exception{
		return sqlSession.insert(NAMESPACE + "addTheater", theaterDTO); 
	}

	public List<TheaterDTO> getList(TheaterDTO theaterDTO) throws Exception{
		return sqlSession.selectList(NAMESPACE + "getList", theaterDTO);
	}
	public List<TheaterDTO> getDayList(TheaterDTO theaterDTO)throws Exception{
		return sqlSession.selectList(NAMESPACE + "getDayList", theaterDTO);
	}
	
	public int checkTime(TheaterDTO theaterDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE + "checkTime", theaterDTO);
	}

	public int deleteCheck(TheaterDTO theaterDTO) {
		return sqlSession.selectOne(NAMESPACE + "deleteCheck", theaterDTO);
		
	}

	public int deleteTheater(TheaterDTO theaterDTO) {
		return sqlSession.delete(NAMESPACE + "deleteTheater", theaterDTO);
		
	}

	public List<TheaterDTO> getTheaterList(TheaterDTO theaterDTO) {
		return sqlSession.selectList(NAMESPACE + "getTheaterList", theaterDTO);
	}

	public TheaterDTO getMovieInfo(Long theaterId) {
		return sqlSession.selectOne(NAMESPACE + "getMovieInfo", theaterId);
	}
}
