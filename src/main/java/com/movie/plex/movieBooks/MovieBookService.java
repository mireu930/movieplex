package com.movie.plex.movieBooks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.movies.MovieDAO;
import com.movie.plex.movies.MovieDTO;
import com.movie.plex.theater.SeatDTO;
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

	public Long movieBookCard(List<String> seat, Long theaterId, UserDTO userDTO, Long totalPrice, String merchant) throws Exception{
		//예매 내역 저장
		MovieBookDTO movieBookDTO = new MovieBookDTO();
		movieBookDTO.setTheaterId(theaterId);
		movieBookDTO.setUserNum(userDTO.getUserNum());
		Long bookId = 0L;
		int result = movieBookDAO.addMovieBook(movieBookDTO);
		if(result > 0) {
			//좌석 추가
			bookId = movieBookDAO.getBookId(movieBookDTO);
			System.out.println("�셿猷�:" + bookId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("seat", seat);
			map.put("theaterId", theaterId);
			map.put("bookId", bookId);
			
			result = movieBookDAO.addSeat(map);

	

			System.out.println("좌석 추가 완료:" + result);
			
			//결제 내역 저장

			if(result > 0) {
				MoviePayments dto = new MoviePayments();
				dto.setBookId(bookId);
				dto.setPayAmounts(totalPrice);
				dto.setPayId(Long.parseLong(merchant));
				System.out.println(totalPrice);
				result = movieBookDAO.addPayment(dto);
			}
		}
		return bookId;
	}

	public BigDecimal getAmounts(Long bookId) throws Exception{
		return movieBookDAO.getAmounts(bookId);
	}

	
	public int bookRefund(MovieBookDTO movieBookDTO) throws Exception {
		movieBookDAO.deleteSeat(movieBookDTO);
		return movieBookDAO.bookRefund(movieBookDTO);
	}


	public Map<String, Object> bookSuccessPage(Long bookId) throws Exception{
		Long theaterId = movieBookDAO.getTheaterId(bookId);
		TheaterDTO theaterDTO = theaterDAO.getMovieInfo(theaterId);
		List<String> seats = movieBookDAO.getSeats(theaterId);
		BigDecimal totalPrice = movieBookDAO.getAmounts(bookId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("theaterDTO", theaterDTO);
		map.put("seats", seats);
		map.put("totalPrice", totalPrice);
		
		return map;
	}

	public Long movieBookBankBook(List<String> seat, Long theaterId, Long totalPrice, UserDTO userDTO) throws Exception{
		MovieBookDTO movieBookDTO = new MovieBookDTO();
		movieBookDTO.setTheaterId(theaterId);
		movieBookDTO.setUserNum(userDTO.getUserNum());
		Long bookId = 0L;
		int result = movieBookDAO.addMovieBookBankBook(movieBookDTO);
		if(result > 0) {
			bookId = movieBookDAO.getBookIdBankBook(movieBookDTO);
			System.out.println("�셿猷�:" + bookId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("seat", seat);
			map.put("theaterId", theaterId);
			map.put("bookId", bookId);
			
			result = movieBookDAO.addSeat(map);
			System.out.println("醫뚯꽍 異붽� �셿猷�:" + result);
			if(result > 0) {
				MoviePayments dto = new MoviePayments();
				dto.setBookId(bookId);
				dto.setPayAmounts(totalPrice);
				System.out.println(totalPrice);
				result = movieBookDAO.addPaymentBankBook(dto);
			}
		}
		return bookId;
	}
	
}
