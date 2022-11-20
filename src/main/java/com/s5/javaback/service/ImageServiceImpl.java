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
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final AwsService awsService;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
   /* @Override
    @Transactional
    public List<Image> imagesPost(List<MultipartFile> postImage) {
        List<Image> imagesPost=new ArrayList<>();
        for (MultipartFile m: postImage ) {
            if(m==null||m.isEmpty()){
                imagesPost.add(imageRepository.findById(1L).get());
            }else { imagesPost.add(imageRepository.save(awsService.uploadFile(m)));}
        }
        LOGGER.warn("Array de amazon creado "+imagesPost.size());
        return imagesPost;
    }*/

    @Override
    @Transactional
    public Image imageUser(MultipartFile image) {
        Image img= awsService.uploadFile(image);
        LOGGER.warn("Iamegen creada"+ img.getFileName());
        return imageRepository.save(img);
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
        awsService.deleteFileFromS3Bucket(img.getImageUrl());
        return imageMapper.updateImageMapper(img, this.imageUser(newImage));
    }


}
