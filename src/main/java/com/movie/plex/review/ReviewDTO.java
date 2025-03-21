package com.movie.plex.review;

import java.sql.Date;

public class ReviewDTO {

	
			private Long reviewId;
			private String reviewContents;
			private Date reviewDate;
			private Long reviewRate;
			private Long userNum;
			private Long contentId;
			
			
			public Long getReviewId() {
				return reviewId;
			}
			public void setReviewId(Long reviewId) {
				this.reviewId = reviewId;
			}
			public String getReviewContents() {
				return reviewContents;
			}
			public void setReviewContents(String reviewContents) {
				this.reviewContents = reviewContents;
			}
			public Date getReviewDate() {
				return reviewDate;
			}
			public void setReviewDate(Date reviewDate) {
				this.reviewDate = reviewDate;
			}
			public Long getReviewRate() {
				return reviewRate;
			}
			public void setReviewRate(Long reviewRate) {
				this.reviewRate = reviewRate;
			}
			public Long getUserNum() {
				return userNum;
			}
			public void setUserNum(Long userNum) {
				this.userNum = userNum;
			}
			public Long getContentId() {
				return contentId;
			}
			public void setContentId(Long contentId) {
				this.contentId = contentId;
			}
			
			
}
