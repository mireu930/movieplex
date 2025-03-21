package com.movie.plex.nestcontents;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NestContentDAO {
	
	@Autowired
	private SqlSession sqlSession;
	 
	private final String NAMESPACE = "com.movie.plex.nestcontents.NestContentDAO.";
	
	public int addJsonList(List<NestContentDTO> nestContentDTOs ) throws Exception {
		return sqlSession.insert(NAMESPACE+"addJsonList", nestContentDTOs);
	}

}
