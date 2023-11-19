package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.UserContentRepository;
import com.xxavierr404.digigame.domain.UserContent;
import com.xxavierr404.digigame.dto.UserContentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserContentService {
    private final UserContentRepository repository;
    private final UserService userService;
    private final GameService gameService;

    public UserContent create(UserContentDto dto) {
        var userContent = new UserContent();
        userContent.setUser(userService.readOne(dto.getUserId()).orElseThrow());
        userContent.setContentType(dto.getContentType());
        userContent.setContentUrl(dto.getContentUrl());
        userContent.setGame(gameService.findById(dto.getGameId()).orElseThrow());
        return repository.save(userContent);
    }

    public Optional<UserContent> findById(Long id) {
        return repository.findById(id);
    }

    public boolean update(UserContent userContent) {
        var searchResult = repository.findById(userContent.getId());
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.save(userContent);
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
