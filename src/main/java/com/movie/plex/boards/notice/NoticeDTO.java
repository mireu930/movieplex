package com.movie.plex.boards.notice;

import java.util.List;

import com.movie.plex.boards.BoardDTO;
import com.movie.plex.boards.qna.QnaFilesDTO;
import com.movie.plex.users.UserDTO;

public class NoticeDTO extends BoardDTO {
	
	private Long noticeNum;
	private UserDTO userDTO;
	private List<NoticeFilesDTO> noticeFilesDTOs;

	public List<NoticeFilesDTO> getNoticeFilesDTOs() {
		return noticeFilesDTOs;
	}

	public void setNoticeFilesDTOs(List<NoticeFilesDTO> noticeFilesDTOs) {
		this.noticeFilesDTOs = noticeFilesDTOs;
	}

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
