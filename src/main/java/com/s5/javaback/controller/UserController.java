package com.s5.javaback.controller;

import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@CrossOrigin("*")

public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(service.getAll(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") long id) {
        return ResponseEntity.of(service.getById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateById(@PathVariable("id") long id, @RequestBody UserRequest request) throws Exception {
        return ResponseEntity.of(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id) throws Exception {
        service.delete(id);

        return ResponseEntity.ok().body("El usuario ha sido deshabilitado");
    }

}
