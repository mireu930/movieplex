package com.movie.plex.nestcontents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.plex.pages.Pager;
import com.movie.plex.review.ReviewDTO;

	@Controller
	public class NestContentController {
	
		@Autowired
		private NestContentService nestContentService;
		  
		@RequestMapping(value="/reviewNest/getMovieList", method=RequestMethod.GET)
		public String getMovieList(Model model, Pager pager) throws Exception {
			List<NestContentDTO> movieList = nestContentService.getMovieList(pager);
			
			model.addAttribute("movieList", movieList);
			model.addAttribute("pager", pager);
			return "/reviewNest/getMovieList";
			
		}
		
		@RequestMapping(value="/reviewNest/getTvList", method=RequestMethod.GET)
		public String getTvList(Model model, Pager pager) throws Exception {
			List<NestContentDTO> tvList = nestContentService.getTvList(pager);
			
			model.addAttribute("tvList",tvList);
			model.addAttribute("pager", pager);
			return "/reviewNest/getTvList";
		}
		
		@RequestMapping(value="/reviewNest/getMovieDetail", method=RequestMethod.GET)
		public String getMovieDetail(@RequestParam("contentId") Long contentId, Model model)throws Exception{
			
			//System.out.println("contentId: " + contentId);
			
			NestContentDTO moviedetail= nestContentService.getMovieDetail(contentId);
			 model.addAttribute("content", moviedetail);
			  model.addAttribute("reviewList", moviedetail.getReviewList());
			
			return "/reviewNest/getMovieDetail";
		}
		
		@RequestMapping(value="/reviewNest/getTvDetail", method=RequestMethod.GET)
		public String getTvDetail(@RequestParam("contentId") Long contentId, Model model) throws Exception{
			
			NestContentDTO tvdetail = nestContentService.getTvDetail(contentId);
			
			 model.addAttribute("content", tvdetail);
			 model.addAttribute("reviewList", tvdetail.getReviewList());
			
			return "/reviewNest/getTvDetail";
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
