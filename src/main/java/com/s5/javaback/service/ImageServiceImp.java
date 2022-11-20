package com.s5.javaback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.s5.javaback.mapper.ImageMapper;
import com.s5.javaback.model.request.ImageRequest;
import com.s5.javaback.model.response.ImageResponse;
import com.s5.javaback.repository.EntertainamentHomeRepository;
import com.s5.javaback.repository.IUserRepository;
import com.s5.javaback.repository.ImageRepository;
import com.s5.javaback.service.abstraction.ImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {

  private final ImageRepository imageRepository;
  private final IUserRepository userRepository;
  private final EntertainamentHomeRepository entertainamentHomeRepository;
  private final ImageMapper imageMapper;

  @Override
  public List<ImageResponse> getByHomeId(Long homeId) {
    return imageMapper.toResponseList(imageRepository.findByEntertainamentHomeId(homeId));
  }

  @Override
  public Optional<ImageResponse> getByUserId(Long userId) {
    return imageRepository.findByUserId(userId).map(image -> imageMapper.toResponse(image));
  }

  @Override
  public boolean delete(Long imageId) {
    try {
      imageRepository.deleteById(imageId);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean deleteByUserId(Long userId) {
    try {
      imageRepository.deleteByUserId(userId);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  @Override
  public boolean deleteByEntertainamentHomeId(Long entertainamentHomeId) {
    try {
      imageRepository.deleteByEntertainamentHomeId(entertainamentHomeId);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public List<ImageResponse> createHouseImages(Long entertainamentHomeId, List<ImageRequest> images) throws Exception {
    var home = entertainamentHomeRepository.findById(entertainamentHomeId).orElseThrow();
    home.addImages(imageMapper.toEntityList(images));

    return imageMapper.toResponseList(imageRepository.saveAll(imageMapper.toEntityList(images)));
  }

  @Override
  public ImageResponse createUserImage(Long userId, ImageRequest image) throws Exception {
    var user = userRepository.findById(userId).orElseThrow();
    user.setImage(imageMapper.toEntity(image));
    
    return imageMapper.toResponse(imageRepository.save(imageMapper.toEntity(image)));    
  }
  
}
