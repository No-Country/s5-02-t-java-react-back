package com.s5.javaback.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.s5.javaback.model.entity.Image;
import com.s5.javaback.model.request.ImageRequest;
import com.s5.javaback.model.response.ImageResponse;

@Mapper(componentModel = "spring")
public interface ImageMapper {
  // Entidad to Response
  ImageResponse toResponse(Image image);
  List<ImageResponse> toResponseList(List<Image> images);

  // Petition to Entity 
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "entertainamentHome", ignore = true)
  Image toEntity(ImageRequest image);
  
  List<Image> toEntityList(List<ImageRequest> images);
  
}
