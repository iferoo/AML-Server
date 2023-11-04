package com.datagear.amlserver.jwt.rest;

import com.datagear.amlserver.jwt.entity.LoginRequest;
import com.datagear.amlserver.jwt.entity.LoginResponse;
import com.datagear.amlserver.jwt.entity.RegisterRequest;
import com.datagear.amlserver.jwt.entity.User;
import com.datagear.amlserver.jwt.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(authenticationService.findAllUser());
    }
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}
