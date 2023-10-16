package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.ChatMember;
import com.xxavierr404.digigame.service.ChatMemberService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    List<ChatMember> findAllByChatId(Long chatId);
    List<ChatMember> findAllByUserId(Long userId);
}
