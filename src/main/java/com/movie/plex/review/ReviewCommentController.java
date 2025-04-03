package com.movie.plex.review;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.movie.plex.users.UserDTO;

@Controller
@RequestMapping(value = "/reviewNest/*")
public class ReviewCommentController {

	@Autowired
	private ReviewCommentService reviewCommentService;

	 @GetMapping("/getComments")
	    @ResponseBody
	    public List<ReviewCommentDTO> getComments(@RequestParam Long reviewId) throws Exception{
	        return reviewCommentService.getCommentsByReviewId(reviewId);
	    }

	



	
	
	
}
