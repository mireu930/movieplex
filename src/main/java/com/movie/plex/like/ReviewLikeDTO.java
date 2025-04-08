package com.movie.plex.like;

public class ReviewLikeDTO {

		private Long likeId;
		private Long userNum;
		private Long reviewId;
		private Long kind;
		
		
		public Long getLikeId() {
			return likeId;
		}
		public void setLikeId(Long likeId) {
			this.likeId = likeId;
		}
		public Long getKind() {
			return kind;
		}
		public void setKind(Long kind) {
			this.kind = kind;
		}
		public Long getUserNum() {
			return userNum;
		}
		public void setUserNum(Long userNum) {
			this.userNum = userNum;
		}
		public Long getReviewId() {
			return reviewId;
		}
		public void setReviewId(Long reviewId) {
			this.reviewId = reviewId;
		}
		
		
}
