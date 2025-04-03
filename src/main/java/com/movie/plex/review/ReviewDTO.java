package com.movie.plex.review;

import java.sql.Date;

public class ReviewDTO {

	
			private Long reviewId;
			private Date reviewDate;
			private Date updateDate;
			private Long reviewRate;
			private Long userNum;
			private Long contentId;
			private Long kind;
			private String userName;
			private String reviewContents;
			
			
			public String getUserName() {
				return userName;
			}
			public void setUserName(String userName) {
				this.userName = userName;
			}
			public Long getKind() {
				return kind;
			}
			public void setKind(Long kind) {
				this.kind = kind;
			}
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
			public Date getUpdateDate() {
				return updateDate;
			}
			public void setUpdateDate(Date updateDate) {
				this.updateDate = updateDate;
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
