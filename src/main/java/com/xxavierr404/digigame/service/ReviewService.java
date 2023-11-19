package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.ReviewRepository;
import com.xxavierr404.digigame.domain.Review;
import com.xxavierr404.digigame.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;
    private final GameService gameService;
    private final UserService userService;

    public Review create(ReviewDto dto) {
        var review = new Review();
        review.setGame(gameService.findById(dto.getGameId()).orElseThrow());
        review.setUser(userService.readOne(dto.getUserId()).orElseThrow());
        review.setText(dto.getReviewText());
        return repository.save(review);
    }

    public Optional<Review> readOne(Long id) {
        return repository.findById(id);
    }

    public boolean update(Review review) {
        var searchResult = repository.findById(review.getId());
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.save(review);
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
