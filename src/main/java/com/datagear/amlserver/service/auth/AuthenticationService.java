package com.datagear.amlserver.service.auth;

import com.datagear.amlserver.config.JwtService;
import com.datagear.amlserver.entity.auth.Role;
import com.datagear.amlserver.entity.auth.User;
import com.datagear.amlserver.dao.auth.UserRepository;
import com.datagear.amlserver.entity.auth.LoginRequest;
import com.datagear.amlserver.entity.auth.LoginResponse;
import com.datagear.amlserver.entity.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public List<User> findAllUser() {
        List<User> users = repository.findAll();
        return users;
    }

    public LoginResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .groups(request.getGroup())
                .role(Role.USER)
                .build();
        User createdUser = repository.save(user);
        System.out.println("User: " + createdUser);
        var jwtToken = jwtService.generateToken(createdUser);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .token(jwtToken)
                .build();
    }
}
