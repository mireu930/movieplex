package com.movie.plex.boards.qna;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.plex.boards.faq.FaqDTO;

@Repository
public class QnaDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.movie.plex.boards.qna.QnaDAO.";
	
	public List<QnaDTO> getList() throws Exception {
		return sqlSession.selectList(NAMESPACE+"getList");
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
}
