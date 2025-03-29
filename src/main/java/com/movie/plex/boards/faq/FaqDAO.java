package com.movie.plex.boards.faq;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.plex.boards.notice.NoticeDTO;
import com.movie.plex.boards.notice.NoticeFilesDTO;
import com.movie.plex.boards.qna.QnaDTO;
import com.movie.plex.boards.qna.QnaFilesDTO;
import com.movie.plex.pages.Pager;

@Repository
public class FaqDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.movie.plex.boards.faq.FaqDAO.";
	
	public List<FaqDTO> getList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"getList", pager);
	}
	
	public Long getTotalCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getTotalCount", pager);
	}
	
	public FaqDTO getDetail(FaqDTO faqDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getDetail", faqDTO);
	}
	
	public int add(FaqDTO faqDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"add", faqDTO);
	}
	
	public int update(FaqDTO faqDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"update", faqDTO);
	}
	
	public int delete(FaqDTO faqDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"delete", faqDTO);
	}
	
	public int updateHit(FaqDTO faqDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"updateHit", faqDTO);
	}
	
	public int addFile(FaqFilesDTO faqFilesDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"addFile", faqFilesDTO);
	}
	
	public FaqFilesDTO getFileDetail(FaqFilesDTO faqFilesDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getFileDetail", faqFilesDTO);
	}
	
	public int deleteFile(FaqFilesDTO faqFilesDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"deleteFile", faqFilesDTO);
	}
	
	public int deleteFileAll(FaqDTO faqDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"deleteFileAll", faqDTO);
	}
}
