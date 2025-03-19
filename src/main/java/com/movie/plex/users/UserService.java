package com.movie.plex.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	public UserDTO getLogin(UserDTO userDTO) throws Exception {
		UserDTO result = userDAO.getLogin(userDTO);
		
		if(result != null) {
			if(result.getUserPw().equals(userDTO.getUserPw())) {
				return result;
			}
		}
		
		return null;
	}
}
