package com.movie.plex.movieBooks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.plex.couponConnect.CouponConnectDTO;
import com.movie.plex.movies.MovieDTO;
import com.movie.plex.movies.MovieService;
import com.movie.plex.theater.TheaterDTO;
import com.movie.plex.users.UserDTO;
import com.movie.plex.users.UserService;

@Controller
@RequestMapping("/movieBooks/*")
public class MovieBookController {
	
	@Autowired
	private MovieService movieService;
	@Autowired
	private MovieBookService bookService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="booking", method = RequestMethod.GET)
	public void movieBooks(Model model) throws Exception{
		List<MovieDTO> dtos = movieService.getList();
		
		model.addAttribute("list", dtos);
	}
	

	@RequestMapping(value="seatBook", method = RequestMethod.POST)
	public String seatBook(Long theaterId, Model model, HttpSession session) throws Exception{
		TheaterDTO dto = bookService.getMovieInfo(theaterId);
		Object user = session.getAttribute("user");
		if(user == null) {
			System.out.println("유저 없음");
			model.addAttribute("result", 0);
			return "/commons/ajax";
		}
		//System.out.println(theaterId);
		model.addAttribute("theaterDTO", dto);
		
		return "/movieBooks/seatBook";
	}
	
	@ResponseBody
	@RequestMapping(value="getSeats", method=RequestMethod.POST)
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
	
	@RequestMapping(value="paymentPage", method=RequestMethod.POST)
	public void paymentPage(Long theaterId, Model model, HttpSession session) throws Exception{
		TheaterDTO dto = bookService.getMovieInfo(theaterId);
		UserDTO user = (UserDTO)session.getAttribute("user");
		List<CouponConnectDTO> coupons = userService.couponList(user);
		List<CouponConnectDTO> sendCoupons = new ArrayList<CouponConnectDTO>();
		for(CouponConnectDTO coupon : coupons) {
			if(coupon.getUsed() == 0) {
				sendCoupons.add(coupon);
			}
		}
		System.out.println("쿠폰" + coupons.size());
		model.addAttribute("theaterDTO", dto);
		model.addAttribute("coupons", sendCoupons);
		
		System.out.println("paymentPage");
	}
	@RequestMapping(value="bookSuccessPage", method=RequestMethod.POST)
	public void bookSuccessPage(Long bookId, Model model) throws Exception{
		Map<String, Object> map = bookService.bookSuccessPage(bookId);
		model.addAttribute("theaterDTO", map.get("theaterDTO"));
		model.addAttribute("seats", map.get("seats"));
		model.addAttribute("totalPrice", map.get("totalPrice"));
		
	}
}
