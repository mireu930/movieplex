package com.movie.plex.nestcontents;

import java.sql.Date;

public class NestContentDTO {
		
		private Long movieId;
		private String movieTitle;
		private String moviePoster;
		private Date releaseDate;
		private String overView;
		private Double popularity;
		private Long kind;
		
		
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
		public Double getPopularity() {
			return popularity;
		}
		public void setPopularity(Double popularity) {
			this.popularity = popularity;
		}
		public Long getKind() {
			return kind;
		}
		public void setKind(Long kind) {
			this.kind = kind;
		}
		
		
		
		
		
	
	
}
