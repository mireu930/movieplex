package com.movie.plex.boards.qna;

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
@RequestMapping(value = "/qna/*")
public class QnaCotroller {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("kind")
	public String getKind() {
		return "qna";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getList() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<QnaDTO> list = qnaService.getList();
		
		modelAndView.addObject("list", list);
		modelAndView.setViewName("board/list");
		return modelAndView;
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView getDetail(QnaDTO qnaDTO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		qnaDTO = qnaService.getDetail(qnaDTO);
		
		modelAndView.addObject("dto", qnaDTO);
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
	public String add(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.add(qnaDTO);
		String a="";
		
		if(result>0) {
			a = "redirect:./list";
		}
		
		return a;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public ModelAndView update(QnaDTO qnaDTO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		qnaDTO = qnaService.getDetail(qnaDTO);
		
		modelAndView.addObject("dto", qnaDTO);
		modelAndView.setViewName("board/boardform");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update2(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.update(qnaDTO);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(result > 0) {
			modelAndView.setViewName("redirect:./detail?qnaNum="+qnaDTO.getqnaNum());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView delete(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.delete(qnaDTO);
		
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
