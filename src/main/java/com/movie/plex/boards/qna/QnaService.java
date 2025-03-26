package com.movie.plex.boards.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnaService {
	@Autowired
	private QnaDAO qnaDAO;
}
