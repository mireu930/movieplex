package com.movie.plex.websocket;

public class Message {
	private String code;
	private String sender;
	private String receiver;
	private String content;
	private String regdate;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "Message [code=" + code + ", sender=" + sender + ", receiver=" + receiver + ", content=" + content
				+ ", regdate=" + regdate + "]";
	}

}
