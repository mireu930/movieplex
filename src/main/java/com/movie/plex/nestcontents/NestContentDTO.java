package com.movie.plex.nestcontents;

import java.sql.Date;

public class NestContentDTO {
		
		private Long contentID;
		private String contentTitle;
		private String shortPoster;
		private String longPoster;
		private Date releaseDate;
		private String overView;
		private Long popularity;
		private Long kind;
		
		
		public Long getContentID() {
			return contentID;
		}
		public void setContentID(Long contentID) {
			this.contentID = contentID;
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
		public Long getPopularity() {
			return popularity;
		}
		public void setPopularity(Long popularity) {
			this.popularity = popularity;
		}
		public Long getKind() {
			return kind;
		}
		public void setKind(Long kind) {
			this.kind = kind;
		}
		
		
		
		
		
		
		
	
	
}
