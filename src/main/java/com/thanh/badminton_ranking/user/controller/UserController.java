package com.thanh.badminton_ranking.user.controller;

import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.authentication.dto.response.RegisterResponse;
import com.thanh.badminton_ranking.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

}
