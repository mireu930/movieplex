package com.movie.plex.websocket;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class HttpSessionInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, 
                                   ServerHttpResponse response, 
                                   WebSocketHandler wsHandler, 
                                   Map<String, Object> attributes) throws Exception {
        // HttpServletRequest로 변환
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpRequest = servletRequest.getServletRequest();
            HttpSession session = httpRequest.getSession(false);  // 세션 가져오기 (없으면 null)

            if (session != null) {
            	String userName = (String) session.getAttribute("userName");
                Integer userGrade = (Integer) session.getAttribute("userGrade");// "user" 속성 가져오기

                attributes.put("userName", userName);
                attributes.put("userGrade", userGrade);
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, 
                               ServerHttpResponse response, 
                               WebSocketHandler wsHandler, 
                               Exception exception) {
        // 핸드셰이크 후 별도 처리 필요 없음
    }
}