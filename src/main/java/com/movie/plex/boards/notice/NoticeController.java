package com.movie.plex.boards.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
}
