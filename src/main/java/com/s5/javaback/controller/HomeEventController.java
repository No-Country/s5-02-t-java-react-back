package com.s5.javaback.controller;

import com.s5.javaback.model.request.HomeEventRequest;
import com.s5.javaback.service.abstraction.HomeEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entertainamentHome")
@CrossOrigin("*")
public class HomeEventController {
@Autowired
HomeEventService service ;

    @PostMapping("/create")
    public ResponseEntity<?>create(@Valid @RequestBody HomeEventRequest request) throws Exception {
             ResponseEntity<?>  response = service.create(request);
        return new ResponseEntity(response.getBody(), response.getStatusCode());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@Valid @PathVariable Long id ,  @RequestBody HomeEventRequest request) throws Exception {

        ResponseEntity<?>  response = service.update(id,request);
        return new ResponseEntity(response.getBody(), response.getStatusCode());
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<?>findById(@Valid @PathVariable Long id ) throws Exception {
        ResponseEntity<?>  response = service.getById(id);
        return new ResponseEntity(response.getBody(), response.getStatusCode());
    }
}
