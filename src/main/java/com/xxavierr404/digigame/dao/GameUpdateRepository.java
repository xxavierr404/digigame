package com.xxavierr404.digigame.dao;

import com.xxavierr404.digigame.domain.GameUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUpdateRepository extends JpaRepository<GameUpdate, Long> {
}
