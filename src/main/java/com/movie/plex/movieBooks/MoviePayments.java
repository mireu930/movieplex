package com.movie.plex.movieBooks;

public class MoviePayments {
	private Long payId;
	private Long payCheck;
	private Long payAmounts;
	private Long bookId;
	
	
	public Long getPayId() {
		return payId;
	}
	public void setPayId(Long payId) {
		this.payId = payId;
	}
	public Long getPayCheck() {
		return payCheck;
	}
	public void setPayCheck(Long payCheck) {
		this.payCheck = payCheck;
	}
	public Long getPayAmounts() {
		return payAmounts;
	}
	public void setPayAmounts(Long payAmounts) {
		this.payAmounts = payAmounts;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

}
