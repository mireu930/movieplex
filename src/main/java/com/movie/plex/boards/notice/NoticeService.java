package com.movie.plex.boards.notice;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movie.plex.boards.faq.FaqDTO;
import com.movie.plex.boards.qna.QnaDTO;
import com.movie.plex.boards.qna.QnaFilesDTO;
import com.movie.plex.files.FileManager;
import com.movie.plex.pages.Pager;

@Service
public class NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private FileManager fileManager;
	
	public List<NoticeDTO> getList(Pager pager) throws Exception {
		Long totalCount = noticeDAO.getTotalCount(pager);
		
		pager.makeNum();
		pager.makePage(totalCount);
		
		return noticeDAO.getList(pager);
	}
	
	public NoticeDTO getDetail(NoticeDTO noticeDTO, boolean check) throws Exception {
		if(check) {
			noticeDAO.updateHit(noticeDTO);
		}
		return noticeDAO.getDetail(noticeDTO);
	}
	
	public int add(NoticeDTO noticeDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = noticeDAO.add(noticeDTO);
		
		for(MultipartFile attache: attaches) {
			if(attache.isEmpty()) {
				continue;
			}
			
			NoticeFilesDTO noticeFilesDTO = this.save(attache, session.getServletContext());
			noticeFilesDTO.setNoticeNum(noticeDTO.getNoticeNum());
			result = noticeDAO.addFile(noticeFilesDTO);
		}
		return result;
	}
	
	public int update(NoticeDTO noticeDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = noticeDAO.update(noticeDTO);
		
		for(MultipartFile attache: attaches) {
			if(attache.isEmpty()) {
				continue;
			}
			NoticeFilesDTO noticeFilesDTO = this.save(attache, session.getServletContext());
			
			noticeFilesDTO.setNoticeNum(noticeDTO.getNoticeNum());
			result = noticeDAO.addFile(noticeFilesDTO);
				
			}
		
		return result;
	}
	
	public int delete(NoticeDTO noticeDTO, HttpSession session) throws Exception {
		int result = noticeDAO.deleteFileAll(noticeDTO);
		result = noticeDAO.delete(noticeDTO);
		
		if(result > 0) {
			String path = session.getServletContext().getRealPath("/resources/images/notice/");
			if (noticeDTO != null && noticeDTO.getNoticeFilesDTOs() != null) {
			    for (NoticeFilesDTO noticeFilesDTO : noticeDTO.getNoticeFilesDTOs()) {
			        fileManager.delete(path, noticeFilesDTO.getFileName());
			    }
			}
		}
		
		return result;
	}
	
	public int fileDelete(NoticeFilesDTO noticeFilesDTO, HttpSession session) throws Exception {
		noticeFilesDTO = noticeDAO.getFileDetail(noticeFilesDTO);
		
		int result = noticeDAO.deleteFile(noticeFilesDTO);
		
		if(result > 0) {
			String path = session.getServletContext().getRealPath("/resources/images/notice/");

			fileManager.delete(path, noticeFilesDTO.getFileName());
		}
		
		return result;
	}
	
	public NoticeFilesDTO getFileDetail(NoticeFilesDTO noticeFilesDTO) throws Exception {
		return noticeDAO.getFileDetail(noticeFilesDTO);
	}
	
	public String detailFiles(HttpSession session, MultipartFile files)throws Exception{
		String path = session.getServletContext().getRealPath("/resources/images/notice/");
		System.out.println(path);
		String fileName = fileManager.file(path, files);
		System.out.println(fileName);
		return fileName;
		
	}
	
	public void deleteFile(NoticeFilesDTO noticeFilesDTO, HttpSession session) throws Exception {
		String path = session.getServletContext().getRealPath("/resources/images/notice/");
		fileManager.delete(path, noticeFilesDTO.getFileName());
	}
	
	private NoticeFilesDTO save(MultipartFile attach, ServletContext context) throws Exception {
		String path = context.getRealPath("/resources/images/notice/");
		System.out.println(path);
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String f = fileManager.file(path, attach);
		
		NoticeFilesDTO noticeFilesDTO = new NoticeFilesDTO();
		
		noticeFilesDTO.setFileName(f);
		noticeFilesDTO.setOldName(attach.getOriginalFilename());
		
		return noticeFilesDTO;
	}
}
