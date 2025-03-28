package com.movie.plex.boards.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.boards.faq.FaqDTO;

@Service
public class QnaService {
	@Autowired
	private QnaDAO qnaDAO;
	
	public List<QnaDTO> getList() throws Exception {
		return qnaDAO.getList();
	}
	
	public QnaDTO getDetail(QnaDTO qnaDTO, boolean check) throws Exception {
		if(check) {
			qnaDAO.updateHit(qnaDTO);
		}
		return qnaDAO.getDetail(qnaDTO);
	}
	
	public int add(QnaDTO qnaDTO) throws Exception {
		return qnaDAO.add(qnaDTO);
	}
	
	public int update(QnaDTO qnaDTO) throws Exception {
		return qnaDAO.update(qnaDTO);
	}
	
	public int delete(QnaDTO qnaDTO) throws Exception {
		return qnaDAO.delete(qnaDTO);
	}
}
