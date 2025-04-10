
package com.movie.plex.websocket;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.movie.plex.users.UserDTO;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;

	
	@GetMapping(value = "/chatRoom")
	public String chatRoomList(Model model, HttpSession session, ChatRoomJoin chatRoomJoin) throws Exception {
		
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		
		List<ChatRoom> list = null;
		
		String str ="";
		
		if(userDTO == null || userDTO.getUserId() == null) {
			model.addAttribute("result", "로그인이필요합니다.");
			model.addAttribute("path", "/users/login");
			str = "commons/result";
		}else {
			if(userDTO.getUserGrade()==4) {
				list = chatService.chatRoomList();
			} else {
				chatRoomJoin.setUserNum(userDTO.getUserNum());
				list = chatService.getChatRoomJoin(chatRoomJoin);
				
			}
			model.addAttribute("list", list);
			model.addAttribute("user", userDTO);	
			
			str = "chat/chatRoom";
		}
		
		return str;
	}
	
	@PostMapping(value = "/addChatRoom")
	public String addChatRoom(ChatRoom chatRoom, HttpSession session, Model model, ChatRoomJoin chatRoomJoin) throws Exception {
	
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		chatRoom.setUserNum(userDTO.getUserNum());
		
		int result = chatService.addChatRoom(chatRoom);
		
		if(result>0) {
			chatRoomJoin.setUserNum(userDTO.getUserNum());
			chatRoomJoin.setChatRoomNo(chatRoom.getChatRoomNo());
			chatService.addChatRoomDetail(chatRoomJoin);
			
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
		return "chat/chatRoomDetail";
	}
	
	@GetMapping(value = "/exit")
	public String exitChatRoom(ChatRoomJoin chatRoomJoin, HttpSession session) throws Exception {
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		
		chatRoomJoin.setUserNum(userDTO.getUserNum());
		chatRoomJoin.setChatRoomNo(chatRoomJoin.getChatRoomNo());
		
		chatService.exitChatRoom(chatRoomJoin);
		
		return "redirect:./chatRoom";
	}
}
