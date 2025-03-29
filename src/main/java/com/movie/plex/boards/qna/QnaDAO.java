package com.movie.plex.boards.qna;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.movie.plex.pages.Pager;

@Repository
public class QnaDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.movie.plex.boards.qna.QnaDAO.";
	
	public List<QnaDTO> getList(Pager pager) throws Exception {
		return sqlSession.selectList(NAMESPACE+"getList", pager);
	}
	
	public Long getTotalCount(Pager pager) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getTotalCount", pager);
	}
	
	public QnaDTO getDetail(QnaDTO qnaDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getDetail", qnaDTO);
	}
	
	public int add(QnaDTO qnaDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"add", qnaDTO);
	}
	
	public int update(QnaDTO qnaDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"update", qnaDTO);
	}
	
	public int delete(QnaDTO qnaDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"delete", qnaDTO);
	}
	
	public int updateHit(QnaDTO qnaDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"updateHit", qnaDTO);
	}
	
	public int reply(QnaDTO qnaDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"reply", qnaDTO);
	}
	
	public int updateStep(QnaDTO qnaDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"updateStep", qnaDTO);
	}
	
	public int addFile(QnaFilesDTO qnaFilesDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"addFile", qnaFilesDTO);
	}
	
	public QnaFilesDTO getFileDetail(QnaFilesDTO qnaFilesDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getFileDetail", qnaFilesDTO);
	}
	
	public int deleteFile(QnaFilesDTO qnaFilesDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"deleteFile", qnaFilesDTO);
	}
	
	public int deleteFileAll(QnaDTO qnaDTO) throws Exception {
		return sqlSession.delete(NAMESPACE+"deleteFileAll", qnaDTO);
	}
}
