package com.movie.plex.movies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/movies/*")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieJson movieJson;
	
	@RequestMapping(value="getRuntime", method=RequestMethod.GET)
	public String getRuntime(MovieDTO movieDTO, Model model) throws Exception{
		System.out.println("!!!");
		Long runTime = movieJson.getRuntime(movieDTO);
		
		model.addAttribute("result", runTime);
		
		return "/commons/ajax";
	}
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public void movieList(Model model) throws Exception{
		List<MovieDTO> list = movieService.getMainList();
		model.addAttribute("topMovieList", list);
	}
	
}