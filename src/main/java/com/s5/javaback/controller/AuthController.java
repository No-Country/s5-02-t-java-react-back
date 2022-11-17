package com.s5.javaback.controller;

import com.s5.javaback.model.request.AuthRequest;
import com.s5.javaback.model.request.AuthResponse;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.security.jwt.JwtUtil;
import com.s5.javaback.security.service.UserDetailsServiceImpl;
import com.s5.javaback.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
    private final AuthenticationManager manager;
    private final JwtUtil jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(final @RequestBody AuthRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));

            final var userId = service.getByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                    .map(UserResponse::getId)
                    .orElseThrow();

            final var userDetails = userDetailsService.loadUserByUsername(request.getUsernameOrEmail());
            final var jwt = jwtUtils.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(userId, jwt));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> register(final @RequestBody UserRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

}
