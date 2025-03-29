package com.movie.plex.boards;

import com.movie.plex.files.FileDTO;

public class BoardFileDTO extends FileDTO{

	private Long fileNum;
	
	public Long getFileNum() {
		return fileNum;
	}
	public void setFileNum(Long fileNum) {
		this.fileNum = fileNum;
	}	
	
}
