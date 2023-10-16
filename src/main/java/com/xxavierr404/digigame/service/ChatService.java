package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.ChatRepository;
import com.xxavierr404.digigame.dao.LicenseRepository;
import com.xxavierr404.digigame.domain.Chat;
import com.xxavierr404.digigame.domain.License;
import com.xxavierr404.digigame.dto.ChatDto;
import com.xxavierr404.digigame.dto.LicenseDto;
import com.xxavierr404.digigame.dto.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository repository;
    private final ChatMapper mapper;

    public Chat create(ChatDto chatDto) {
        return repository.save(mapper.fromDto(chatDto));
    }

    public Optional<Chat> readOne(Long id) {
        return repository.findById(id);
    }

    public boolean update(Chat chat) {
        var searchResult = repository.findById(chat.getId());
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.save(chat);
        return true;
    }

    public boolean delete(Long id) {
        var searchResult = repository.findById(id);
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
