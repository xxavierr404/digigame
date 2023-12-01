package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM message WHERE chat_id = :chatId", nativeQuery = true)
    List<Message> findAllByChatId(@Param("chatId") Long chatId);
}
