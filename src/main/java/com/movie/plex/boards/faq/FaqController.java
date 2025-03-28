package com.movie.plex.boards.faq;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.movie.plex.users.UserDTO;

@Controller
@RequestMapping(value = "/faq/*")
public class FaqController {

	@Autowired
	private FaqService faqService;
	
	@ModelAttribute("kind")
	public String getKind() {
		return "faq";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getList() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<FaqDTO> list = faqService.getList();
		
		modelAndView.addObject("list", list);
		modelAndView.setViewName("board/list");
		return modelAndView;
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView getDetail(FaqDTO faqDTO, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		Object obj = session.getAttribute("updateFaqHit");
		boolean check = false;
		
		if(obj!=null) {
			HashSet<Long> ar = (HashSet<Long>)obj;
			if(!ar.contains(faqDTO.getFaqNum())) {
				ar.add(faqDTO.getFaqNum());
				check = true;
			}
		}else {
			HashSet<Long> num = new HashSet<Long>();
			num.add(faqDTO.getFaqNum());
			session.setAttribute("updateFaqHit", num);
			check=true;
		}
		
		faqDTO = faqService.getDetail(faqDTO, check);
		
		modelAndView.addObject("dto", faqDTO);
		modelAndView.setViewName("board/detail");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(HttpSession session, Model model) throws Exception {
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		model.addAttribute("user", userDTO);
		return "board/boardform";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(FaqDTO faqDTO) throws Exception {
		int result = faqService.add(faqDTO);
		String a="";
		
		if(result>0) {
			a = "redirect:./list";
		}
		
		return a;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public ModelAndView update(FaqDTO faqDTO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		faqDTO = faqService.getDetail(faqDTO, false);
		
		modelAndView.addObject("dto", faqDTO);
		modelAndView.setViewName("board/boardform");
		
		return modelAndView;
	}

	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update2(FaqDTO faqDTO) throws Exception {
		int result = faqService.update(faqDTO);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(result > 0) {
			modelAndView.setViewName("redirect:./detail?faqNum="+faqDTO.getFaqNum());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView delete(FaqDTO faqDTO) throws Exception {
		int result = faqService.delete(faqDTO);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(result > 0) {
			modelAndView.addObject("result","삭제성공");
			modelAndView.addObject("path", "./list");
		} else {
			modelAndView.addObject("result","삭제실패");
			modelAndView.addObject("path", "./detail");
		}
		modelAndView.setViewName("commons/result");
		
		return modelAndView;
		
	}
	
	@RequestMapping(value = "fileDelete", method = RequestMethod.POST)
	public void fileDelete() throws Exception {
		
	}
	
	@RequestMapping(value = "fileDown", method = RequestMethod.GET)
	public void fileDown() throws Exception {
		
	}
}
