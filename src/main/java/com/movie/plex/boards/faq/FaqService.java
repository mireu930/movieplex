package com.movie.plex.boards.faq;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movie.plex.boards.qna.QnaDTO;
import com.movie.plex.boards.qna.QnaFilesDTO;
import com.movie.plex.files.FileManager;
import com.movie.plex.pages.Pager;

@Service
public class FaqService {
	@Autowired
	private FaqDAO faqDAO;
	@Autowired
	private FileManager fileManager;
	
	public List<FaqDTO> getList(Pager pager) throws Exception {
		Long totalCount = faqDAO.getTotalCount(pager);
		
		pager.makeNum();
		pager.makePage(totalCount);
		
		return faqDAO.getList(pager);
	}
	
	public FaqDTO getDetail(FaqDTO faqDTO, boolean check) throws Exception {
		if(check) {
			faqDAO.updateHit(faqDTO);			
		}
		return faqDAO.getDetail(faqDTO);
	}
	
	public int add(FaqDTO faqDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = faqDAO.add(faqDTO);
		
		for(MultipartFile attache: attaches) {
			if(attache.isEmpty()) {
				continue;
			}
			
			FaqFilesDTO faqFilesDTO = this.save(attache, session.getServletContext());
			faqFilesDTO.setFaqNum(faqDTO.getFaqNum());
			result = faqDAO.addFile(faqFilesDTO);
		}
		
		return result;
	}
	
	public int update(FaqDTO faqDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = faqDAO.update(faqDTO);
		
		for(MultipartFile attache: attaches) {
			if(attache.isEmpty()) {
				continue;
			}
			
			FaqFilesDTO faqFilesDTO = this.save(attache, session.getServletContext());
			faqFilesDTO.setFaqNum(faqDTO.getFaqNum());
			result = faqDAO.addFile(faqFilesDTO);
		}
		
		return result;
	}
	
	public int delete(FaqDTO faqDTO, HttpSession session) throws Exception {
		
		faqDTO = faqDAO.getDetail(faqDTO);
		
		int result = faqDAO.deleteFileAll(faqDTO);
		result = faqDAO.delete(faqDTO);
		
		if(result > 0) {
			String path = session.getServletContext().getRealPath("/resources/images/faq/");

			for(FaqFilesDTO faqFilesDTO:((FaqDTO)faqDTO).getFaqFilesDTOs()) {
				fileManager.delete(path, faqFilesDTO.getFileName());
			}
		}
		
		return result;
	}
	
	public int fileDelete(FaqFilesDTO faqFilesDTO, HttpSession session) throws Exception {
		faqFilesDTO = faqDAO.getFileDetail(faqFilesDTO);
		
		int result = faqDAO.deleteFile(faqFilesDTO);
		
		if(result > 0) {
			String path = session.getServletContext().getRealPath("/resources/images/faq/");

			fileManager.delete(path, faqFilesDTO.getFileName());
		}
		
		return result;
	}
	
	public FaqFilesDTO getFileDetail(FaqFilesDTO faqFilesDTO) throws Exception {
		return faqDAO.getFileDetail(faqFilesDTO);
	}
	
	private FaqFilesDTO save(MultipartFile attach, ServletContext context) throws Exception {
		String path = context.getRealPath("/resources/images/faq/");
		System.out.println(path);
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String f = fileManager.file(path, attach);
		
		FaqFilesDTO faqFilesDTO = new FaqFilesDTO();
		
		faqFilesDTO.setFileName(f);
		faqFilesDTO.setOldName(attach.getOriginalFilename());
		
		return faqFilesDTO;
	}
}
