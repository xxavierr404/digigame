package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    List<ChatMember> findAllByChatId(Long chatId);
    List<ChatMember> findAllByUserId(Long userId);
    Optional<ChatMember> findByUserIdAndChatId(Long userId, Long chatId);
    List<ChatMember> findAllByUserIdAndState(Long userId, String state);
    void removeByUserIdAndChatId(Long userId, Long chatId);
}
