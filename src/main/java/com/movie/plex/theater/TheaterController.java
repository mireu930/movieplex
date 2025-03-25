package com.movie.plex.theater;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/theater/*")
public class TheaterController {
	
	@Autowired
	private TheaterService theaterService;
	
	@RequestMapping(value="getList", method=RequestMethod.GET)
	public String getList(TheaterDTO theaterDTO, Model model) throws Exception{
		//System.out.println(theaterDTO.getTheaterDate());
		//System.out.println(theaterDTO.getMovieId());
		List<TheaterDTO> dtos = theaterService.getList(theaterDTO);
		//System.out.println(dtos.size());
		
		model.addAttribute("list", dtos);
		
		return "/admin/adminTheaterDetail";
	}
	
	
}
