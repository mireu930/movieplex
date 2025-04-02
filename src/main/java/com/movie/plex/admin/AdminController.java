package com.movie.plex.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.movie.plex.coupon.CouponDTO;
import com.movie.plex.coupon.CouponService;
import com.movie.plex.movies.MovieDTO;
import com.movie.plex.movies.MovieService;
import com.movie.plex.pages.Pager;
import com.movie.plex.theater.TheaterDTO;
import com.movie.plex.users.UserDTO;
import com.movie.plex.users.UserService;

@Controller
@RequestMapping(value="/admin/*")
public class AdminController {
	
	@Autowired
	private MovieService movieService;
	@Autowired
	private UserService userService;
	@Autowired
	private CouponService couponService;

	@RequestMapping(value = "/mainPage", method=RequestMethod.GET)
	public String adminTheater() throws Exception{
		return "/admin/adminMainPage";
	}
	
	
	@RequestMapping(value="addTheaterForm", method=RequestMethod.GET)
	public String addTheaterForm(Model model) throws Exception{
		List<MovieDTO> dtos = movieService.getList();
		model.addAttribute("movieList", dtos);
		
		return "/admin/theater/adminAddTheaterForm";
	}
	
	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public String userList(Model model, Pager pager) throws Exception {
		
		List<UserDTO> list = userService.userList(pager);
		model.addAttribute("userList", list);
		model.addAttribute("pager", pager);
		return "/admin/user/list";
	}
	
	@RequestMapping(value = "userDetail", method = RequestMethod.GET)
	public String userDetail(HttpSession session, Model model, UserDTO userDTO) throws Exception {
		userDTO = userService.getDetail(userDTO.getUserId());
		model.addAttribute("userDetail", userDTO);
		return "/admin/user/detail";
	}
	
	@RequestMapping(value = "adminUpdate", method = RequestMethod.GET)
	public String adminUpdate(UserDTO userDTO, Model model) throws Exception {
		userDTO = userService.getDetail(userDTO.getUserId());
		int result = userService.adminUpdate(userDTO);
		model.addAttribute("result", result);
		return "commons/ajax";
	}
	
	@RequestMapping(value ="withdraw", method = RequestMethod.GET)
	public String withdraw(UserDTO userDTO, Model model) throws Exception {
		userDTO = userService.getDetail(userDTO.getUserId());
		int result = userService.withdraw(userDTO);
		model.addAttribute("result", result);
		return "commons/ajax";
	}
	
	@RequestMapping(value = "couponAdd", method = RequestMethod.POST)
	public String couponAdd(CouponDTO couponDTO,Model model) throws Exception {
		System.out.println("couponAdd");
		int result  = couponService.couponAdd(couponDTO);
		return "redirect:./mainPage";
	}
	
}
