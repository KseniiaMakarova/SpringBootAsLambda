package com.lambda.boot.service;

import com.lambda.boot.model.Chat;
import com.lambda.boot.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createNewChat() {
        Chat chat = new Chat();
        chat.setText("text " + new Random().nextInt(100));
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @Override
    public Chat get(Integer id) {
        return chatRepository.getOne(id);
    }
}
