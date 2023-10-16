package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.ChatMemberRepository;
import com.xxavierr404.digigame.dao.ChatRepository;
import com.xxavierr404.digigame.domain.Chat;
import com.xxavierr404.digigame.domain.ChatMember;
import com.xxavierr404.digigame.dto.ChatDto;
import com.xxavierr404.digigame.dto.ChatMemberDto;
import com.xxavierr404.digigame.dto.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMemberService {
    private final ChatMemberRepository repository;
    private final ChatService chatService;
    private final UserService userService;

    public ChatMember create(ChatMemberDto dto) {
        var chatMember = new ChatMember();
        chatMember.setChat(chatService.readOne(dto.getChatId()).orElseThrow());
        chatMember.setUser(userService.readOne(dto.getUserId()).orElseThrow());
        chatMember.setJoinTime(LocalDateTime.now());
        return repository.save(chatMember);
    }

    public List<ChatMember> getChatsOfUser(Long id) {
        return repository.findAllByUserId(id);
    }

    public List<ChatMember> getUsersOfChat(Long id) {
        return repository.findAllByChatId(id);
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
