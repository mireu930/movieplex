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
		List<TheaterDTO> dtos = theaterService.getDayList(theaterDTO);
		System.out.println(dtos.size());

		model.addAttribute("theaterList", dtos);

		return "/admin/theater/adminTheaterList";
	}
	
	@RequestMapping(value="addTheater", method=RequestMethod.GET)
	public String addTheater(TheaterDTO theaterDTO, Model model) throws Exception{
		int result = theaterService.addTheater(theaterDTO);
		model.addAttribute("result", result);
		System.out.println("addTheater 완료");
		return "/admin/theater/ajaxResult";
	}
	
	@RequestMapping(value="deleteTheater", method=RequestMethod.GET)
	public String deleteTheater(TheaterDTO theaterDTO, Model model) throws Exception{
		int result = theaterService.deleteTheater(theaterDTO);
		
		model.addAttribute("result", result);
		
		return "/admin/theater/ajaxResult";
	}
}
