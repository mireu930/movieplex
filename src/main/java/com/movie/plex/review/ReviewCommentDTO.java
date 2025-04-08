package com.movie.plex.review;

import java.sql.Date;

public class ReviewCommentDTO {
	
		private Long commentId;
		private String commentContents;
		private Date commentDate;
		private Long reviewId;
		private Long userNum;
		private String userName;
		
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public Long getCommentId() {
			return commentId;
		}
		public void setCommentId(Long commentId) {
			this.commentId = commentId;
		}
		public String getCommentContents() {
			return commentContents;
		}
		public void setCommentContents(String commentContents) {
			this.commentContents = commentContents;
		}
		public Date getCommentDate() {
			return commentDate;
		}
		public void setCommentDate(Date commentDate) {
			this.commentDate = commentDate;
		}
		public Long getReviewId() {
			return reviewId;
		}
		public void setReviewId(Long reviewId) {
			this.reviewId = reviewId;
		}
		public Long getUserNum() {
			return userNum;
		}
		public void setUserNum(Long userNum) {
			this.userNum = userNum;
		} 
		
		
		
}
