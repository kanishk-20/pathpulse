package com.pathpulse.service.impl;

import Service.AuthService;
import com.pathpulse.dto.auth.AuthResponse;
import com.pathpulse.dto.auth.LoginRequest;
import com.pathpulse.dto.auth.RegisterRequest;
import com.pathpulse.entity.User;
import com.pathpulse.repository.UserRepository;
import com.pathpulse.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public void register(RegisterRequest request) {


        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        user.setPassword(hashedPassword);
        userRepository.save(user);
    }
    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow(() ->
                new RuntimeException("Invalid email or password")
        );

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        String token = jwtService.generateToken(
                user.getEmail()
        );

        return new AuthResponse(token);
    }
}
