package com.alaeldin.employee_anagement_service.controller;

import com.alaeldin.employee_anagement_service.dto.LoginRequest;
import com.alaeldin.employee_anagement_service.dto.RegisterRequest;
import com.alaeldin.employee_anagement_service.dto.UserResponse;
import com.alaeldin.employee_anagement_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController
{
    public final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest)
    {
        UserResponse userResponse = authService.authenticate(loginRequest);

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest)
    {
        UserResponse userResponse = authService.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
