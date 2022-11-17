package com.s5.javaback.service;

import com.s5.javaback.model.response.ImageResponse;
import com.s5.javaback.service.abstraction.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public Image imageUser(MultipartFile image) {
        return null;
    }

    @Override
    public ImageResponse imageUp(MultipartFile image) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Image update(Long id, Image img) {
        return null;
    }

    @Override
    public MultipartFile userDefault() throws IOException {
        return null;
    }
}
