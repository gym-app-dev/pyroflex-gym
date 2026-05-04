package com.pyroflex.user.controller;

import com.pyroflex.user.dto.*;
import com.pyroflex.user.model.User;
import com.pyroflex.user.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/health")
    public String health() {
        return "User Service Running";
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return new LoginResponse(userService.login(request));
    }

    @GetMapping("/me")
    public String me(Authentication authentication) {
        return "Logged in user: " + authentication.getName();
    }
}