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

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String createChat() {
        chatService.createNewChat();
        return "success!";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Chat> getChats() {
        return chatService.getAll();
    }
}
