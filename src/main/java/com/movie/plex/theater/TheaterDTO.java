package com.movie.plex.theater;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TheaterDTO {
	private Long theaterId;
	private Date theaterStart;
	private Date theaterEnd;
	private Date theaterDate;
	private String theaterName;
	private Long movieId;
	private Long kind;
	
	public Long getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}
	public Date getTheaterStart() {
		return theaterStart;
	}
	public void setTheaterStart(Date theaterStart) {
		this.theaterStart = theaterStart;
	}
	public Date getTheaterEnd() {
		return theaterEnd;
	}
	public void setTheaterEnd(Date theaterEnd) {
		this.theaterEnd = theaterEnd;
	}
	public Date getTheaterDate() {
		return theaterDate;
	}
	public void setTheaterDate(Date theaterDate) {
		this.theaterDate = theaterDate;
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
	
	
	//출력용 getter
	
	public String getPrintStart() {
		if(theaterStart != null) {
			return new SimpleDateFormat("HH:mm").format(theaterStart);
		}
		
		return "";
	}
	
	public String getPrintEnd() {
		if(theaterStart != null) {
			return new SimpleDateFormat("HH:mm").format(theaterEnd);
		}
		
		return "";
	}
}
