package com.lambda.boot.service;

import com.lambda.boot.model.Chat;

import java.util.List;

public interface ChatService {
    Chat save(Chat chat);

    List<Chat> getAll();

    Chat get(Integer id);
}
