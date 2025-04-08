package com.movie.plex.websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class EchoHandler extends TextWebSocketHandler{
	
	@Autowired
	private ChatService chatService;
	
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	 private static final Logger logger = LoggerFactory.getLogger(EchoHandler.class);

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("session ?? {}" + session.getId());
		sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("전달된메시지:{}",message.getPayload());
		
		ObjectMapper objectMapper = new ObjectMapper();
		ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);
		
		int result = chatService.insertMessage(chatMessage);
		
		if(result>0) {
			for(WebSocketSession s: sessions) {
				Integer  chatRoomNo = (Integer)s.getAttributes().get("chatRoomNo");
				
				if(chatMessage.getChatRoomNo() == chatRoomNo) {
					s.sendMessage(new TextMessage(new Gson().toJson(chatMessage)));
				}
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
	}
	
}
