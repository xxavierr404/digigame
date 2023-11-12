package com.xxavierr404.digigame.controller;

import com.xxavierr404.digigame.dto.UserRegisterDto;
import com.xxavierr404.digigame.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return "redirect:/login";
    }
}
