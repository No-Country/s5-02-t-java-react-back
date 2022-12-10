package com.s5.javaback.service;

import com.s5.javaback.mapper.ImageMapper;
import com.s5.javaback.model.entity.Image;
import com.s5.javaback.model.response.ImageResponse;
import com.s5.javaback.repository.ImageRepository;
import com.s5.javaback.service.abstraction.AwsService;
import com.s5.javaback.service.abstraction.ImageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final AwsService awsService;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    @Override
    @Transactional
    public List<Image> imagesPost(List<MultipartFile> postImage) {
        List<Image> imagesPost=new ArrayList<>();
        for (MultipartFile img: postImage ) {
            if(img==null||img.isEmpty()){
                imagesPost.add(imageRepository.findById(2L).get());
            }else { imagesPost.add(imageRepository.save(awsService.uploadFile(img)));}
        }
        LOGGER.warn("Array de amazon creado "+imagesPost.size());
        return imagesPost;
    }



    @Override
    public ImageResponse imageUp(MultipartFile image) {
        return null;
    }

    public Image findById(Long id){
        return imageRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Image img = findById(id);
        imageRepository.delete(img);
    }
    @Override
    @Transactional
    public Image update(Long id, MultipartFile newImage ){
        Image img = findById(id);
        if (img.getId()==1){
         Image image=  this.imageUser(newImage);
         return imageRepository.save(image);
        }else {
            awsService.deleteFileFromS3Bucket(img.getImageUrl());
            Image i=this.imageUser(newImage);
            img.setImageUrl(i.getImageUrl());
            img.setFileName(i.getFileName());
            Image newimg=imageRepository.save(img);
            return newimg;
        }
    }
    @Override
    @Transactional
    public Image imageUser(MultipartFile image) {
        return awsService.uploadFile(image);
    }

}
