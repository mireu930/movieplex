package com.movie.plex.websocket;

public class ChatRoomJoin {
	private Long userNum;
	private int chatRoomNo;
	
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public int getChatRoomNo() {
		return chatRoomNo;
	}
	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}
}
