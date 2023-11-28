package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByGameNameLikeIgnoreCase(String regex);
}
