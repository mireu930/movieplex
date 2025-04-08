package com.movie.plex.review;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "addComment", method = RequestMethod.GET)
	public void addComment() throws Exception {

	}

	@RequestMapping(value = "addComment", method = RequestMethod.POST)
	public String addComment(ReviewCommentDTO reviewCommentDTO, HttpSession session,HttpServletResponse response) throws Exception {
		//System.out.println("넘어온 reviewId: " + reviewCommentDTO.getReviewId());
		//System.out.println("댓글 내용: " + reviewCommentDTO.getCommentContents());
		
	    if (reviewCommentDTO.getReviewId() == null) {
	        return "error";  // reviewId가 없으면 에러 반환
	    }
		
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
		
		reviewCommentDTO.setUserNum(userDTO.getUserNum());
		int result = reviewCommentService.addComment(reviewCommentDTO);
		
		
		return "redirect:./getReviewDetail?reviewId=" + reviewCommentDTO.getReviewId();
	}

	



	
	
	
}
