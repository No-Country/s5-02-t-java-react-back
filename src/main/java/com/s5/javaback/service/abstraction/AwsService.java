package com.s5.javaback.service.abstraction;

import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.InputStream;
import java.util.List;

public interface AwsService {
    Image uploadFile(MultipartFile file);

    List<String> getObjectsFromS3();

    InputStream downloadFile(String key);
    public String deleteFileFromS3Bucket(String fileUrl);
}
