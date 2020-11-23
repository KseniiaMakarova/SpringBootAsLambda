package com.lambda.boot.controller;

import com.lambda.boot.model.Chat;
import com.lambda.boot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;

    @RequestMapping(path = "/chat", method = RequestMethod.POST)
    public String createChat() {
        chatService.createNewChat();
        return "new chat was created!";
    }

    @RequestMapping(path = "/chat", method = RequestMethod.GET)
    public List<Chat> getChats() {
        return chatService.getAll();
    }
}
