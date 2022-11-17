package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.response.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

public interface ImageService {
    //List<Image> imagesPost(List<MultipartFile> postImagep);
    Image imageUser(MultipartFile image);
    ImageResponse imageUp(MultipartFile image);
    public void delete(Long id);
    public Image update(Long id, Image img);
    public MultipartFile userDefault() throws IOException;
}
