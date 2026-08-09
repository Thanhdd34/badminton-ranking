package com.thanh.badminton_ranking.user.service;

import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.authentication.dto.response.RegisterResponse;
import com.thanh.badminton_ranking.common.enums.Role;
import com.thanh.badminton_ranking.user.entity.User;
import com.thanh.badminton_ranking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public RegisterResponse register(RegisterRequest request) {

        // kiem tra username ton tai chua
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        //tao user
        User user = new User();

        // set de lieu user
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setEnabled(true);

        //luu vao database
        User savedUser = userRepository.save(user);

        //tao reponse
        RegisterResponse response = new RegisterResponse();
        response.setUsername(savedUser.getUsername());
        response.setRole(savedUser.getRole());
        response.setEnabled(true);
        response.setCreatedAt(savedUser.getCreatedAt());
        return response;
    }
}
