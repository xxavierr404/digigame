package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.MessageRepository;
import com.xxavierr404.digigame.domain.Chat;
import com.xxavierr404.digigame.domain.Message;
import com.xxavierr404.digigame.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repository;

    public List<Message> getMessagesByChatId(Long chatId) {
        return repository.findAllByChatId(chatId);
    }

    public Message create(Chat chat, User user, String text) {
        var message = new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setText(text);
        message.setSentTime(LocalDateTime.now());
        return repository.save(message);
    }
}
