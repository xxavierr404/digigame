package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(value = "SELECT * FROM game WHERE LOWER(game_name) LIKE LOWER(CONCAT('%', :regex, '%'))", nativeQuery = true)
    List<Game> findAllByGameNameContainingIgnoreCase(@Param("regex") String regex);
}
