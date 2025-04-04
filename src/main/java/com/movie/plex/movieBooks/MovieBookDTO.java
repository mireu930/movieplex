package com.movie.plex.movieBooks;

import java.sql.Date;

import com.movie.plex.theater.TheaterDTO;
import com.movie.plex.users.UserDTO;

public class MovieBookDTO {
	private Long bookId;
	private Date bookDate;
	private Long userNum;
	private Long theaterId;
	private UserDTO userDTO;
	private TheaterDTO theaterDTO;
	
	public TheaterDTO getTheaterDTO() {
		return theaterDTO;
	}
	public void setTheaterDTO(TheaterDTO theaterDTO) {
		this.theaterDTO = theaterDTO;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public Long getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}
}