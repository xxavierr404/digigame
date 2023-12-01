package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {

    @Query(value = "SELECT * FROM user_chat WHERE chat_id = :chatId", nativeQuery = true)
    List<ChatMember> findAllByChatId(@Param("chatId") Long chatId);

    @Query(value = "SELECT * FROM user_chat WHERE user_id = :userId", nativeQuery = true)
    List<ChatMember> findAllByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM user_chat WHERE user_id = :userId AND chat_id = :chatId", nativeQuery = true)
    Optional<ChatMember> findByUserIdAndChatId(@Param("userId") Long userId, @Param("chatId") Long chatId);

    @Query(value = "SELECT * FROM user_chat WHERE user_id = :userId AND state = :state", nativeQuery = true)
    List<ChatMember> findAllByUserIdAndState(@Param("userId") Long userId, @Param("state") String state);

    @Modifying
    @Query(value = "DELETE FROM user_chat WHERE user_id = :userId AND chat_id = :chatId", nativeQuery = true)
    void removeByUserIdAndChatId(@Param("userId") Long userId, @Param("chatId") Long chatId);
}
