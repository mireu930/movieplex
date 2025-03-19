package com.movie.plex.coupon;

public class CouponDTO {
	private Long couponNum;
	private String couponName;
	private Long couponCost;
	private Long userNum;
	
	public Long getCouponNum() {
		return couponNum;
	}
	public void setCouponNum(Long couponNum) {
		this.couponNum = couponNum;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Long getCouponCost() {
		return couponCost;
	}
	public void setCouponCost(Long couponCost) {
		this.couponCost = couponCost;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	
	
}
