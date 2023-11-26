package com.xxavierr404.digigame.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/")
    public String indexPage(Authentication authentication, Model model) {
        model.addAttribute("user",
                authentication != null
                        ? authentication.getPrincipal()
                        : null
        );

        return "index";
    }
}
