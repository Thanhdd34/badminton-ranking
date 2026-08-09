package com.thanh.badminton_ranking.user.service;

import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.authentication.dto.response.RegisterResponse;


public interface UserService {
    RegisterResponse register(RegisterRequest request);
}
