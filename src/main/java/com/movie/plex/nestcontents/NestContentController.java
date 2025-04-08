package com.movie.plex.nestcontents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.movie.plex.pages.Pager;

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
		public ModelAndView getMovieDetail(NestContentDTO nestContentDTO) throws Exception{
			//System.out.println("movie detail");
			
			nestContentDTO = nestContentService.getMovieDetail(nestContentDTO);
			
			ModelAndView mv = new ModelAndView();
			//model
			mv.addObject("content", nestContentDTO);
			//view
			mv.setViewName("/reviewNest/getMovieDetail");
			
			return mv;
		}
		
		@RequestMapping(value="/reviewNest/getTvDetail", method=RequestMethod.GET)
		public ModelAndView getTvDetail(NestContentDTO nestContentDTO) throws Exception{
			//System.out.println("movie detail");
			
			nestContentDTO = nestContentService.getTvDetail(nestContentDTO);
			
			ModelAndView mv = new ModelAndView();
			//model
			mv.addObject("content", nestContentDTO);
			//view
			mv.setViewName("/reviewNest/getTvDetail");
			
			return mv;
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
