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
	
//	@RequestMapping(value="/getMainList", method=RequestMethod.GET)
//	public void getMainList(Model model) throws Exception{
//		List<MovieDTO> dtos = movieService.getMainList();
//		
//		model.addAttribute("topMovieList", dtos);
//		
//		
//	}
}
