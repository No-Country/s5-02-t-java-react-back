package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.entity.Image;
import com.s5.javaback.model.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    List<Image> imagesPost(List<MultipartFile> postImagep);
    Image imageUser(MultipartFile image);
    ImageResponse imageUp(MultipartFile image);
    public void delete(Long id);
    public Image update(Long id, MultipartFile img);
}
