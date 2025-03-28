package com.movie.plex.boards.faq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.boards.qna.QnaDTO;

@Service
public class FaqService {
	@Autowired
	private FaqDAO faqDAO;
	
	public List<FaqDTO> getList() throws Exception {
		return faqDAO.getList();
	}
	
	public FaqDTO getDetail(FaqDTO faqDTO, boolean check) throws Exception {
		if(check) {
			faqDAO.updateHit(faqDTO);			
		}
		return faqDAO.getDetail(faqDTO);
	}
	
	public int add(FaqDTO faqDTO) throws Exception {
		return faqDAO.add(faqDTO);
	}
	
	public int update(FaqDTO faqDTO) throws Exception {
		return faqDAO.update(faqDTO);
	}
	
	public int delete(FaqDTO faqDTO) throws Exception {
		return faqDAO.delete(faqDTO);
	}
}
