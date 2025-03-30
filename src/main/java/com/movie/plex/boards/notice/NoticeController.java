package com.movie.plex.boards.notice;

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

import com.movie.plex.pages.Pager;
import com.movie.plex.users.UserDTO;

@Controller
@RequestMapping(value = "/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("kind")
	public String getKind() {
		return "notice";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getList(Pager pager) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		List<NoticeDTO> list = noticeService.getList(pager);
		
		modelAndView.addObject("list", list);
		modelAndView.setViewName("board/list");
		return modelAndView;
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView getDetail(NoticeDTO noticeDTO, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		Object obj = session.getAttribute("updateNoticeHit");
		boolean check = false;
		
		if(obj!=null) {
			HashSet<Long> ar = (HashSet<Long>)obj;
			if(!ar.contains(noticeDTO.getNoticeNum())) {
				ar.add(noticeDTO.getNoticeNum());
				check = true;
			}
		}else {
			HashSet<Long> num = new HashSet<Long>();
			num.add(noticeDTO.getNoticeNum());
			session.setAttribute("updateNoticeHit", num);
			check=true;
		}
		
		noticeDTO = noticeService.getDetail(noticeDTO,check);
		
		modelAndView.addObject("dto", noticeDTO);
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
	public String add(NoticeDTO noticeDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = noticeService.add(noticeDTO, session, attaches);
		String a="";
		
		if(result>0) {
			a = "redirect:./list";
		}
		
		return a;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public ModelAndView update(NoticeDTO noticeDTO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		noticeDTO = noticeService.getDetail(noticeDTO,false);
		
		modelAndView.addObject("dto", noticeDTO);
		modelAndView.setViewName("board/boardform");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update2(NoticeDTO noticeDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = noticeService.update(noticeDTO, session, attaches);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if(result > 0) {
			modelAndView.setViewName("redirect:./detail?noticeNum="+noticeDTO.getNoticeNum());
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView delete(NoticeDTO noticeDTO, HttpSession session) throws Exception {
		int result = noticeService.delete(noticeDTO, session);
		
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
	public String fileDelete(NoticeFilesDTO noticeFilesDTO, HttpSession session, Model model) throws Exception {
		int result = noticeService.fileDelete(noticeFilesDTO, session);
		model.addAttribute("result", result);
		return "commons/ajax";
	}
	
	@RequestMapping(value = "fileDown", method = RequestMethod.GET)
	public String fileDown(NoticeFilesDTO noticeFilesDTO, Model model) throws Exception {
		noticeFilesDTO = noticeService.getFileDetail(noticeFilesDTO);
		model.addAttribute("file", noticeFilesDTO);
		return "fileDown";
	}
	
	@RequestMapping(value = "detailFiles", method = RequestMethod.POST)
	public String detailFiles (MultipartFile uploadFile,HttpSession session, Model model)throws Exception{
		String fileName = noticeService.detailFiles(session, uploadFile);
		
		fileName = "/resources/images/notice/"+fileName;
		
		model.addAttribute("result", fileName);
		
		return "commons/ajax";
	}
	
	@RequestMapping(value = "detailFilesDelete", method = RequestMethod.POST)
	public String detailFilesDelete(HttpSession session, NoticeFilesDTO noticeFilesDTO, Model model) throws Exception {
		System.out.println("detailFilesDelete");
		noticeService.deleteFile(noticeFilesDTO, session);
		model.addAttribute("result", 1);
		return "commons/ajax";
	}
}
