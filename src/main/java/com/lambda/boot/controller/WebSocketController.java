package com.lambda.boot.controller;

import com.lambda.boot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @Autowired
    private ChatService chatService;

    @MessageMapping("/create")
    @SendTo("/chat")
    public String createChat() {
        chatService.createNewChat();
        return "success from web socket!";
    }
}
