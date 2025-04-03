package com.movie.plex.movieBooks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.movies.MovieDAO;
import com.movie.plex.movies.MovieDTO;
import com.movie.plex.theater.TheaterDAO;
import com.movie.plex.theater.TheaterDTO;
import com.movie.plex.users.UserDTO;

@Service
public class MovieBookService {
	
	@Autowired
	private TheaterDAO theaterDAO;
	@Autowired
	private MovieBookDAO movieBookDAO;

	public TheaterDTO getMovieInfo(Long theaterId) throws Exception{
		return theaterDAO.getMovieInfo(theaterId);
	}
	
	public List<String> getSeats(Long theaterId) throws Exception{
		return movieBookDAO.getSeats(theaterId);
	}

	public Long movieBookInfo(List<String> seat, Long theaterId, UserDTO userDTO, Long totalPrice) {
		MovieBookDTO movieBookDTO = new MovieBookDTO();
		movieBookDTO.setTheaterId(theaterId);
		movieBookDTO.setUserNum(userDTO.getUserNum());
		Long bookId = 0L;
		int result = movieBookDAO.addMovieBook(movieBookDTO);
		if(result > 0) {
			bookId = movieBookDAO.getBookId(movieBookDTO);
			System.out.println("완료:" + bookId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("seat", seat);
			map.put("theaterId", theaterId);
			map.put("bookId", bookId);
			
			result = movieBookDAO.addSeat(map);
			System.out.println("좌석 추가 완료:" + result);
			if(result > 0) {
				MoviePayments dto = new MoviePayments();
				dto.setBookId(bookId);
				dto.setPayAmounts(totalPrice);
				result = movieBookDAO.addPayment(dto);
			}
		}
		return bookId;
	}
	
}
