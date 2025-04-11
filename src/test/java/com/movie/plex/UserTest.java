package com.movie.plex;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.movie.plex.movies.MovieDTO;
import com.movie.plex.movies.MovieJson;
import com.movie.plex.users.UserDAO;
import com.movie.plex.users.UserDTO;

public class UserTest extends SampleTestCase{
	
	@Autowired
	private UserDAO userDAO;
	
	@Test
	public void userCouponTest() throws Exception{
		assertNotNull(userDAO);
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId("sss");
		
//		List<UserDTO> list = userDAO.couponList(userDTO);

//		assertNotEquals(0, list.size());
	}

}
