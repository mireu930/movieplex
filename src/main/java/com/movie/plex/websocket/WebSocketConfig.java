package com.movie.plex.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
    private final EchoHandler echoHandler; 

    @Autowired
    public WebSocketConfig(EchoHandler echoHandler) {
        this.echoHandler = echoHandler; // EchoHandler를 주입받음
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(echoHandler, "/chatServer")
        		.addInterceptors(new HttpSessionInterceptor())
                .setAllowedOrigins("*") // CORS 허용
                .withSockJS(); // SockJS 지원
    }
}