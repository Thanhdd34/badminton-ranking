package com.thanh.badminton_ranking.user.controller;

import com.thanh.badminton_ranking.authentication.dto.request.LoginRequest;
import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.authentication.dto.response.LoginResponse;
import com.thanh.badminton_ranking.authentication.dto.response.RegisterResponse;
import com.thanh.badminton_ranking.user.dto.response.UserProfileResponse;
import com.thanh.badminton_ranking.user.entity.User;
import com.thanh.badminton_ranking.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/me")
    public UserProfileResponse getCurrentUser(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return new UserProfileResponse(
                user.getUsername(),
                user.getRole()
        );
    }

}
