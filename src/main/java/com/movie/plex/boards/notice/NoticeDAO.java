package com.movie.plex.boards.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.plex.boards.faq.FaqDTO;
import com.movie.plex.boards.qna.QnaDTO;
import com.movie.plex.boards.qna.QnaFilesDTO;
import com.movie.plex.pages.Pager;

@Repository
public class NoticeDAO {

	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.movie.plex.boards.notice.NoticeDAO.";
	
	public List<NoticeDTO> getList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"getList", pager);
	}
	
	public Long getTotalCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getTotalCount", pager);
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
	
	public int updateHit(NoticeDTO noticeDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"updateHit", noticeDTO);
	}
	
	public int addFile(NoticeFilesDTO noticeFilesDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"addFile", noticeFilesDTO);
	}
	
	public NoticeFilesDTO getFileDetail(NoticeFilesDTO noticeFilesDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getFileDetail", noticeFilesDTO);
	}
	
	public int deleteFile(NoticeFilesDTO noticeFilesDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"deleteFile", noticeFilesDTO);
	}
	
	public int deleteFileAll(NoticeDTO noticeDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"deleteFileAll", noticeDTO);
	}
}
