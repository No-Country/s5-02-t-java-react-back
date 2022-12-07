package com.s5.javaback.controller;

import com.s5.javaback.model.request.AuthRequest;
import com.s5.javaback.model.request.AuthResponse;
import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.service.abstraction.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> login() throws Exception {
        return ResponseEntity.ok(service.isEnabled());
    }

    @ApiOperation(value = "Registro", notes = "Registra un Usuario", response = ResponseEntity.class)
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> register() throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create());
    }

}
