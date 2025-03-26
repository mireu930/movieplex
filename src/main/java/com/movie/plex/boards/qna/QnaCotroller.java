package com.movie.plex.boards.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QnaCotroller {
	
	@Autowired
	private QnaService qnaService;
}
