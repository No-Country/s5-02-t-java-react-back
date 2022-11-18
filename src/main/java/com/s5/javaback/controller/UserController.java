package com.s5.javaback.controller;

import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
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

    @PutMapping("")
    public ResponseEntity<UserResponse> update(@RequestPart ( value="image",required=false) MultipartFile image,
                                               @RequestPart(value="user", required=true) UserRequest request) throws Exception {
        return ResponseEntity.of(service.update(request,image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id) throws Exception {
        service.delete(id);

        return ResponseEntity.ok().body("El usuario ha sido deshabilitado");
    }

}
