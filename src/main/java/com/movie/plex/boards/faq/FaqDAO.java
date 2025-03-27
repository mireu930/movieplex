package com.movie.plex.boards.faq;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.plex.boards.qna.QnaDTO;

@Repository
public class FaqDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.movie.plex.boards.faq.FaqDAO.";
	
	public List<FaqDTO> getList() throws Exception {
		return sqlSession.selectList(NAMESPACE+"getList");
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
}
