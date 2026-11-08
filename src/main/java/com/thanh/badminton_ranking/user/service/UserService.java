package com.thanh.badminton_ranking.user.service;

import com.thanh.badminton_ranking.authentication.dto.request.LoginRequest;
import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.authentication.dto.response.LoginResponse;
import com.thanh.badminton_ranking.authentication.dto.response.RegisterResponse;


public interface UserService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
