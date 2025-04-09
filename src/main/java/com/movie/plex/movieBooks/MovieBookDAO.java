package com.movie.plex.movieBooks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.plex.theater.SeatDTO;
import com.movie.plex.users.UserDTO;

@Repository
public class MovieBookDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.movie.plex.movieBooks.MovieBookDAO.";
	
	public List<String> getSeats(Long theaterId) throws Exception{
		return sqlSession.selectList(NAMESPACE + "getSeats", theaterId);
	}

	public int addSeat(Map<String, Object> map) throws Exception{
		return sqlSession.insert(NAMESPACE + "addSeat", map);
	}

	public int addMovieBook(MovieBookDTO movieBookDTO) throws Exception{
		return sqlSession.insert(NAMESPACE + "addMovieBook", movieBookDTO);
	}
	
	public int addMovieBookBankBook(MovieBookDTO movieBookDTO) throws Exception{
		return sqlSession.insert(NAMESPACE + "addMovieBookBankBook", movieBookDTO);
	}

	public Long getBookId(MovieBookDTO movieBookDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE + "getBookId", movieBookDTO);
	}
	
	public Long getBookIdBankBook(MovieBookDTO movieBookDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE +"getBookIdBankBook", movieBookDTO);
	}

	public int addPayment(MoviePayments dto) throws Exception{
		return sqlSession.insert(NAMESPACE + "addPayment", dto);
	}
	public int addPaymentBankBook(MoviePayments dto) throws Exception{
		return sqlSession.insert(NAMESPACE + "addPaymentBankBook", dto);
	}

	public BigDecimal getAmounts(Long bookId) throws Exception{
		return sqlSession.selectOne(NAMESPACE + "getAmounts", bookId);
	}

	public Long getTheaterId(Long bookId) throws Exception{
		return sqlSession.selectOne(NAMESPACE + "getTheaterId", bookId);
	}

	public int deleteSeat(SeatDTO seatDTO) {
		return sqlSession.delete(NAMESPACE +"deleteSeat", seatDTO);
	}

	public int deletePay(Long bookId) {
		return sqlSession.delete(NAMESPACE +"deletePay", bookId);
	}

}
