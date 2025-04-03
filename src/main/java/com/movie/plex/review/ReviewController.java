package com.movie.plex.review;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.plex.users.UserDTO;

@Controller
@RequestMapping(value = "/reviewNest/*")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "addReview", method = RequestMethod.GET)
	public void addReview() throws Exception {

	}

	@RequestMapping(value = "addReview", method = RequestMethod.POST)
	public String addReview(ReviewDTO reviewDTO, HttpSession session,HttpServletResponse response) throws Exception {
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		if (userDTO == null) {
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('로그인이 필요합니다.'); location.href='/users/login';</script>");
	        out.flush();
	        return null;
	    }
		//System.out.println(reviewDTO.getReviewContents());
		//System.out.println(reviewDTO.getKind());
		reviewDTO.setUserNum(userDTO.getUserNum());
		int result = reviewService.addReview(reviewDTO);

		if (reviewDTO.getKind() == 0) {
	        return "redirect:./getMovieDetail?contentId=" + reviewDTO.getContentId();
	    } else {
	        return "redirect:./getTvDetail?contentId=" + reviewDTO.getContentId();
	    }
	}



	@RequestMapping(value = "getReviewList", method = RequestMethod.GET)
	public String getReviewList(ReviewDTO reviewDTO, @RequestParam("contentId") Long contentId, Model model) throws Exception {
		
		List<ReviewDTO> ar = reviewService.getReviewList(contentId);
		
		
		model.addAttribute("reviewList", ar);
		
		if (reviewDTO.getKind() == 0) {
	        return "redirect:./getMovieDetail?contentId=" + reviewDTO.getContentId();
	    } else {
	        return "redirect:./getTvDetail?contentId=" + reviewDTO.getContentId();
	    }
	}
	
	@RequestMapping(value= "/reviewNest/getReviewDetail", method=RequestMethod.GET)
	public String getReviewDetail(@RequestParam("reviewId") Long reviewId, Model model) throws Exception {
		
		ReviewDTO reviewDetail = reviewService.getReviewDetail(reviewId);
		

	        model.addAttribute("reviewDetail", reviewDetail);
	        
	    return "reviewNest/getReviewDetail";
	}

	
	
	@RequestMapping(value="updateReview", method= RequestMethod.GET)
	public void updateReview(ReviewDTO reviewDTO, Model model) throws Exception {
			//reviewDTO = reviewService.updateMovieReview(reviewDTO);
			model.addAttribute("reviewDTO", reviewDTO);
	}
	
	@RequestMapping(value="updateReview", method=RequestMethod.POST)
	public String updateReview(ReviewDTO reviewDTO) throws Exception{
		int result = reviewService.updateReview(reviewDTO);
		
		return "redirect:./getReviewDetail?reviewId=" +reviewDTO.getReviewId();
	}
	
	

	
	
	@RequestMapping(value="deleteReview", method=RequestMethod.GET)
	public String deleteReview(ReviewDTO reviewDTO, Model model) throws Exception{
		int result = reviewService.deleteReview(reviewDTO);
		String s = "삭제 실패";
		if(result>0) {
				s = "삭제 성공";
		}
		model.addAttribute("result" ,s);
		model.addAttribute("path", "./getReviewList");
		
		return "commons/result";
	}
	
	
}
