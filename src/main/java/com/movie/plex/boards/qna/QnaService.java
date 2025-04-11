package com.movie.plex.boards.qna;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movie.plex.boards.faq.FaqDTO;
import com.movie.plex.boards.notice.NoticeFilesDTO;
import com.movie.plex.files.FileManager;
import com.movie.plex.pages.Pager;


@Service
public class QnaService {
	@Autowired
	private QnaDAO qnaDAO;
	@Autowired
	private FileManager fileManager;
	
	public List<QnaDTO> getList(Pager pager) throws Exception {
		
		Long totalCount = qnaDAO.getTotalCount(pager);
		
		pager.makeNum();
		pager.makePage(totalCount);
		return qnaDAO.getList(pager);
	}
	
	public QnaDTO getDetail(QnaDTO qnaDTO, boolean check) throws Exception {
		if(check) {
			qnaDAO.updateHit(qnaDTO);
		}
		return qnaDAO.getDetail(qnaDTO);
	}
	
	public int add(QnaDTO qnaDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = qnaDAO.add(qnaDTO);
		
		for(MultipartFile attache: attaches) {
			if(attache.isEmpty()) {
				continue;
			}
			
			QnaFilesDTO qnaFilesDTO = this.save(attache, session.getServletContext());
			qnaFilesDTO.setQnaNum(qnaDTO.getqnaNum());
			result = qnaDAO.addFile(qnaFilesDTO);
		}
		
		return result;
	}
	
	public int update(QnaDTO qnaDTO, HttpSession session, MultipartFile [] attaches) throws Exception {
		int result = qnaDAO.update(qnaDTO);
		
		for(MultipartFile attache: attaches) {
			if(attache.isEmpty()) {
				continue;
			}
				QnaFilesDTO qnaFilesDTO = this.save(attache, session.getServletContext());
				//DBÀúÀå
				qnaFilesDTO.setQnaNum(qnaDTO.getqnaNum());
				result = qnaDAO.addFile(qnaFilesDTO);
				
			}
		
		return result;
	}
	
	public int delete(QnaDTO qnaDTO, HttpSession session) throws Exception {
		qnaDTO = qnaDAO.getDetail(qnaDTO);
		
		int result = qnaDAO.deleteFileAll(qnaDTO);
		result = qnaDAO.delete(qnaDTO);
		
		if(result > 0) {
			String path = session.getServletContext().getRealPath("/resources/images/qna/");
			
			for(QnaFilesDTO qnaFilesDTO:((QnaDTO)qnaDTO).getQnaFilesDTOs()) {
				fileManager.delete(path, qnaFilesDTO.getFileName());
			}
		}
		
		return result;
	}
	
	public int fileDelete(QnaFilesDTO qnaFilesDTO, HttpSession session) throws Exception {
		qnaFilesDTO = qnaDAO.getFileDetail(qnaFilesDTO);
		
		int result = qnaDAO.deleteFile(qnaFilesDTO);
		
		if(result > 0) {
			String path = session.getServletContext().getRealPath("/resources/images/qna/");

			fileManager.delete(path, qnaFilesDTO.getFileName());
		}
		
		return result;
	}
	
	public QnaFilesDTO getFileDetail(QnaFilesDTO qnaFilesDTO) throws Exception {
		return qnaDAO.getFileDetail(qnaFilesDTO);
	}
	
	public String detailFiles(HttpSession session, MultipartFile files)throws Exception{
		String path = session.getServletContext().getRealPath("/resources/images/qna/");
		System.out.println(path);
		String fileName = fileManager.file(path, files);
		System.out.println(fileName);
		return fileName;
		
	}
	
	public void deleteFile(QnaFilesDTO qnaFilesDTO, HttpSession session) throws Exception {
		String path = session.getServletContext().getRealPath("/resources/images/qna/");
		fileManager.delete(path, qnaFilesDTO.getFileName());
	}
	
	public int reply(QnaDTO qnaDTO) throws Exception {
		
		QnaDTO parent = qnaDAO.getDetail(qnaDTO);
		
		System.out.println(parent.getqnaNum());
		System.out.println(parent.getBoardRef());
		System.out.println(parent.getBoardStep());
		System.out.println(parent.getBoardDepth());
		
		qnaDTO.setBoardRef(parent.getBoardRef());
		qnaDTO.setBoardStep(parent.getBoardStep()+1);
		qnaDTO.setBoardDepth(parent.getBoardDepth()+1);
		
		int result = qnaDAO.updateStep(parent);
		
		result = qnaDAO.reply(qnaDTO);
		
		return result;
	}
	
	private QnaFilesDTO save(MultipartFile attach, ServletContext context) throws Exception {
		String path = context.getRealPath("/resources/images/qna/");
		System.out.println(path);
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String f = fileManager.file(path, attach);
		
		QnaFilesDTO qnaFilesDTO = new QnaFilesDTO();
		
		qnaFilesDTO.setFileName(f);
		qnaFilesDTO.setOldName(attach.getOriginalFilename());
		
		return qnaFilesDTO;
	}
}
