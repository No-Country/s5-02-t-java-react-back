package com.s5.javaback.service;

import com.s5.javaback.service.abstraction.AwsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.InputStream;
import java.util.List;
@Service
public class AwsServiceImpl implements AwsService {
    @Override
    public Image uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public List<String> getObjectsFromS3() {
        return null;
    }

    @Override
    public InputStream downloadFile(String key) {
        return null;
    }

    @Override
    public String deleteFileFromS3Bucket(String fileUrl) {
        return null;
    }
}
