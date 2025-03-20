package com.movie.plex.movies;

import java.sql.Date;

public class MovieDTO {
	private Long movieId;
	private String movieTitle;
	private String moviePoster;
	private Date releaseDate;
	private String overView;
	private Double populartity;
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
	public String getMoviePoster() {
		return moviePoster;
	}
	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
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
		return populartity;
	}
	public void setPopulartity(Double populartity) {
		this.populartity = populartity;
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
