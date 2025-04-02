package com.movie.plex.movieBooks;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.plex.movies.MovieDTO;
import com.movie.plex.movies.MovieService;
import com.movie.plex.theater.TheaterDTO;

@Controller
@RequestMapping("/movieBooks/*")
public class MovieBookController {
	
	@Autowired
	private MovieService movieService;
	@Autowired
	private MovieBookService bookService;
	
	
	@RequestMapping(value="booking", method = RequestMethod.GET)
	public void movieBooks(Model model) throws Exception{
		List<MovieDTO> dtos = movieService.getList();
		
		model.addAttribute("list", dtos);
	}
	
	@RequestMapping(value="seatBook", method = RequestMethod.GET)
	public void seatBook(Long theaterId, Model model) throws Exception{
		TheaterDTO dto = bookService.getMovieInfo(theaterId);
		
		model.addAttribute("theaterDTO", dto);
		
	}
	
	@ResponseBody
	@RequestMapping(value="getSeats", method=RequestMethod.GET)
	public List<String> getSeats(Long theaterId,Model model) throws Exception{
		List<String> seats = bookService.getSeats(theaterId);
//		ObjectMapper objectMapper = new ObjectMapper();
//		return objectMapper.writeValueAsString(seats);
		//java에서 json으로 변환하는 방법
		//1. ObjectMapper를 사용한다. => String으로 반환 => JS에서 .text()로 받아야함
		//2. 그냥 직접 반환한다(@ResonseBody를 쓰면 스프링이 자동으로 json으로 변환해줌) 
		//         => Json으로 반환 => .json()으로 받아야함
		return seats;
	}
	
	@RequestMapping(value="paymentPage", method=RequestMethod.GET)
	public void paymentPage() throws Exception{
		System.out.println("paymentPage");
	}
}
