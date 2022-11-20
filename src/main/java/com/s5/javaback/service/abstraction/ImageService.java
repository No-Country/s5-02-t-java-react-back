package com.s5.javaback.service.abstraction;

import java.util.List;
import java.util.Optional;

import com.s5.javaback.model.request.ImageRequest;
import com.s5.javaback.model.response.ImageResponse;

public interface ImageService {
  List<ImageResponse> createHouseImages(Long entertainamentHomeId, List<ImageRequest> images) throws Exception;
  
  ImageResponse createUserImage(Long userId, ImageRequest image) throws Exception;

  List<ImageResponse> getByHomeId(Long homeId);

  Optional<ImageResponse> getByUserId(Long userId);

  boolean delete(Long imageId);

  boolean deleteByUserId(Long userId);

  boolean deleteByEntertainamentHomeId(Long entertainamentHomeId);
}
