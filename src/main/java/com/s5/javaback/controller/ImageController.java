package com.s5.javaback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s5.javaback.model.request.ImageRequest;
import com.s5.javaback.model.response.ImageResponse;
import com.s5.javaback.service.abstraction.ImageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
  
  private final ImageService imageService;
 
  @PostMapping("/home/{homeId}")
  public ResponseEntity<List<ImageResponse>> createHouseImages(@PathVariable Long homeId, @RequestBody List<ImageRequest> images) throws Exception{
    return ResponseEntity.status(HttpStatus.CREATED).body(imageService.createHouseImages(homeId,images));
  }

  @PostMapping("/user/{userId}")
  public ResponseEntity<ImageResponse> createUserImage(@PathVariable Long userId, @RequestBody ImageRequest image) throws Exception{
    return ResponseEntity.status(HttpStatus.CREATED).body(imageService.createUserImage(userId, image));
  }

  @GetMapping("/home/{homeId}")
  public ResponseEntity<List<ImageResponse>> getByHomeId(@PathVariable Long homeId){
    return ResponseEntity.ok(imageService.getByHomeId(homeId));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<ImageResponse> getByUserId(@PathVariable Long userId){
    return ResponseEntity.of(imageService.getByUserId(userId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ImageResponse> delete(@PathVariable Long id){
    return imageService.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/home/{userId}")
  public ResponseEntity<ImageResponse> deleteByUserId(@PathVariable Long userId){
    return imageService.deleteByUserId(userId) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/user/{entertainamentId}")
  public ResponseEntity<ImageResponse> deleteByEntertainamentHomeId(@PathVariable Long entertainamentId){
    return imageService.deleteByEntertainamentHomeId(entertainamentId) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }

}
