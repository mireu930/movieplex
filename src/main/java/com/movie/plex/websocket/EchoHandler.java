package com.movie.plex.websocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.*;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.movie.plex.users.UserDTO;

@Component
@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler{
	
	private List<WebSocketSession> adminSessions = new ArrayList<WebSocketSession>();
	 private List<WebSocketSession> userSessions = new ArrayList<WebSocketSession>();  

	 private static final Logger logger = LoggerFactory.getLogger(EchoHandler.class);
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> attributes = session.getAttributes();
	    UserDTO user = (UserDTO) attributes.get("user"); // 세션에서 유저 정보 가져오기
	    
        if (user.getUserGrade()== null || user.getUserGrade() < 1) {
            session.close(CloseStatus.NOT_ACCEPTABLE);
            return;
        }

        System.out.println("WebSocket 연결됨: userGrade=" + user.getUserGrade());
	    }


	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Map<String, Object> attributes = session.getAttributes();
	    UserDTO user = (UserDTO) attributes.get("user");

	    if (user == null) {
	        logger.warn("유저 정보를 찾을 수 없음. 세션 ID: {}", session.getId());
	        return;
	    }

	    try {
	        // 🔴 기존 코드 (오류 발생 가능)
	        // logger.info(user.getUserName(), session.getId(), message.getPayload());

	    	String logMessage = String.format("%s(%s)로부터 메시지 수신: %s", user.getUserName(), session.getId(), message.getPayload());
	    	logger.info(logMessage);
	    } catch (Exception e) {
	        logger.error("메시지 처리 중 오류 발생", e);
	    }

	    // 관리자가 아닌 경우 → 관리자들에게 메시지 전달
	    if (user.getUserGrade() != 4) {
	        for (WebSocketSession adminSession : adminSessions) {
	            if (adminSession.isOpen()) {
	                adminSession.sendMessage(new TextMessage("[사용자] " + user.getUserName() + ": " + message.getPayload()));
	            }
	        }
	    }
	    // 관리자가 보낸 메시지는 모든 사용자에게 전달
	    else {
	        for (WebSocketSession userSession : userSessions) {
	            if (userSession.isOpen()) {
	                userSession.sendMessage(new TextMessage("[관리자] " + user.getUserName() + ": " + message.getPayload()));
	            }
	        }
	    }
	    }
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	    Map<String, Object> attributes = session.getAttributes();
	    UserDTO user = (UserDTO) attributes.get("user");

	    if (user != null) {
	        if (user.getUserGrade() == 4) {
	            adminSessions.remove(session);
	            logger.info("관리자 {}({}) 연결 종료", user.getUserName(), session.getId());
	        } else {
	            userSessions.remove(session);
	            logger.info("사용자 {}({}) 연결 종료", user.getUserName(), session.getId());
	        }
	    } else {
	        logger.info("세션 {} 연결 종료", session.getId());
	    }
	}
}
