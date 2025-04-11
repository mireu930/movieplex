package com.movie.plex.users;

import java.sql.Date;

import com.movie.plex.coupon.CouponDTO;
import com.movie.plex.couponConnect.CouponConnectDTO;

public class UserDTO {
	private Long userNum;
	private String userId;
	private String userPw;
	private String userEmail;
	private String userPhone;
	private String userName;
	private Long userGrade;
	private Date registDate;
	private Long userOut;
	private Long sns;
	
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(Long userGrade) {
		this.userGrade = userGrade;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	public Long getUserOut() {
		return userOut;
	}
	public void setUserOut(Long userOut) {
		this.userOut = userOut;
	}
	public Long getSns() {
		return sns;
	}
	public void setSns(Long sns) {
		this.sns = sns;
	}
	
	
}
