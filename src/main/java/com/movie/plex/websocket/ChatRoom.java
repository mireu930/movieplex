package com.movie.plex.websocket;


public class ChatRoom {
	
	    private int chatRoomNo;
	    private String title;
	    private String status;
	    private Long userNum;
	    private String userId;
	    private int cnt;

	    // Getter & Setter
	    public int getChatRoomNo() { return chatRoomNo; }
	    public void setChatRoomNo(int chatRoomNo) { this.chatRoomNo = chatRoomNo; }

	    public String getTitle() { return title; }
	    public void setTitle(String title) { this.title = title; }

	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }

	    public Long getUserNum() { return userNum; }
	    public void setUserNum(Long userNum) { this.userNum = userNum; }

	    public String getUserId() { return userId; }
	    public void setUserId(String userId) { this.userId = userId; }

	    public int getCnt() { return cnt; }
	    public void setCnt(int cnt) { this.cnt = cnt; }

	}
