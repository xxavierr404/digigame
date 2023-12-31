package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.UserContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContentRepository extends JpaRepository<UserContent, Long> {
}
