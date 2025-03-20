package com.movie.plex.movieBooks;

import java.sql.Date;

public class MovieBookDTO {
	private Long bookId;
	private Date bookDate;
	private Long userName;
	private Long theaterId;
	
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
	public Long getUserName() {
		return userName;
	}
	public void setUserName(Long userName) {
		this.userName = userName;
	}
	public Long getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}
	
	
}
