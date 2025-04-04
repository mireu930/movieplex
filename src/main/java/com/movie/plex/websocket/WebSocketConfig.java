package com.movie.plex.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
    private final EchoHandler echoHandler; // ✅ EchoHandler를 주입받음

    @Autowired
    public WebSocketConfig(EchoHandler echoHandler) {
        this.echoHandler = echoHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoHandler, "/echo")
                .addInterceptors(new HttpSessionInterceptor()) // 세션 정보 인터셉터 추가
                .setAllowedOrigins("http://localhost:80") // CORS 허용
                .withSockJS(); // SockJS 지원
    }
}