package com.thanh.badminton_ranking.authentication.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Size(min = 5, max = 50)
    @NotBlank(message = "Username is required!")
    private String username;

    @Size(min = 5, max = 50)
    @NotBlank(message = "Password is required!")
    private String password;
}
