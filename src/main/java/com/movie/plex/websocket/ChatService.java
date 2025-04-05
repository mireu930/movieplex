package com.movie.plex.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.plex.Utils;

@Service
public class ChatService {
	@Autowired
	private ChatDAO chatDAO;
	
	public List<ChatRoom> chatRoomList() throws Exception {
		return chatDAO.chatRoomList();
	}
	
	public List<ChatRoom> getChatRoomJoin(ChatRoomJoin chatRoomJoin) throws Exception {
		return chatDAO.getChatRoomJoin(chatRoomJoin);
	}
	
	public int addChatRoom(ChatRoom chatRoom) throws Exception {
		return chatDAO.addChatRoom(chatRoom);
	}
	
	public List<ChatMessage> addChatRoomDetail(ChatRoomJoin chatRoomJoin) throws Exception {
		List<ChatMessage> list = null;
		
		Long addCheck = chatDAO.addCheck(chatRoomJoin);
		int result = 1;
		
		if(addCheck == 0) {
			 result = chatDAO.addChatRoomDetail(chatRoomJoin);			
		}
		
		if(result>0) {
			list = chatDAO.chatMessage(chatRoomJoin.getChatRoomNo());			
		}
		
		return list;
	}
	
	public int insertMessage(ChatMessage chatMessage) throws Exception {
		chatMessage.setMessage(Utils.XSSHandling(chatMessage.getMessage()));
		chatMessage.setMessage(Utils.newLineHandling(chatMessage.getMessage()));
		return chatDAO.insertMessage(chatMessage);
	}

}
