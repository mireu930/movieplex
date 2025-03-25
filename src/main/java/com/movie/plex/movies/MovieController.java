package com.movie.plex.movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="/getList", method=RequestMethod.GET)
	public void getList(Model model) throws Exception{
		List<MovieDTO> dtos = movieService.getList();
		
		model.addAttribute("topMovieList", dtos);
	}
}
