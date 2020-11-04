package com.lambda.boot.controller;

import com.lambda.boot.model.Chat;
import com.lambda.boot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import java.util.List;
import java.util.Random;

@RestController
@EnableWebMvc
public class ChatController {
    @Autowired
    private ChatService chatService;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String createChat() {
        chatService.save(new Chat().setText("text " + new Random().nextInt(100)));
        return "success!";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Chat> getChats() {
        return chatService.getAll();
    }
}
