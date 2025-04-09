package com.movie.plex.nestcontents;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.plex.like.ContentsLikeService;
import com.movie.plex.like.ReviewLikeService;
import com.movie.plex.pages.Pager;
import com.movie.plex.pages.ReviewPager;
import com.movie.plex.review.ReviewDTO;
import com.movie.plex.review.ReviewService;
import com.movie.plex.users.UserDTO;

	@Controller
	public class NestContentController {
	
		@Autowired
		private NestContentService nestContentService;
		@Autowired
		private ContentsLikeService contentsLikeService;
		@Autowired
		private ReviewService reviewService;
		@Autowired
		private ReviewLikeService reviewLikeService;
		  
		@RequestMapping(value="/reviewNest/getMovieList", method=RequestMethod.GET)
		public String getMovieList(Model model, ReviewPager pager, HttpSession session) throws Exception {
			List<NestContentDTO> movieList = nestContentService.getMovieList(pager);
			
			model.addAttribute("movieList", movieList);
			System.out.println(pager.isEndCheck());
			model.addAttribute("pager", pager);
			
			UserDTO user = (UserDTO) session.getAttribute("user");
	        if (user != null) {
	        	
	        	Long userNum = user.getUserNum();  
	            Long kind = 0L;  
	            
	            // 해당 유저가 좋아요 누른 영화 콘텐츠 목록 조회
	            List<Long> likedContentIds = contentsLikeService.getLikedContentsIds(userNum, kind);
	            model.addAttribute("likedContentIds", likedContentIds);
	            model.addAttribute("userNum", user.getUserNum());
	        }
			
			return "/reviewNest/getMovieList";
			
		}
		
		@RequestMapping(value="/reviewNest/getTvList", method=RequestMethod.GET)
		public String getTvList(Model model, ReviewPager pager, HttpSession session) throws Exception {
			List<NestContentDTO> tvList = nestContentService.getTvList(pager);
			
			model.addAttribute("tvList",tvList);
			model.addAttribute("pager", pager);
			
			UserDTO user = (UserDTO) session.getAttribute("user");
	        if (user != null) {
	        	
	        	Long userNum = user.getUserNum();  
	            Long kind = 1L;  
	            
	            // 해당 유저가 좋아요 누른 영화 콘텐츠 목록 조회
	            List<Long> likedContentIds = contentsLikeService.getLikedContentsIds(userNum, kind);
	            model.addAttribute("likedContentIds", likedContentIds);
	            model.addAttribute("userNum", user.getUserNum());
			
	        }
	        return "/reviewNest/getTvList";
		}
		
		@RequestMapping(value="/reviewNest/getMovieDetail", method=RequestMethod.GET)
		public String getMovieDetail(@RequestParam("contentId") Long contentId, HttpSession session , Model model)throws Exception{
			
			//System.out.println("contentId: " + contentId);
			
			NestContentDTO moviedetail= nestContentService.getMovieDetail(contentId);
			 model.addAttribute("content", moviedetail);
			 
			 
			// 2. 리뷰 목록 가져오기
			 List<ReviewDTO> reviewList = reviewService.getReviewList(contentId);
			 model.addAttribute("reviewList", reviewList);
			 
			  
			// 3. 로그인 유저 정보
			  UserDTO user = (UserDTO) session.getAttribute("user");
			  Long userNum = null;
			  if (user != null) {
			      userNum = user.getUserNum();
			  }
			   
			    // 4. 로그인 유저가 좋아요 누른 리뷰 ID 목록 가져오기 (kind = 0: 영화)
			    List<Long> likedReviewIds = new ArrayList<Long>();
			    if (userNum != null) {
			        likedReviewIds = reviewLikeService.getLikedReviewIds(userNum, 0L);
			    }

			    model.addAttribute("likedReviewIds", likedReviewIds);
			    model.addAttribute("userNum", userNum);
			
			return "/reviewNest/getMovieDetail";
		}
		
		@RequestMapping(value="/reviewNest/getTvDetail", method=RequestMethod.GET)
		public String getTvDetail(@RequestParam("contentId") Long contentId, HttpSession session, Model model) throws Exception{
			
			NestContentDTO tvdetail = nestContentService.getTvDetail(contentId);
			 model.addAttribute("content", tvdetail);
			 
			// 2. 리뷰 목록 가져오기
			 List<ReviewDTO> reviewList = reviewService.getReviewList(contentId);
			 model.addAttribute("reviewList", tvdetail.getReviewList());
			 
			// 3. 로그인 유저 정보
			  UserDTO user = (UserDTO) session.getAttribute("user");
			  Long userNum = null;
			  if (user != null) {
			      userNum = user.getUserNum();
			  }
			   
			    // 4. 로그인 유저가 좋아요 누른 리뷰 ID 목록 가져오기 
			    List<Long> likedReviewIds = new ArrayList<Long>();
			    if (userNum != null) {
			        likedReviewIds = reviewLikeService.getLikedReviewIds(userNum, 1L);
			    }
			
			    model.addAttribute("likedReviewIds", likedReviewIds);
			    model.addAttribute("userNum", userNum);
			    
			return "/reviewNest/getTvDetail";
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
