package com.thanh.badminton_ranking.user.service;

import com.thanh.badminton_ranking.authentication.dto.request.RegisterRequest;
import com.thanh.badminton_ranking.authentication.dto.response.RegisterResponse;
import com.thanh.badminton_ranking.common.enums.Role;
import com.thanh.badminton_ranking.exception.UsernameAlreadyExistsException;
import com.thanh.badminton_ranking.user.entity.User;
import com.thanh.badminton_ranking.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void register_sholdSuccess_whenUsernameNotExists() {

        /* kiểm tra repository*/
        //Arrange
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("thanh123");
        registerRequest.setPassword("12345678");

        when(userRepository.findByUsername("thanh123")).thenReturn(Optional.empty());

        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("hashed-password");

        //tao user giả để giả lập đưa vào db
        User savedUser = new User();
        savedUser.setUsername("thanh123");
        savedUser.setPassword("hashed-password");
        savedUser.setRole(Role.USER);
        savedUser.setEnabled(true);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        //Act
        RegisterResponse registerResponse = userServiceImpl.register(registerRequest);
        assertNotNull(registerResponse);


        //asset
        assertNotNull(registerResponse);
        assertEquals("thanh123",registerResponse.getUsername());

        assertEquals(Role.USER,registerResponse.getRole());

        assertEquals(true, registerResponse.getEnabled());

        /* kiểm tra trong service */
        verify(userRepository).findByUsername("thanh123");
        verify(passwordEncoder).encode("12345678");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_shouldThrowException_whenUsernameExists() {

        // arrange
        RegisterRequest registerRequest = new RegisterRequest();
        when(userRepository.findByUsername("thanh123")).thenReturn(Optional.of(new User()));

        registerRequest.setUsername("thanh123");
        registerRequest.setPassword("12345678");

        UsernameAlreadyExistsException exception = assertThrows(
                UsernameAlreadyExistsException.class,
                () -> userServiceImpl.register(registerRequest)
        );

        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));

        ArgumentCaptor<User> userCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository,never()).save(any(User.class));
    }
}
