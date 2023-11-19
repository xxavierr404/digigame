package com.xxavierr404.digigame.controller;

import com.xxavierr404.digigame.domain.ContentType;
import com.xxavierr404.digigame.domain.User;
import com.xxavierr404.digigame.dto.UserContentDto;
import com.xxavierr404.digigame.service.UserContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/games/{gameId}/user-content")
@RequiredArgsConstructor
public class UserContentController {

    private final UserContentService userContentService;

    @PostMapping
    public String addUserContent(
            @PathVariable Long gameId,
            @RequestParam ContentType contentType,
            @RequestParam String contentUrl,
            Authentication authentication
    ) {
        var userId = ((User) authentication.getPrincipal()).getId();

        var userContent = new UserContentDto();
        userContent.setUserId(userId);
        userContent.setGameId(gameId);
        userContent.setContentType(contentType);
        userContent.setContentUrl(contentUrl);

        userContentService.create(userContent);

        return "redirect:/games/{gameId}/user-content";
    }
}
