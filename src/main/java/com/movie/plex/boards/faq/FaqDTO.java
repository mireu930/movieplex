package com.movie.plex.boards.faq;

import java.util.List;

import com.movie.plex.boards.BoardDTO;
import com.movie.plex.boards.qna.QnaFilesDTO;
import com.movie.plex.users.UserDTO;

public class FaqDTO extends BoardDTO {

	private Long FaqNum;
	private UserDTO userDTO;
	private List<FaqFilesDTO> faqFilesDTOs;

	public List<FaqFilesDTO> getFaqFilesDTOs() {
		return faqFilesDTOs;
	}

	public void setFaqFilesDTOs(List<FaqFilesDTO> faqFilesDTOs) {
		this.faqFilesDTOs = faqFilesDTOs;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public Long getFaqNum() {
		return FaqNum;
	}

	public void setFaqNum(Long faqNum) {
		FaqNum = faqNum;
	}
}
