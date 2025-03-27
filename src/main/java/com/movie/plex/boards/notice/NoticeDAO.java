package com.movie.plex.boards.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.plex.boards.faq.FaqDTO;

@Repository
public class NoticeDAO {

	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.movie.plex.boards.notice.NoticeDAO.";
	
	public List<NoticeDTO> getList() throws Exception {
		return sqlSession.selectList(NAMESPACE+"getList");
	}

	public NoticeDTO getDetail(NoticeDTO noticeDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getDetail", noticeDTO);
	}
	
	public int add(NoticeDTO noticeDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"add", noticeDTO);
	}
	
	public int update(NoticeDTO noticeDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"update", noticeDTO);
	}
	
	public int delete(NoticeDTO noticeDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"delete", noticeDTO);
	}
}
