package com.movie.plex.review;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movie.plex.like.ContentsLikeService;
import com.movie.plex.like.ReviewLikeService;
import com.movie.plex.users.UserDTO;

@Controller
@RequestMapping(value = "/reviewNest/*")
public class ReviewCommentController {

	@Autowired
	private ReviewCommentService reviewCommentService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ReviewLikeService reviewLikeService;
	@Autowired
	private ContentsLikeService contentsLikeService;
	
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
	
	@RequestMapping(value="updateComment", method= RequestMethod.GET)
	public void updateComment(ReviewCommentDTO reviewCommentDTO, Model model) throws Exception {
			
			model.addAttribute("reviewCommentDTO", reviewCommentDTO);
	}
	
	@RequestMapping(value="updateComment", method=RequestMethod.POST)
	public String updateComment(ReviewCommentDTO commentDTO, HttpSession session, Model model) throws Exception {
	    reviewCommentService.updateComment(commentDTO);
	    
	    UserDTO user = (UserDTO) session.getAttribute("user");
	    if (user == null) return "redirect:/login";
	    
	    model.addAttribute("myReviews", reviewService.getMyReviews(user.getUserNum()));
	    model.addAttribute("myComments", reviewCommentService.getMyComments(user.getUserNum()));
	    model.addAttribute("likedContents", contentsLikeService.getMyLikedContents(user.getUserNum()));
	    model.addAttribute("likedReviews", reviewLikeService.getMyLikedReviews(user.getUserNum()));
	   
	    return "/reviewNest/nestMypage";
	}

	
	@RequestMapping(value="deleteComment", method=RequestMethod.POST)
	public String deleteComment(@RequestParam("commentId") Long commentId, HttpSession session, RedirectAttributes redirectAttributes) throws Exception{
		reviewCommentService.deleteComment(commentId);

	    return "redirect:/users/reviewNest/nestMypage";
	}
	



	
	
	
}
