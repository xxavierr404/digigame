package com.xxavierr404.digigame.controller;

import com.xxavierr404.digigame.domain.User;
import com.xxavierr404.digigame.dto.GameDto;
import com.xxavierr404.digigame.dto.IssueReportDto;
import com.xxavierr404.digigame.dto.ReviewDto;
import com.xxavierr404.digigame.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final ReviewService reviewService;
    private final IssueReportService reportService;
    private final UserContentService contentService;
    private final UserService userService;

    @GetMapping("/games")
    public String getGames(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        model.addAttribute("gameDto", new GameDto());
        return "games";
    }

    @PostMapping("/games")
    public String addGame(GameDto gameDto) {
        gameService.create(gameDto);
        return "redirect:/games";
    }

    @GetMapping("/games/{gameId}")
    public String gameDetails(@PathVariable Long gameId, Authentication authentication, Model model) {
        var gameOpt = gameService.findById(gameId);
        var userId = ((User) authentication.getPrincipal()).getId();
        model.addAttribute("game", gameOpt.orElseThrow());
        model.addAttribute("owned", userService.hasGame(userId, gameId));
        return "game-details";
    }

    @PostMapping("/games/{gameId}/reviews")
    public String addReview(
            @PathVariable Long gameId,
            @RequestParam String text,
            Authentication authentication
    ) {
        var review = new ReviewDto();
        review.setGameId(gameId);
        review.setReviewText(text);
        review.setUserId(((User) authentication.getPrincipal()).getId());
        reviewService.create(review);
        return "redirect:/games/{gameId}";
    }

    @PostMapping("/games/{gameId}/reports")
    public String addIssueReport(
            @PathVariable Long gameId,
            @RequestParam String reportText,
            Authentication authentication
    ) {
        var report = new IssueReportDto();
        report.setGameId(gameId);
        report.setReportText(reportText);
        report.setUserId(((User) authentication.getPrincipal()).getId());
        reportService.create(report);
        return "redirect:/games/{gameId}";
    }

    @GetMapping("/games/{gameId}/purchase")
    public String purchaseGame(
            @PathVariable Long gameId,
            Authentication authentication
    ) {
        var userId = ((User) authentication.getPrincipal()).getId();
        if (!userService.hasGame(userId, gameId)) {
            userService.addGame(
                    userId,
                    gameId
            );
        }
        return "redirect:/games/{gameId}";
    }

    @GetMapping("/games/{gameId}/user-content")
    public String gameContent(@PathVariable Long gameId, Model model) {
        var game = gameService.findById(gameId).orElseThrow();
        model.addAttribute("game", game);
        return "game-content";
    }
}
