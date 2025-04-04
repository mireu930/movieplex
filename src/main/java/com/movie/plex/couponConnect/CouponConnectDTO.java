package com.movie.plex.couponConnect;

import com.movie.plex.coupon.CouponDTO;
import com.movie.plex.users.UserDTO;

public class CouponConnectDTO {
	private Long userNum;
	private Long couponNum;
	private String couponCode;
	private int used;
	
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	private UserDTO userDTO;
	private CouponDTO couponDTO;
	
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public CouponDTO getCouponDTO() {
		return couponDTO;
	}
	public void setCouponDTO(CouponDTO couponDTO) {
		this.couponDTO = couponDTO;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public Long getCouponNum() {
		return couponNum;
	}
	public void setCouponNum(Long couponNum) {
		this.couponNum = couponNum;
	}
}
