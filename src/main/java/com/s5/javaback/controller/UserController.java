package com.s5.javaback.controller;

import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.service.abstraction.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @ApiOperation(value = "GetAll", notes = "Devuelve todos los usuarios", response = ResponseEntity.class)
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(service.getAll(status));
    }

    @ApiOperation(value = "GetById", notes = "Busca un usuario por id", response = ResponseEntity.class)
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") long id) {
        return ResponseEntity.of(service.getById(id));
    }

    @ApiOperation(value = "Update", notes = "Actualiza un usuario", response = ResponseEntity.class)
    @PatchMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestPart(value="image", required=false) MultipartFile image,
                                               @RequestPart(value="user") @Valid UserRequest request) throws Exception {
        return ResponseEntity.of(service.update(request,image));
    }

    @ApiOperation(value = "Borrar", notes = "Borra un usuario por id", response = ResponseEntity.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById() throws Exception {
        service.delete();

        return ResponseEntity.ok().body("El usuario ha sido deshabilitado");
    }

}
