package com.movie.plex.theater;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminTheaterTestController {
	
	@RequestMapping(value = "/adminTheater", method=RequestMethod.GET)
	public String adminTheater() throws Exception{
		return "/admin/adminTheater";
	}
}
