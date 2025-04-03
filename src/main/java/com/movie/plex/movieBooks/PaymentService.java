package com.movie.plex.movieBooks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.movie.plex.users.UserDTO;

@Service
public class PaymentService {

	@Autowired
	private MovieBookService bookService;
	
	@Value("${import.channel}")
	private String importChannel;
	
	public Map<String, Object> movieBookInfo(List<String> seat, Long theaterId, UserDTO userDTO, Long totalPrice) throws Exception{
		Long bookId = bookService.movieBookInfo(seat, theaterId, userDTO, totalPrice);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("bookId", bookId);
		result.put("importChannel", importChannel);
		result.put("userName", userDTO.getUserName());
		result.put("userEmail", userDTO.getUserEmail());
		result.put("userPhone", userDTO.getUserPhone());
		return result;
	}
}
