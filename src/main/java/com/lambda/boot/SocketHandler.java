package com.lambda.boot;

import com.lambda.boot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {
    @Autowired
    private ChatService chatService;

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    int messagecount = 0;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws InterruptedException, IOException {

        // parse message
//        String value = message.getPayload();
//        Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);
            messagecount++;
            System.out.println(messagecount);
            chatService.createNewChat();
            session.sendMessage(new TextMessage(String.valueOf(messagecount)));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }
}
