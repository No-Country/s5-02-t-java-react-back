package com.s5.javaback.controller;

import com.s5.javaback.model.request.UserRequest;
import com.s5.javaback.model.response.ImageResponse;
import com.s5.javaback.model.response.UserResponse;
import com.s5.javaback.service.abstraction.ImageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imgService;

    @ApiOperation(value ="Crear turno", notes="Crea un turno y lo asigna a una casa",
            response = ResponseEntity.class)
    @PostMapping
    public ResponseEntity<ImageResponse> imageUp(@RequestPart ( value="image",required=false) MultipartFile image) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(imgService.imageUp(image));
    }
}
