package com.datagear.amlserver.rest.auth;

import com.datagear.amlserver.entity.auth.LoginRequest;
import com.datagear.amlserver.entity.auth.LoginResponse;
import com.datagear.amlserver.entity.auth.RegisterRequest;
import com.datagear.amlserver.entity.auth.User;
import com.datagear.amlserver.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(authenticationService.findAllUser());
    }
    @PostMapping("/reqister")
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
