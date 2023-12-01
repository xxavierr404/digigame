package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.GameRepository;
import com.xxavierr404.digigame.domain.Game;
import com.xxavierr404.digigame.dto.GameDto;
import com.xxavierr404.digigame.dto.mapper.GameMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository repository;
    private final GameMapper mapper;

    public Game create(GameDto gameDto) {
        return repository.save(mapper.fromDto(gameDto));
    }

    public Optional<Game> findById(Long id) {
        return repository.findById(id);
    }

    public boolean update(Game game) {
        var searchResult = repository.findById(game.getId());
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.save(game);
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

    public List<Game> getAllGames() {
        return repository.findAll();
    }

    public List<Game> searchByName(String name) {
        return repository.findAllByGameNameContainingIgnoreCase(name);
    }
}
