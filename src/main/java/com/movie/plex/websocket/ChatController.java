
package com.movie.plex.websocket;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.plex.users.UserDTO;

@Controller
public class ChatController {

	private Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	@GetMapping(value = "/chat")
	public String chat2(Model model, HttpSession session) {
		logger.info("[Controller]: chat 페이지 진입");
		Object user = session.getAttribute("user");
		model.addAttribute("user", user);
		return "chat";
	}
}
