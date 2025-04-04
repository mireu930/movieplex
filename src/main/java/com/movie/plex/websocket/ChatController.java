
package com.movie.plex.websocket;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movie.plex.users.UserDTO;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;

	private Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	@GetMapping(value = "/chat")
	public String chat2(Model model, HttpSession session) {
		logger.info("[Controller]: chat 페이지 진입");
		Object user = session.getAttribute("user");
		model.addAttribute("user", user);
		return "chat";
	}
	
	@GetMapping(value = "/chatRoom")
	public String chatRoomList(Model model) throws Exception {
		
		List<ChatRoom> list = chatService.chatRoomList();
		model.addAttribute("list", list);
		
		return "chatRoom";
	}
	
	@PostMapping(value = "/addChatRoom")
	public String addChatRoom(ChatRoom chatRoom, HttpSession session, Model model) throws Exception {
	
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		chatRoom.setUserNum(userDTO.getUserNum());
		
		int result = chatService.addChatRoom(chatRoom);
		
		if(result>0) {
			model.addAttribute("result", "방만들기성공");
			model.addAttribute("path", "./chatRoom");
		} else {
			model.addAttribute("result", "방만들기실패");
			model.addAttribute("path", "./chatRoom");
		}
		return "commons/result";
	}
	
	@GetMapping(value="/addChatRoomDetail")
	public String addChatRoomDetail(ChatRoomJoin chatRoomJoin, HttpSession session, Model model) throws Exception {
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		chatRoomJoin.setUserNum(userDTO.getUserNum());
		List<ChatMessage> list = chatService.addChatRoomDetail(chatRoomJoin);
		model.addAttribute("list", list);
		model.addAttribute("user", userDTO);
		model.addAttribute("chatRoomNo", chatRoomJoin.getChatRoomNo());
		return "chatRoomDetail";
	}
}
