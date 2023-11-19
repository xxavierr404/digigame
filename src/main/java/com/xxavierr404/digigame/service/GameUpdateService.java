package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.GameUpdateRepository;
import com.xxavierr404.digigame.domain.GameUpdate;
import com.xxavierr404.digigame.dto.GameUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameUpdateService {
    private final GameUpdateRepository repository;
    private final GameService gameService;

    public GameUpdate create(GameUpdateDto dto) {
        var gameUpdate = new GameUpdate();
        gameUpdate.setGame(gameService.findById(dto.getGameId()).orElseThrow());
        gameUpdate.setUpdateUrl(dto.getUpdateUrl());
        return repository.save(gameUpdate);
    }

    public Optional<GameUpdate> readOne(Long id) {
        return repository.findById(id);
    }

    public boolean update(GameUpdate gameUpdate) {
        var searchResult = repository.findById(gameUpdate.getId());
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.save(gameUpdate);
        return true;
    }

    public boolean delete(Long id) {
        var searchResult = repository.findById(id);
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
