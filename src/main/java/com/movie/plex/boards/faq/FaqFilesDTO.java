package com.movie.plex.boards.faq;

import com.movie.plex.boards.BoardFileDTO;

public class FaqFilesDTO extends BoardFileDTO {
	
	private Long faqNum;

	public Long getFaqNum() {
		return faqNum;
	}

	public void setFaqNum(Long faqNum) {
		this.faqNum = faqNum;
	}
}
