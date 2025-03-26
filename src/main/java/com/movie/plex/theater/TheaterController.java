package com.movie.plex.theater;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/theater/*")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	@RequestMapping(value = "getDayList", method = RequestMethod.GET)
	public String getList(TheaterDTO theaterDTO, Model model) throws Exception {
		System.out.println(theaterDTO.getTheaterDate());
		List<TheaterDTO> dtos = theaterService.getDayList(theaterDTO);
		System.out.println(dtos.size());

		model.addAttribute("theaterList", dtos);

		return "/admin/theater/adminTheaterList";
	}
	
	@RequestMapping(value="addTheater", method=RequestMethod.GET)
	public void addTheater(TheaterDTO theaterDTO) throws Exception{
		System.out.println("성공");
		System.out.println(theaterDTO.getMovieId());
		System.out.println(theaterDTO.getTheaterName());
		//System.out.println(theaterDTO.getTheaterStart());
		System.out.println(theaterDTO.getTheaterDate());
	}

}
