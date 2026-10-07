package com.thanh.badminton_ranking.user.service;

import com.thanh.badminton_ranking.authentication.dto.request.LoginRequest;
import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.authentication.dto.response.LoginResponse;
import com.thanh.badminton_ranking.authentication.dto.response.RegisterResponse;
import com.thanh.badminton_ranking.authentication.service.JwtService;
import com.thanh.badminton_ranking.common.enums.Role;
import com.thanh.badminton_ranking.exception.InvalidCredentialsException;
import com.thanh.badminton_ranking.exception.UserDisabledException;
import com.thanh.badminton_ranking.exception.UsernameAlreadyExistsException;
import com.thanh.badminton_ranking.user.entity.User;
import com.thanh.badminton_ranking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    @Override
    public RegisterResponse register(RegisterRequest request) {

        // kiem tra username ton tai chua
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
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

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        if(Boolean.FALSE.equals(user.getEnabled())) {
            throw new UserDisabledException("Account is not enabled");
        }

        String token = jwtService.generateToken(user);
        return new  LoginResponse(
                user.getUsername(),
                user.getRole(),
                token
        );
    }

}
