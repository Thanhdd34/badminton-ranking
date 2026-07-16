package com.thanh.badminton_ranking.authentication.dto.response;

import com.thanh.badminton_ranking.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private String username;
    private Role role;
    private String message;
}
