package com.s5.javaback.controller;

import com.s5.javaback.model.request.AuthRequest;
import com.s5.javaback.model.request.AuthResponse;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.security.jwt.JwtUtil;
import com.s5.javaback.security.service.UserDetailsServiceImpl;
import com.s5.javaback.service.abstraction.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @ApiOperation(value = "Login", notes = "Loguearse", response = ResponseEntity.class)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(final @RequestBody @Valid AuthRequest request) {
        return ResponseEntity.ok(service.authentication(request));
    }

    @ApiOperation(value = "Registro", notes = "Registra un Usuario", response = ResponseEntity.class)
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> register(final @RequestBody @Valid UserRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

}
