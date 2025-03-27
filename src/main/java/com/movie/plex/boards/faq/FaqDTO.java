package com.movie.plex.boards.faq;

import com.movie.plex.boards.BoardDTO;
import com.movie.plex.users.UserDTO;

public class FaqDTO extends BoardDTO {

	private Long FaqNum;
	private UserDTO userDTO;

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
