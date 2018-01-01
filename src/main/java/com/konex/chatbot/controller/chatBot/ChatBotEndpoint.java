package com.konex.chatbot.controller.chatBot;

import com.konex.chatbot.model.Response;
import com.konex.chatbot.util.Util;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@SessionScope
public class ChatBotEndpoint extends TextWebSocketHandler {
    private Chatbot chatbot;

    public ChatBotEndpoint() {
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.chatbot = new Chatbot();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();

        if (!Util.isCommand(request))
            session.sendMessage(new TextMessage(Util.getResponseJSON(request, true, false, "", "")));//user

        for (Response response : chatbot.getResponse(request))
            session.sendMessage(new TextMessage(Util.getResponseJSON(response)));//bot
    }
}
