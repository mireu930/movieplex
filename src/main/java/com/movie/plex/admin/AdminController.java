package com.movie.plex.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.plex.movies.MovieDTO;
import com.movie.plex.movies.MovieService;
import com.movie.plex.theater.TheaterDTO;

@Controller
@RequestMapping(value="/admin/*")
public class AdminController {
	
	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "/mainPage", method=RequestMethod.GET)
	public String adminTheater() throws Exception{
		return "/admin/adminMainPage";
	}
	
	
	@RequestMapping(value="addTheaterForm", method=RequestMethod.GET)
	public String addTheaterForm(Model model) throws Exception{
		List<MovieDTO> dtos = movieService.getList();
		model.addAttribute("movieList", dtos);
		
		return "/admin/theater/adminAddTheaterForm";
	}

	
}
