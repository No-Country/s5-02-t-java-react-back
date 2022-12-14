package com.s5.javaback.mapper;

import com.s5.javaback.model.entity.Image;
import com.s5.javaback.model.response.ImageResponse;
import org.springframework.stereotype.Component;



@Component
public class ImageMapper {
    public Image updateImageMapper(Image img, Image newImage) {
       img.setId(newImage.getId());
       img.setImageUrl(newImage.getImageUrl());
       img.setFileName(newImage.getFileName());
       return img;
    }

    public ImageResponse dtoToEntity(Image image) {
        return ImageResponse.builder()
                .imageUrl(image.getImageUrl())
                .build();
    }
}
