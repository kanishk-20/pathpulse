package com.pathpulse.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {


    @NotBlank(message="Name is required")
    @Size(min=3, max=100)
    private String name;

    @NotBlank(message="Email is required")
    @Email(message="Invalid Email")
    private String email;

    @NotBlank(message="Password is required")
    @Size(min=6, max=100)
    private String password;
}
