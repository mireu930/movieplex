package com.movie.plex.movies;

import java.sql.Date;

public class MovieDTO {
	private Long movieId;
	private String movieTitle;
	private String shortPoster;
	private String longPoster;
	private Date releaseDate;
	private String overView;
	private Double popularity;
	private Long ticketPrice;
	private Long movieCheck;
	
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getShortPoster() {
		return shortPoster;
	}
	public void setShortPoster(String shortPoster) {
		this.shortPoster = shortPoster;
	}
	public String getLongPoster() {
		return longPoster;
	}
	public void setLongPoster(String longPoster) {
		this.longPoster = longPoster;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getOverView() {
		return overView;
	}
	public void setOverView(String overView) {
		this.overView = overView;
	}
	public Double getPopulartity() {
		return popularity;
	}
	public void setPopulartity(Double populartity) {
		this.popularity = populartity;
	}
	public Long getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(Long ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public Long getMovieCheck() {
		return movieCheck;
	}
	public void setMovieCheck(Long movieCheck) {
		this.movieCheck = movieCheck;
	}
	
}
