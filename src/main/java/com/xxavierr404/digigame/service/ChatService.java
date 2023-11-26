package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.ChatMemberRepository;
import com.xxavierr404.digigame.dao.ChatRepository;
import com.xxavierr404.digigame.dao.LicenseRepository;
import com.xxavierr404.digigame.dao.UserRepository;
import com.xxavierr404.digigame.domain.Chat;
import com.xxavierr404.digigame.domain.ChatMember;
import com.xxavierr404.digigame.domain.License;
import com.xxavierr404.digigame.domain.User;
import com.xxavierr404.digigame.dto.ChatDto;
import com.xxavierr404.digigame.dto.LicenseDto;
import com.xxavierr404.digigame.dto.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository repository;
    private final UserRepository userRepository;
    private final ChatMapper mapper;

    @Transactional
    public void create(ChatDto chatDto, Long userId) {
        var chat = mapper.fromDto(chatDto);
        var savedChat = repository.save(chat);
        invite(userId, savedChat.getId());
    }

    @Transactional
    public void invite(Long userId, Long chatId) {
        var user = userRepository.findById(userId).orElseThrow();
        var chat = repository.findById(chatId).orElseThrow();
        user.getChats().add(repository.findById(chatId).orElseThrow());
        userRepository.save(user);
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
