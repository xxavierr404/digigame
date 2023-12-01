package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    Optional<User> getByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM users WHERE nickname = :nickname", nativeQuery = true)
    Optional<User> getByNickname(@Param("nickname") String nickname);
}
