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
import com.movie.plex.users.KakaoApi;
import com.movie.plex.users.UserDTO;
import com.movie.plex.users.UserService;

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
		@Autowired
		private KakaoApi kakaoApi;
		@Autowired
		private UserService userService;
		  
		@RequestMapping(value="/reviewNest/getMovieList", method=RequestMethod.GET)
		public String getMovieList(Model model, ReviewPager pager, HttpSession session) throws Exception {
			List<NestContentDTO> movieList = nestContentService.getMovieList(pager);
			
			model.addAttribute("movieList", movieList);
			model.addAttribute("pager", pager);
			
			UserDTO user = (UserDTO) session.getAttribute("user");
	        if (user != null) {
	        	
	        	Long userNum = user.getUserNum();  
	            Long kind = 0L;  
	            
	            // �빐�떦 �쑀��媛� 醫뗭븘�슂 �늻瑜� �쁺�솕 肄섑뀗痢� 紐⑸줉 議고쉶
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
	            
	            // �빐�떦 �쑀��媛� 醫뗭븘�슂 �늻瑜� �쁺�솕 肄섑뀗痢� 紐⑸줉 議고쉶
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
			 
			// 2. 由щ럭 紐⑸줉 媛��졇�삤湲�
			  List<ReviewDTO> reviewList = reviewService.getReviewList(contentId, 0L);
			  model.addAttribute("reviewList", moviedetail.getReviewList());
			  
			// 3. 濡쒓렇�씤 �쑀�� �젙蹂�
			  UserDTO user = (UserDTO) session.getAttribute("user");
			  Long userNum = null;
			  if (user != null) {
			      userNum = user.getUserNum();
			  }
			   
			    // 4. 濡쒓렇�씤 �쑀��媛� 醫뗭븘�슂 �늻瑜� 由щ럭 ID 紐⑸줉 媛��졇�삤湲� (kind = 0: �쁺�솕)
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
			 
			// 2. 由щ럭 紐⑸줉 媛��졇�삤湲�
			 List<ReviewDTO> reviewList = reviewService.getReviewList(contentId, 1L);
			 model.addAttribute("reviewList", tvdetail.getReviewList());
			 
			// 3. 濡쒓렇�씤 �쑀�� �젙蹂�
			  UserDTO user = (UserDTO) session.getAttribute("user");
			  Long userNum = null;
			  if (user != null) {
			      userNum = user.getUserNum();
			  }
			   
			    // 4. 濡쒓렇�씤 �쑀��媛� 醫뗭븘�슂 �늻瑜� 由щ럭 ID 紐⑸줉 媛��졇�삤湲� 
			    List<Long> likedReviewIds = new ArrayList<Long>();
			    if (userNum != null) {
			        likedReviewIds = reviewLikeService.getLikedReviewIds(userNum, 1L);
			    }
			
			    model.addAttribute("likedReviewIds", likedReviewIds);
			    model.addAttribute("userNum", userNum);
			    
			return "/reviewNest/getTvDetail";
		}
		
		
		@RequestMapping(value = "/reviewNest/login", method = RequestMethod.GET)
		public String getLogin(Model model) throws Exception {
			model.addAttribute("kakaoApi", kakaoApi.getKakaoApi());
			model.addAttribute("redirectUrl2", kakaoApi.getKakaoRedirectUrl2());
			return "/reviewNest/login";
		}
		
		
		
		@RequestMapping(value = "/reviewNest/login", method = RequestMethod.POST)
		public String getLogin(UserDTO userDTO, HttpSession session, Model model) throws Exception {
			userDTO = userService.getLogin(userDTO);
			
			if(userDTO != null) {
				 if (userDTO.getUserOut() == 1) {
					// 사용자가 비활성화된 상태일 경우 로그인 실패 처리
			            model.addAttribute("result", "비활성화된 계정입니다 관리자에게 문의하세요.");
			            model.addAttribute("path", "./login");
			            return "commons/result";  // 비활성화된 사용자 메시지 출력
			        }else {
			        	session.setAttribute("user", userDTO);
			        	
			        	return "redirect:/reviewNest";
			        }
			}
			
			model.addAttribute("result", "로그인 실패");
			model.addAttribute("path", "./login");
			
			
			return "commons/result";
		}
		
		@RequestMapping(value = "/reviewNest/logout", method=RequestMethod.GET)
		public String logout(HttpSession session) throws Exception {
			session.invalidate();
			return "redirect:/reviewNest";
		}
		
		@RequestMapping(value = "/reviewNest/kakaologout", method = RequestMethod.GET)
		public String kakaologout(HttpSession session) throws Exception {
			String a = "redirect:/reviewNest";
			
			String accessToken = (String) session.getAttribute("accessToken");

		    if (accessToken != null) {
		        kakaoApi.kakaoLogout(accessToken);
		        session.removeAttribute("accessToken");
		        String kakaoUrl = "https://kauth.kakao.com/oauth/logout?client_id="+kakaoApi.getKakaoApi()+"&logout_redirect_uri=http://localhost/reviewNest/login";
		        a = "redirect:"+kakaoUrl;
		    }
		    session.invalidate();
			return a;
		}
		
	}
	
	
