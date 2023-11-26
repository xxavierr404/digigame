package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.MessageRepository;
import com.xxavierr404.digigame.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repository;

    public List<Message> getMessagesByChatId(Long chatId) {
        return repository.findAllByChatId(chatId);
    }
}
