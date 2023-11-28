package com.xxavierr404.digigame.controller;

import com.xxavierr404.digigame.domain.User;
import com.xxavierr404.digigame.dto.GameDto;
import com.xxavierr404.digigame.dto.GameUpdateDto;
import com.xxavierr404.digigame.dto.IssueReportDto;
import com.xxavierr404.digigame.dto.ReviewDto;
import com.xxavierr404.digigame.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final GameUpdateService gameUpdateService;
    private final ReviewService reviewService;
    private final IssueReportService reportService;
    private final UserService userService;

    @GetMapping("/games")
    public String getGames(Authentication authentication, Model model) {
        model.addAttribute("games", gameService.getAllGames());
        model.addAttribute("gameDto", new GameDto());
        model.addAttribute("user", authentication.getPrincipal());
        return "games";
    }

    @PostMapping("/games")
    public String addGame(GameDto gameDto) {
        gameService.create(gameDto);
        return "redirect:/games";
    }

    @GetMapping("/games/{gameId}")
    public String gameDetails(@PathVariable Long gameId, Authentication authentication, Model model) {
        var game = gameService.findById(gameId).orElseThrow();
        var userId = ((User) authentication.getPrincipal()).getId();
        model.addAttribute("game", game);
        model.addAttribute("owned", userService.hasGame(userId, gameId));
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("hasCover", game.getCoverImage() != null);
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

    @PostMapping("/games/{gameId}/updates")
    public String addUpdate(@PathVariable Long gameId, @RequestParam String updateUrl) {
        var update = new GameUpdateDto();
        update.setGameId(gameId);
        update.setUpdateUrl(updateUrl);

        gameUpdateService.create(update);

        return "redirect:/games/{gameId}";
    }

    @GetMapping("/games/{gameId}/user-content")
    public String gameContent(@PathVariable Long gameId, Model model) {
        var game = gameService.findById(gameId).orElseThrow();
        model.addAttribute("game", game);
        return "game-content";
    }

    @PostMapping("/games/{gameId}/edit")
    public String editGameDetails(
            @PathVariable Long gameId,
            @RequestParam String description,
            @RequestParam("coverImage") MultipartFile coverImage
    ) {
        var game = gameService.findById(gameId).orElseThrow();
        game.setDescription(description);

        try {
            byte[] coverImageData = coverImage.getBytes();
            game.setCoverImage(coverImageData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameService.update(game);

        return "redirect:/games/" + gameId;
    }

    @GetMapping("/games/{gameId}/coverImage")
    @ResponseBody
    public ResponseEntity<byte[]> getGameCoverImage(@PathVariable Long gameId) {
        var game = gameService.findById(gameId).orElseThrow();

        var coverImage = game.getCoverImage();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(coverImage, headers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public String searchGames(@RequestParam(name = "search", required = false) String search, Model model) {
        if (search == null) {
            return "redirect:/games";
        }

        var searchResults = gameService.searchByName(search);

        model.addAttribute("games", searchResults);

        return "games";
    }
}
