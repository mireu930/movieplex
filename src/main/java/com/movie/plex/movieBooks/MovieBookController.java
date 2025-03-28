package com.movie.plex.movieBooks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.plex.movies.MovieDTO;
import com.movie.plex.movies.MovieService;

@Controller
@RequestMapping("/movieBooks/*")
public class MovieBookController {
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="booking", method = RequestMethod.GET)
	public void movieBooks(Model model) throws Exception{
		List<MovieDTO> dtos = movieService.getList();
		
		model.addAttribute("list", dtos);
	}
}
