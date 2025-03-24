package com.movie.plex.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@RequestMapping(value = "/mainPage", method=RequestMethod.GET)
	public String adminTheater() throws Exception{
		return "/admin/adminMainPage";
	}
	
	@RequestMapping(value="/getTheaterPage", method=RequestMethod.GET)
	public String getTheaterPage() throws Exception{
		
		return "admin/adminTheater";
	}
	
	
}
