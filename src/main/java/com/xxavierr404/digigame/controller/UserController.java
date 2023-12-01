package com.xxavierr404.digigame.controller;

import com.xxavierr404.digigame.domain.License;
import com.xxavierr404.digigame.domain.User;
import com.xxavierr404.digigame.service.LicenseService;
import com.xxavierr404.digigame.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final LicenseService licenseService;

    public UserController(UserService userService, LicenseService licenseService) {
        this.userService = userService;
        this.licenseService = licenseService;
    }

    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            User user = userService.findByUsername(username);
            List<License> userLicenses = licenseService.findByUserId(user.getId());

            model.addAttribute("user", user);
            model.addAttribute("userLicenses", userLicenses);
        }
        return "profile";
    }

    @PostMapping("/update-nickname")
    public String updateNickname(@RequestParam String newNickname, Authentication authentication) {
        var user = ((User) authentication.getPrincipal());
        user.setNickname(newNickname);
        userService.update(user);
        return "redirect:/user/profile";
    }
}
