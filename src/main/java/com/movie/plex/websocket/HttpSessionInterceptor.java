package com.movie.plex.websocket;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class HttpSessionInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, 
                                   ServerHttpResponse response, 
                                   WebSocketHandler wsHandler, 
                                   Map<String, Object> attributes) throws Exception {
      
		// 예: 요청 파라미터에서 chatRoomNo를 가져와서 넣음
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			String chatRoomNo = servletRequest.getServletRequest().getParameter("chatRoomNo");

			if (chatRoomNo != null) {
				attributes.put("chatRoomNo", Integer.parseInt(chatRoomNo));
			}
		}

		return true; // 핸드셰이크 계속 진행
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
							   WebSocketHandler wsHandler, Exception exception) {
		// do nothing
	}
}