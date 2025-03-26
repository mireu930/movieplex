package com.movie.plex.boards.notice;

import com.movie.plex.boards.BoardDTO;

public class NoticeDTO extends BoardDTO {
	
	private Long noticeNum;

	public Long getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(Long noticeNum) {
		this.noticeNum = noticeNum;
	}
}
