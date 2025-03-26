package com.movie.plex.boards.notice;

import com.movie.plex.boards.BoardFileDTO;

public class NoticeFilesDTO extends BoardFileDTO {
	
	private Long noticeNum;

	public Long getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(Long noticeNum) {
		this.noticeNum = noticeNum;
	}
}
