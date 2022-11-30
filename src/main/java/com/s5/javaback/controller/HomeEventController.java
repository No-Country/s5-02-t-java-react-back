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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/home_event")
@CrossOrigin("*")
public class HomeEventController {
@Autowired
HomeEventService service ;

    @ApiOperation(value ="add", notes="Crea una casa",
            response = ResponseEntity.class)
    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestPart(value="postimages", required = false) List<MultipartFile> postImage,
                                   @RequestPart(value = "home_event",required = true) @Valid HomeEventRequest request)
            throws Exception {
             ResponseEntity<?>  response = service.create(postImage, request);
        return new ResponseEntity(response.getBody(), response.getStatusCode());
    }
//value = "home_event", required = true
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
