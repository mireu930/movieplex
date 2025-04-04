package com.movie.plex.websocket;

public class ChatRoomJoin {
	private Long userNum;
	private int chatRoomNo;
	
	public ChatRoomJoin() {}
	
	public ChatRoomJoin(Long userNum, int chatRoomNo) {
		this.userNum = userNum;
		this.chatRoomNo = chatRoomNo;
	}
	
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
	
	 @Override
	    public String toString() {
	        return "ChatRoomJoin{" +
	                "userNum=" + userNum +
	                ", chatRoomNo=" + chatRoomNo +
	                '}';
	    }

	    // equals() & hashCode() 오버라이드
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ChatRoomJoin that = (ChatRoomJoin) o;
	        return userNum == that.userNum && chatRoomNo == that.chatRoomNo;
	    }

	    @Override
	    public int hashCode() {
	        return java.util.Objects.hash(userNum, chatRoomNo);
	    }
}
