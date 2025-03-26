package com.movie.plex.boards.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("kind")
	public String getKind() {
		return "notice";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void getList() throws Exception {
		
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public void getDetail() throws Exception {
		
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public void add() throws Exception {
		
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add2() throws Exception {
		
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update() throws Exception {
		
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update2() throws Exception {
		
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public void delete() throws Exception {
		
	}
	
	@RequestMapping(value = "fileDelete", method = RequestMethod.POST)
	public void fileDelete() throws Exception {
		
	}
	
	@RequestMapping(value = "fileDown", method = RequestMethod.GET)
	public void fileDown() throws Exception {
		
	}
}
