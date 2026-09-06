package com.thanh.badminton_ranking.user.service;

import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.authentication.dto.response.RegisterResponse;
import com.thanh.badminton_ranking.exception.UsernameAlreadyExistsException;
import com.thanh.badminton_ranking.user.repository.UserRepository;


public interface UserService {


    RegisterResponse register(RegisterRequest request);
}
