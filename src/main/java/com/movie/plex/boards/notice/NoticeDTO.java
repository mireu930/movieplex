package com.movie.plex.boards.notice;

import com.movie.plex.boards.BoardDTO;
import com.movie.plex.users.UserDTO;

public class NoticeDTO extends BoardDTO {
	
	private Long noticeNum;
	private UserDTO userDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public Long getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(Long noticeNum) {
		this.noticeNum = noticeNum;
	}
}
