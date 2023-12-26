package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.ChatMemberRepository;
import com.xxavierr404.digigame.domain.ChatMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMemberService {
    private final ChatMemberRepository repository;

    public Optional<ChatMember> findByUserIdAndChatId(Long userId, Long chatId) {
        return repository.findByUserIdAndChatId(userId, chatId);
    }

    public List<ChatMember> findAllByUserIdAndState(Long userId, String state) {
        return repository.findAllByUserIdAndState(userId, state);
    }
}
