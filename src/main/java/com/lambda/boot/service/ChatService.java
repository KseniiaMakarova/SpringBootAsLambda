package com.lambda.boot.service;

import com.lambda.boot.model.Chat;

import java.util.List;

public interface ChatService {
    Chat createNewChat();

    List<Chat> getAll();

    Chat get(Integer id);
}
