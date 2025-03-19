package com.movie.plex.users;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.movie.plex.users.UserDAO.";
	
	public UserDTO getLogin(UserDTO userDTO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getLogin", userDTO);
	}

}
