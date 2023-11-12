package com.xxavierr404.digigame.controller;

import com.xxavierr404.digigame.dto.GameDto;
import com.xxavierr404.digigame.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

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
}
