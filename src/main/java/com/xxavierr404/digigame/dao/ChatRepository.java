package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
