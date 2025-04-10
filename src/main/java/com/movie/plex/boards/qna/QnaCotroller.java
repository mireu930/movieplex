package com.movie.plex.boards.qna;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.movie.plex.boards.notice.NoticeFilesDTO;
import com.movie.plex.pages.Pager;
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
	public ModelAndView getList(Pager pager) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<QnaDTO> list = qnaService.getList(pager);
		
		modelAndView.addObject("list", list);
		modelAndView.addObject("pager", pager);
		modelAndView.setViewName("board/list");
		return modelAndView;
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView getDetail(QnaDTO qnaDTO, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		Object obj = session.getAttribute("updateQnaHit");
		boolean check = false;
		
		if(obj!=null) {
			HashSet<Long> ar = (HashSet<Long>)obj;
			if(!ar.contains(qnaDTO.getqnaNum())) {
				ar.add(qnaDTO.getqnaNum());
				check = true;
			}
		}else {
			HashSet<Long> num = new HashSet<Long>();
			num.add(qnaDTO.getqnaNum());
			session.setAttribute("updateQnaHit", num);
			check=true;
		}
		
		qnaDTO = qnaService.getDetail(qnaDTO,check);
		
		modelAndView.addObject("dto", qnaDTO);
		modelAndView.setViewName("board/detail");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(HttpSession session, Model model) throws Exception {
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		
		if(userDTO == null) {
			model.addAttribute("result", "로그인이 필요합니다.");
			model.addAttribute("path", "./list");
			return "commons/result";
		} else {
			model.addAttribute("user", userDTO);
			return "board/boardform";			
		}
		
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(QnaDTO qnaDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = qnaService.add(qnaDTO, session, attaches);
		String a="";
		
		if(result>0) {
			a = "redirect:./list";
		}
		
		return a;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public ModelAndView update(QnaDTO qnaDTO, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		qnaDTO = qnaService.getDetail(qnaDTO,false);
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		
		if(userDTO == null) { 
			modelAndView.addObject("result", "로그인이 필요합니다.");
			modelAndView.addObject("path", "./detail?qnaNum="+qnaDTO.getqnaNum());
			modelAndView.setViewName("commons/result");
		} else if(userDTO.getUserNum() != qnaDTO.getUserNum()) {
			modelAndView.addObject("result", "작성자만 수정가능합니다.");
			modelAndView.addObject("path", "./detail?qnaNum="+qnaDTO.getqnaNum());
			modelAndView.setViewName("commons/result");	
		}else {
			modelAndView.addObject("dto", qnaDTO);
			modelAndView.setViewName("board/boardform");			
		}
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update2(QnaDTO qnaDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = qnaService.update(qnaDTO,session,attaches);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(result > 0) {
			modelAndView.setViewName("redirect:./detail?qnaNum="+qnaDTO.getqnaNum());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView delete(QnaDTO qnaDTO, HttpSession session) throws Exception {
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		qnaDTO = qnaService.getDetail(qnaDTO,false);
		ModelAndView modelAndView = new ModelAndView();
		
		if(userDTO == null) { 
			modelAndView.addObject("result", "로그인이 필요합니다.");
			modelAndView.addObject("path", "./detail?qnaNum="+qnaDTO.getqnaNum());
			modelAndView.setViewName("commons/result");
		} else if(userDTO.getUserNum() != qnaDTO.getUserNum()) {
			System.out.println("userNum");
			System.out.println(userDTO.getUserNum());
			System.out.println(qnaDTO.getUserNum());
			modelAndView.addObject("result", "작성자만 삭제가능합니다.");
			modelAndView.addObject("path", "./detail?qnaNum="+qnaDTO.getqnaNum());
			modelAndView.setViewName("commons/result");
		} else if(userDTO.getUserNum() == qnaDTO.getUserNum()||userDTO.getUserGrade() == 4) {
			int result = qnaService.delete(qnaDTO, session);
			if(result > 0) {
				modelAndView.addObject("result","삭제성공");
				modelAndView.addObject("path", "./list");
			} else {
				modelAndView.addObject("result","삭제실패");
				modelAndView.addObject("path", "./detail");
			}
			modelAndView.setViewName("commons/result");			
		} else {
			modelAndView.addObject("result","에러");
			modelAndView.addObject("path", "./list");
			modelAndView.setViewName("commons/result");	
		}
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = "reply", method = RequestMethod.GET)
	public String reply(HttpSession session, Model model, @ModelAttribute("reply")QnaDTO qnaDTO, QnaDTO qnadto) throws Exception {
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		if(userDTO == null) {
			model.addAttribute("result", "로그인이 필요합니다.");
			model.addAttribute("path", "./detail?qnaNum="+qnadto.getqnaNum());
			return "commons/result";
		}else {
			model.addAttribute("user", userDTO);
			return "board/boardform";			
		}
	}
	
	@RequestMapping(value = "reply", method = RequestMethod.POST)
	public String reply(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.reply(qnaDTO);
		String a ="";
		
		if(result>0) {
			a="redirect:./list";
		}
		
		return a;
	}
	
	@RequestMapping(value = "fileDelete", method = RequestMethod.POST)
	public String fileDelete(QnaFilesDTO qnaFilesDTO, HttpSession session, Model model) throws Exception {
		int result = qnaService.fileDelete(qnaFilesDTO, session);
		model.addAttribute("result", result);
		return "commons/ajax";
	}
	
	@RequestMapping(value = "fileDown", method = RequestMethod.GET)
	public String fileDown(QnaFilesDTO qnaFilesDTO, Model model) throws Exception {
		qnaFilesDTO = qnaService.getFileDetail(qnaFilesDTO);
		model.addAttribute("file", qnaFilesDTO);
		return "fileDown";
	}
	
	@RequestMapping(value = "detailFiles", method = RequestMethod.POST)
	public String detailFiles (MultipartFile uploadFile,HttpSession session, Model model)throws Exception{
		String fileName = qnaService.detailFiles(session, uploadFile);
		
		fileName = "/resources/images/qna/"+fileName;
		
		model.addAttribute("result", fileName);
		
		return "commons/ajax";
	}
	
	@RequestMapping(value = "detailFilesDelete", method = RequestMethod.POST)
	public String detailFilesDelete(HttpSession session, QnaFilesDTO qnaFilesDTO, Model model) throws Exception {
		System.out.println("detailFilesDelete");
		qnaService.deleteFile(qnaFilesDTO, session);
		model.addAttribute("result", 1);
		return "commons/ajax";
	}
}
