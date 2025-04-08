package com.movie.plex.review;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.movie.plex.users.UserDTO;

@Controller
@RequestMapping(value = "/reviewNest/*")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "addMovieReview", method = RequestMethod.GET)
	public void addMovieReview() throws Exception {

	}

	@RequestMapping(value = "addMovieReview", method = RequestMethod.POST)
	public String addMovieReview(ReviewDTO reviewDTO, HttpSession session) throws Exception {
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		reviewDTO.setUserNum(userDTO.getUserNum());
		int result = reviewService.addMovieReview(reviewDTO);

		return "redirect:./getMovieDetail";
	}

	  @RequestMapping(value="addTvReview", method=RequestMethod.GET) 
		public void addTvReview() throws Exception{
	 
	 	}
	 
	@RequestMapping(value = "addTvReview", method = RequestMethod.POST)
		public String addTvReview(ReviewDTO reviewDTO, HttpSession session) throws Exception {
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		reviewDTO.setUserNum(userDTO.getUserNum());
		int result = reviewService.addTvReivew(reviewDTO);

		return "redirect:./getTvDetail";
	}

	@RequestMapping(value = "getMovieReviewList", method = RequestMethod.GET)
	public void getMovieReviewList(Model model) throws Exception {
		System.out.println("MovieReviewList");
		List<ReviewDTO> moviear = reviewService.getMovieReviewList();

		model.addAttribute("movieReview", moviear);
	}

	@RequestMapping(value = "getTvReviewList", method = RequestMethod.GET)
	public void getTvReviewList(Model model) throws Exception {
		List<ReviewDTO> tvar = reviewService.getTvReviewList();

		model.addAttribute("getTvList", tvar);
	}
	
	
	@RequestMapping(value="updateMovieReview", method= RequestMethod.GET)
	public void updateMovieReview(ReviewDTO reviewDTO, Model model) throws Exception {
			//reviewDTO = reviewService.updateMovieReview(reviewDTO);
			model.addAttribute("reviewDTO", reviewDTO);
	}
	@RequestMapping(value="updateMovieReview", method=RequestMethod.POST)
	public String updateMovieReview(ReviewDTO reviewDTO) throws Exception{
		int result = reviewService.updateMovieReview(reviewDTO);
		
		return "redirect:./getMovieDetail?reviewId=" +reviewDTO.getReviewId();
	}
	
	
	@RequestMapping(value="updateTvReview", method=RequestMethod.GET)
	public void updateTvReview(ReviewDTO reviewDTO, Model model) throws Exception {
		//reviewDTO = reviewService.updateTvReview(reviewDTO);
		model.addAttribute("reviewDTO", reviewDTO);
	}
	@RequestMapping(value="updateTvReview", method=RequestMethod.POST)
	public String updateTvReview(ReviewDTO reviewDTO) throws Exception{
		int result = reviewService.updateTvReview(reviewDTO);
		
		return "redirect:./getTvDetail?reviewId=" +reviewDTO.getReviewId();
	}
	
	
	@RequestMapping(value="deleteMovieReview", method=RequestMethod.GET)
	public String deleteMovieReview(ReviewDTO reviewDTO, Model model) throws Exception{
		int result = reviewService.deleteMovieReview(reviewDTO);
		String s = "삭제 실패";
		if(result>0) {
				s = "삭제 성공";
		}
		model.addAttribute("result" ,s);
		model.addAttribute("path", "./getMovieReviewList");
		
		return "commons/result";
	}
	@RequestMapping(value="deleteTvReview", method=RequestMethod.GET)
	public String deleteTvReview(ReviewDTO reviewDTO, Model model) throws Exception{
		int result = reviewService.deleteMovieReview(reviewDTO);
		String s = "삭제 실패";
		if(result>0) {
				s = "삭제 성공";
		}
		model.addAttribute("result" ,s);
		model.addAttribute("path", "./getTvReviewList");
		
		return "commons/result";
	}
}
