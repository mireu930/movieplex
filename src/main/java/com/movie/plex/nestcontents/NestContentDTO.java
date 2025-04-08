package com.movie.plex.nestcontents;

import java.sql.Date;

public class NestContentDTO {
		
		private Long contentId;
		private String contentTitle;
		private String shortPoster;
		private String longPoster;
		private Date releaseDate;
		private String overView;
		private Double popularity;
		private Long kind;
		
		
		public Long getContentId() {
			return contentId;
		}
		public void setContentId(Long contentId) {
			this.contentId = contentId;
		}
		public String getContentTitle() {
			return contentTitle;
		}
		public void setContentTitle(String contentTitle) {
			this.contentTitle = contentTitle;
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
