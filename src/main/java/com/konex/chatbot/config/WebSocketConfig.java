package com.konex.chatbot.config;

import com.konex.chatbot.controller.chatBot.ChatBotEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    public static ConcurrentMap<String, WebSocketSession> sessionConcurrentMap = new ConcurrentHashMap<String, WebSocketSession>();

    @Bean
    public WebSocketHandler chatHandler() {
        return new ChatBotEndpoint();
    }

    @Bean
    public HandshakeInterceptor interceptor() {
        return new Interceptor();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler(), "/chatbot").setAllowedOrigins("*");
    }
}