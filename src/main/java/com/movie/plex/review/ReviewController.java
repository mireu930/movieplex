package com.movie.plex.review;

import java.io.PrintWriter;
import java.util.ArrayList;
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
		List<ReviewDTO> ar = reviewService.getReviewList(contentId, kind);
		
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
	public String updateReview(ReviewDTO reviewDTO) throws Exception{
		int result = reviewService.updateReview(reviewDTO);
		
		return "redirect:./getReviewDetail?reviewId=" +reviewDTO.getReviewId();
	}
	
	

	
	

	@RequestMapping(value="deleteReview", method=RequestMethod.GET)
	public String deleteReview(ReviewDTO reviewDTO, Model model) throws Exception{
		int result = reviewService.deleteReview(reviewDTO);
		String s = "삭제 실패";

		if(result>0) {
				s = "���� ����";
		}
    
		model.addAttribute("result" ,s);
		model.addAttribute("path", "./getMovieReviewList");
		
		return "commons/result";
	}
  
	@RequestMapping(value="deleteTvReview", method=RequestMethod.GET)
	public String deleteTvReview(ReviewDTO reviewDTO, Model model) throws Exception{
		int result = reviewService.deleteMovieReview(reviewDTO);
		String s = "���� ����";
		if(result>0) {
				s = "���� ����";
		}
		model.addAttribute("result" ,s);
		model.addAttribute("path", "./getTvReviewList");

		
		return "commons/result";
	}
	
	
	
	
}
