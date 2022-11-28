package com.s5.javaback.controller;

import com.s5.javaback.model.request.HomeEventRequest;
import com.s5.javaback.model.response.HomeEventResponse;
import com.s5.javaback.service.abstraction.HomeEventService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entertainamentHome")
@CrossOrigin("*")
public class HomeEventController {
@Autowired
HomeEventService service ;

    @ApiOperation(value ="Crear", notes="Crea una casa",
            response = ResponseEntity.class)
    @PostMapping("/create")
    public ResponseEntity<?>create(@Valid @RequestBody HomeEventRequest request) throws Exception {
             ResponseEntity<?>  response = service.create(request);
        return new ResponseEntity(response.getBody(), response.getStatusCode());
    }

    @ApiOperation(value ="Update", notes="Actualiza una casa",
            response = ResponseEntity.class)
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@Valid @PathVariable Long id ,  @RequestBody HomeEventRequest request) throws Exception {

        ResponseEntity<?>  response = service.update(id,request);
        return new ResponseEntity(response.getBody(), response.getStatusCode());
    }

    @ApiOperation(value ="FindById", notes="Busca una casa por id",
            response = ResponseEntity.class)
    @GetMapping("/find/{id}")
    public ResponseEntity<?>findById(@Valid @PathVariable Long id ) throws Exception {
        ResponseEntity<?>  response = service.getById(id);
        return new ResponseEntity(response.getBody(), response.getStatusCode());
    }

    @ApiOperation(value = "getByName", notes = "Busca una casa por nombre y las devuelve",
    response = ResponseEntity.class)
    @GetMapping("/name")
    public ResponseEntity<List<HomeEventResponse>> getByName(@RequestParam String name){
        List<HomeEventResponse> responses = service.getHomeByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}
