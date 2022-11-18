package com.s5.javaback.service.abstraction;

import com.s5.javaback.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface AwsService {
    Image uploadFile(MultipartFile file);

    List<String> getObjectsFromS3();

    InputStream downloadFile(String key);
    public String deleteFileFromS3Bucket(String fileUrl);
}
