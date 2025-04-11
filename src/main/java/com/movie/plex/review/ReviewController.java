package com.movie.plex.review;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.movie.plex.like.ContentsLikeService;
import com.movie.plex.like.ReviewLikeService;
import com.movie.plex.users.UserDTO;

@Controller
@RequestMapping(value = "/reviewNest/*")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ReviewCommentService reviewCommentService;
	@Autowired
	private ReviewLikeService reviewLikeService;
	@Autowired
	private ContentsLikeService contentsLikeService;
	
	@RequestMapping(value="checkUser", method= RequestMethod.GET)
	public String checkUser(HttpSession session, Model model) throws Exception{
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		
		if(userDTO == null) {
			model.addAttribute("result", 0);
		}else {
			model.addAttribute("result", 1);
		}
		return "/commons/ajax";
	} 

	@RequestMapping(value = "addReview", method = RequestMethod.GET)
	public void addReview() throws Exception {

	}

	@RequestMapping(value = "addReview", method = RequestMethod.POST)
	public String addReview(ReviewDTO reviewDTO, HttpSession session,HttpServletResponse response) throws Exception {
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		
		
		 if (reviewDTO.getReviewRate() == null) {
		        reviewDTO.setReviewRate(0L); // 기본값 0
		    }
		
		
		reviewDTO.setUserNum(userDTO.getUserNum());
		
		 int exists = reviewService.checkReviewExists(reviewDTO.getUserNum(), reviewDTO.getContentId(), reviewDTO.getKind());
		    if (exists > 0) {
		        response.setContentType("text/html; charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        out.println("<script>alert('이미 작성한 리뷰가 있습니다.'); history.back();</script>");
		        out.flush();
		        return null;
		    }
		
		int result = reviewService.addReview(reviewDTO);

		if (reviewDTO.getKind() == 0) {
	        return "redirect:./getMovieDetail?contentId=" + reviewDTO.getContentId();
	    } else {
	        return "redirect:./getTvDetail?contentId=" + reviewDTO.getContentId();
	    }
	}



	@RequestMapping(value = "getReviewList", method = RequestMethod.GET)
	public String getReviewList(@RequestParam("contentId") Long contentId, @RequestParam("kind") Long kind, HttpSession session ,Model model) throws Exception {
		
		System.out.println("📌 getReviewList() 호출됨");
		
		// DTO에 값 세팅
	    ReviewDTO reviewDTO = new ReviewDTO();
	    reviewDTO.setContentId(contentId);
	    reviewDTO.setKind(kind);
		
		// 리뷰 목록
		List<ReviewDTO> ar = reviewService.getReviewList(contentId);
		
		//null일 경우 빈 리스트로 초기화-jsp c:if문
		if (ar == null) {
	        ar = new ArrayList<ReviewDTO>();
	    }
		model.addAttribute("reviewList", ar);
		System.out.println("리뷰개수: " + ar.size());
		
		// 2. 로그인한 유저 번호
	    Long userNum = (Long) session.getAttribute("userNum");

	    // 3. 유저가 좋아요 누른 리뷰 ID 목록 가져오기
	    List<Long> likedReviewIds = new ArrayList<Long>();
	    if (userNum != null) {
	        likedReviewIds = reviewLikeService.getLikedReviewIds(userNum, kind);
	    }
	    model.addAttribute("likedReviewIds", likedReviewIds);
		
		if (reviewDTO.getKind() == 0) {
	        return "redirect:./getMovieDetail?contentId=" + reviewDTO.getContentId();
	    } else {
	        return "redirect:./getTvDetail?contentId=" + reviewDTO.getContentId();
	    }
	}
	
	@RequestMapping(value= "/reviewNest/getReviewDetail", method=RequestMethod.GET)
	public String getReviewDetail(@RequestParam("reviewId") Long reviewId, Model model) throws Exception {
		
		//리뷰상세
		ReviewDTO reviewDetail = reviewService.getReviewDetail(reviewId);
	        model.addAttribute("reviewDetail", reviewDetail);
	    
	    //댓글리스트
	    List<ReviewCommentDTO> commentList = reviewCommentService.getCommentsByReviewId(reviewId);
	    model.addAttribute("commentList", commentList);
	        
	    return "reviewNest/getReviewDetail";
	}

	
	
	@RequestMapping(value="updateReview", method= RequestMethod.GET)
	public void updateReview(ReviewDTO reviewDTO, Model model) throws Exception {
			//reviewDTO = reviewService.updateMovieReview(reviewDTO);
			model.addAttribute("reviewDTO", reviewDTO);
	}
	
	@RequestMapping(value="updateReview", method=RequestMethod.POST)
	public String updateReview(ReviewDTO reviewDTO, HttpSession session, Model model) throws Exception{
		reviewService.updateReview(reviewDTO);
		
		UserDTO user = (UserDTO) session.getAttribute("user");
	    if (user == null) return "redirect:/login";

	    model.addAttribute("myReviews", reviewService.getMyReviews(user.getUserNum()));
	    model.addAttribute("myComments", reviewCommentService.getMyComments(user.getUserNum()));
	    model.addAttribute("likedContents", contentsLikeService.getMyLikedContents(user.getUserNum()));
	    model.addAttribute("likedReviews", reviewLikeService.getMyLikedReviews(user.getUserNum()));

		
		return "/reviewNest/nestMypage";
	}
	

	
	@RequestMapping(value="deleteReview", method=RequestMethod.POST)
	public String deleteReview(@RequestParam("reviewId") Long reviewId, HttpSession session, RedirectAttributes redirectAttributes) throws Exception{
		try {
	        reviewService.deleteReview(reviewId);
	        redirectAttributes.addFlashAttribute("message", "리뷰가 성공적으로 삭제되었습니다.");
	    } catch (DataIntegrityViolationException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "댓글이 달린 리뷰는 삭제할 수 없습니다.");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
	    }

	    return "redirect:/users/reviewNest/nestMypage";

	
	}
  
	
}
