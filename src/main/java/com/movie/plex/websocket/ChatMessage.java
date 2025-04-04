package com.movie.plex.websocket;

import java.sql.Date;
import java.util.Objects;

public class ChatMessage {
	
	private int cmNo;
	private String message;
	private Date createDate;
	private int chatRoomNo;
	private Long userNum;
	
	private String userId;
	
    public ChatMessage() {}

    public ChatMessage(int cmNo, String message, Date createDate, int chatRoomNo, Long userNum, String userId) {
        this.cmNo = cmNo;
        this.message = message;
        this.createDate = createDate;
        this.chatRoomNo = chatRoomNo;
        this.userNum = userNum;
        this.userId = userId;
    }

	public int getCmNo() {
		return cmNo;
	}

	public void setCmNo(int cmNo) {
		this.cmNo = cmNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getChatRoomNo() {
		return chatRoomNo;
	}

	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}

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

    @Override
    public String toString() {
        return "ChatMessage{" +
                "cmNo=" + cmNo +
                ", message='" + message + '\'' +
                ", createDate='" + createDate + '\'' +
                ", chatRoomNo=" + chatRoomNo +
                ", userNum=" + userNum +
                ", userId='" + userId + '\'' +
                '}';
    }

    // equals() & hashCode() 오버라이드 (객체 비교 시 필요)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return cmNo == that.cmNo &&
                chatRoomNo == that.chatRoomNo &&
                userNum == that.userNum &&
                Objects.equals(message, that.message) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmNo, message, createDate, chatRoomNo, userNum, userId);
    }
}
