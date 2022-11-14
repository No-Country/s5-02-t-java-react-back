package com.s5.javaback.controller;

import com.s5.javaback.model.request.EntertainamentHomeRequest;
import com.s5.javaback.service.abstraction.EntertainamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entertainamentHome")
@CrossOrigin("*")
public class EntertainamentHomoController {
@Autowired
    EntertainamentService service ;

    @PostMapping("/create")
    public ResponseEntity<?>create(@Valid @RequestBody EntertainamentHomeRequest request) throws Exception {
             ResponseEntity<?>  response =        service.create(request);
        return new ResponseEntity(response.getBody(), response.getStatusCode());
    }
}
