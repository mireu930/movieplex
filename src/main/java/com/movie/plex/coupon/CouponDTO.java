package com.movie.plex.coupon;


public class CouponDTO {
	private Long couponNum;
	private String couponName;
	private Long couponCost;
	private String couponCode;
	private int used;
	private int count;

	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
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
	
}
