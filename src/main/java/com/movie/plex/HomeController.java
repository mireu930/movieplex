package com.movie.plex;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.plex.movies.MovieDTO;
import com.movie.plex.movies.MovieService;
import com.movie.plex.nestcontents.NestContentDTO;
import com.movie.plex.nestcontents.NestContentService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MovieService movieService;
	@Autowired
	private NestContentService nestContentService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) throws Exception{
		logger.info("Welcome home! The client locale is {}.");
		
		List<MovieDTO> dtos = movieService.getMainList();
		model.addAttribute("topMovieList", dtos);
		return "main";
	}
	
	
	  @RequestMapping(value = "/reviewNest", method = RequestMethod.GET) public
	  String reviewNestMain(Locale locale, Model model) {
	  logger.info("Welcome home! The client locale is {}.", locale);
	  
	  Date date = new Date(); DateFormat dateFormat =
	  DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	  
	  String formattedDate = dateFormat.format(date);
	  
	  model.addAttribute("serverTime", formattedDate );
	  
	  return "/reviewNest/main"; 
	 
	  }
}
