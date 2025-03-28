package com.movie.plex.boards.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.boards.faq.FaqDTO;

@Service
public class NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	public List<NoticeDTO> getList() throws Exception {
		return noticeDAO.getList();
	}
	
	public NoticeDTO getDetail(NoticeDTO noticeDTO, boolean check) throws Exception {
		if(check) {
			noticeDAO.updateHit(noticeDTO);
		}
		return noticeDAO.getDetail(noticeDTO);
	}
	
	public int add(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.add(noticeDTO);
	}
	
	public int update(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.update(noticeDTO);
	}
	
	public int delete(NoticeDTO noticeDTO) throws Exception {
		return noticeDAO.delete(noticeDTO);
	}
}
