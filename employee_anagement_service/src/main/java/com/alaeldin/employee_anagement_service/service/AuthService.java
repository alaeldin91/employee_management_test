package com.alaeldin.employee_anagement_service.service;

import com.alaeldin.employee_anagement_service.dto.LoginRequest;
import com.alaeldin.employee_anagement_service.dto.RegisterRequest;
import com.alaeldin.employee_anagement_service.dto.UserResponse;
import com.alaeldin.employee_anagement_service.exception.ResourceNotFound;
import com.alaeldin.employee_anagement_service.mapper.UserMapper;
import com.alaeldin.employee_anagement_service.model.User;
import com.alaeldin.employee_anagement_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponse authenticate(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail()
                        , loginRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        assert userDetails != null;
        String token = jwtService.generateToken(userDetails);

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFound("Auth", "email", loginRequest.getEmail()));

        return UserResponse.builder().
                id(user.getId())
                .firstname(user.getFirstname())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .build();
    }

    public UserResponse register(RegisterRequest registerRequest) {

        User user = userMapper.toEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        User saveUser = userRepository.save(user);
        String token = jwtService.generateToken(saveUser);
        UserResponse userResponse = userMapper.toDto(saveUser);
        userResponse.setToken(token);

        return userResponse;
    }
}
