package com.movie.plex.theater;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.movie.plex.movies.MovieDTO;

public class TheaterDTO {
	private Long theaterId;
	private Timestamp theaterStart;
	private Timestamp theaterEnd;
	private String theaterName;
	private Long movieId;
	private Long kind;
	private MovieDTO movieDTO;
	private SeatDTO seatDTO;
	
	public SeatDTO getSeatDTO() {
		return seatDTO;
	}
	public void setSeatDTO(SeatDTO seatDTO) {
		this.seatDTO = seatDTO;
	}
	public MovieDTO getMovieDTO() {
		return movieDTO;
	}
	public void setMovieDTO(MovieDTO movieDTO) {
		this.movieDTO = movieDTO;
	}
	public Long getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}
	
	
	public Timestamp getTheaterStart() {
		return theaterStart;
	}
	public void setTheaterStart(Timestamp theaterStart) {
		this.theaterStart = theaterStart;
	}
	public Timestamp getTheaterEnd() {
		return theaterEnd;
	}
	public void setTheaterEnd(Timestamp theaterEnd) {
		this.theaterEnd = theaterEnd;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Long getKind() {
		return kind;
	}
	public void setKind(Long kind) {
		this.kind = kind;
	}
	
	
	//異쒕젰�슜 getter
	
	public String getPrintStart() {
		if(theaterStart != null) {
			return new SimpleDateFormat("MM-dd HH:mm").format(theaterStart);
		}
		
		return "";
	}
	
	public String getPrintEnd() {
		if(theaterStart != null) {
			return new SimpleDateFormat("MM-dd HH:mm").format(theaterEnd);
		}
		
		return "";
	}
	
	public String getPrintDate() {
		if(theaterStart != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(theaterStart);
		}
		return "";
	}
	public String getTimeStart() {
		if(theaterStart != null) {
			return new SimpleDateFormat("HH:mm").format(theaterStart);
		}
		return "";
	}
	
	public String getTimeEnd() {
		if(theaterEnd != null) {
			return new SimpleDateFormat("HH:mm").format(theaterEnd);
		}
		return "";
	}
	
}
