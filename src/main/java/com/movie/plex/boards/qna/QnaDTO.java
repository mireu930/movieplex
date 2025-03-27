package com.movie.plex.boards.qna;

import com.movie.plex.boards.BoardDTO;
import com.movie.plex.users.UserDTO;

public class QnaDTO extends BoardDTO {
	
	private Long qnaNum;
	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
	private UserDTO userDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
	public Long getqnaNum() {
		return qnaNum;
	}
	public void setqnaNum(Long qnaNum) {
		this.qnaNum = qnaNum;
	}
	public Long getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(Long boardRef) {
		this.boardRef = boardRef;
	}
	public Long getBoardStep() {
		return boardStep;
	}
	public void setBoardStep(Long boardStep) {
		this.boardStep = boardStep;
	}
	public Long getBoardDepth() {
		return boardDepth;
	}
	public void setBoardDepth(Long boardDepth) {
		this.boardDepth = boardDepth;
	}
	
	
}
